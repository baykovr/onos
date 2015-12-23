package edu.frescoplus.onos;

import edu.frescoplus.core.lib.AFP_Library;
import org.onlab.packet.*;
import org.onosproject.core.ApplicationId;
import org.onosproject.net.DeviceId;
import org.onosproject.net.flow.*;
import org.onosproject.net.flow.criteria.Criterion;
import org.onosproject.net.flowobjective.DefaultForwardingObjective;
import org.onosproject.net.flowobjective.FlowObjectiveService;
import org.onosproject.net.flowobjective.ForwardingObjective;
import org.onosproject.net.packet.PacketContext;
import org.slf4j.Logger;

import java.util.Set;

public class FP_libONOS extends AFP_Library {

    private ApplicationId appId;
    private PacketContext context;
    private FlowObjectiveService flowObjectiveService;
    private FlowRuleService flowRuleService;


    public FP_libONOS(Logger log) {
        super(log);
    }

    /*
    * This is a critical function for the runtime.*/
    public void setContext(ApplicationId appId,
                           PacketContext context,
                           FlowObjectiveService flowObjectiveService,
                           FlowRuleService flowRuleService) {
        this.appId = appId;
        this.context = context;
        this.flowObjectiveService = flowObjectiveService;
        this.flowRuleService = flowRuleService;
    }

    @Override
    public boolean isIPv4(){
        Ethernet pkt = context.inPacket().parsed();
        return pkt.getEtherType() == Ethernet.TYPE_IPV4;
    }

    @Override
    public boolean isICMP() {
        Ethernet pkt = context.inPacket().parsed();
        return (isIPv4()) &&
                ((IPv4) pkt.getPayload()).getProtocol() == IPv4.PROTOCOL_ICMP;
    }

    @Override
    public boolean isTCP() {
        Ethernet pkt = context.inPacket().parsed();
        return (isIPv4()) &&
                ((IPv4) pkt.getPayload()).getProtocol() == IPv4.PROTOCOL_TCP;
    }

    @Override
    public boolean isUDP() {
        Ethernet pkt = context.inPacket().parsed();
        return (isIPv4()) &&
                ((IPv4) pkt.getPayload()).getProtocol() == IPv4.PROTOCOL_UDP;
    }

    @Override
    public fMac getMAC() {
        Ethernet eth = context.inPacket().parsed();
        DeviceId deviceId = context.inPacket().receivedFrom().deviceId();

        MacAddress src = eth.getSourceMAC();
        MacAddress dst = eth.getDestinationMAC();

        return new fMac<MacAddress>(src,dst);
    }

    @Override
    public fTCP getTCP()
    {
        Ethernet pkt = context.inPacket().parsed();
        IPv4 ipv4 = (IPv4) pkt.getPayload();
        TCP tcp   = (TCP) ipv4.getPayload();
        return new fTCP<>(
                ipv4.getSourceAddress(),
                ipv4.getSourceAddress(),
                tcp.getSourcePort(),
                tcp.getDestinationPort()
        );
    }

    @Override
    public fUDP getUDP()
    {
        Ethernet pkt = context.inPacket().parsed();

        IPv4 ipv4 = (IPv4) pkt.getPayload();
        UDP udp   = (UDP) ipv4.getPayload();

        return new fUDP<>(
                ipv4.getSourceAddress(),
                ipv4.getSourceAddress(),
                udp.getSourcePort(),
                udp.getDestinationPort()
        );
    }


    @Override
    public int getSrcIP() {
        Ethernet pkt = context.inPacket().parsed();
        return ((IPv4) pkt.getPayload()).getSourceAddress();
    }

    @Override
    public int getDstIP() {
        Ethernet pkt = context.inPacket().parsed();
        return ((IPv4) pkt.getPayload()).getDestinationAddress();
    }

    @Override
    public int getDstPort() {
        Ethernet pkt = context.inPacket().parsed();
        if (isTCP()) {
            IPv4 ipv4 = (IPv4) pkt.getPayload();
            TCP tcp = (TCP) ipv4.getPayload();
            return tcp.getDestinationPort();
        } else if (isUDP()) {
            IPv4 ipv4 = (IPv4) pkt.getPayload();
            UDP udp = (UDP) ipv4.getPayload();
            return udp.getDestinationPort();
        } else {
            return -1;
        }
    }

    @Override
    public int getSrcPort() {
        Ethernet pkt = context.inPacket().parsed();
        if (isTCP()) {
            IPv4 ipv4 = (IPv4) pkt.getPayload();
            TCP tcp = (TCP) ipv4.getPayload();
            return tcp.getSourcePort();
        } else if (isUDP()) {
            IPv4 ipv4 = (IPv4) pkt.getPayload();
            UDP udp = (UDP) ipv4.getPayload();
            return udp.getSourcePort();
        } else {
            return -1;
        }
    }

    @Override
    public int getPacketCount(int ip) {

        // Need to get device id.
        DeviceId deviceId = context.inPacket().receivedFrom().deviceId();

        for (FlowEntry flow : flowRuleService.getFlowEntries(deviceId))
        {
            // Check if this flow applies for ip address ip
            // I'm not sure how to extract this yet.
            Set<Criterion> criteria = flow.selector().criteria();
            for (Criterion c : criteria)
            {
                if ( c.type() == Criterion.Type.IPV4_SRC )
                {
                    System.out.println(c.toString());
                }
            }
        }
        // get the bytes
        return 0;
    }

    @Override
    public int getByteCount(int ip) {

        return 0;
    }

    @Override
    public void DROP() {
        Ethernet pkt = context.inPacket().parsed();
        DeviceId deviceId = context.inPacket().receivedFrom().deviceId();

        IPv4 cntxIPv4 = (IPv4) pkt.getPayload();

        TrafficSelector.Builder selectorBuilder = DefaultTrafficSelector.builder();

        Ip4Prefix matchIp4SrcPrefix =
                Ip4Prefix.valueOf(cntxIPv4.getSourceAddress(),
                        Ip4Prefix.MAX_MASK_LENGTH);

        selectorBuilder.matchEthType(Ethernet.TYPE_IPV4)
                .matchIPSrc(matchIp4SrcPrefix);

        TrafficTreatment drop = DefaultTrafficTreatment.builder()
                .drop().build();

        flowObjectiveService.forward(deviceId, DefaultForwardingObjective.builder()
                .fromApp(appId)
                .withSelector(selectorBuilder.build())
                .withTreatment(drop)
                .withFlag(ForwardingObjective.Flag.VERSATILE)
                .withPriority(128) // How high?
                .makeTemporary(60) // Temporary? How long.
                .add());
    }

    @Override
    public void FORWARD() {
        // Do nothing actually, ifwd will take care of it.
    }

    @Override
    public void REDIRECT(int destinationIP) {
        Ethernet pkt = context.inPacket().parsed();
        // Again we let ifwd do the work for us.
        IPv4 cntxIPv4 = (IPv4) pkt.getPayload();
        cntxIPv4.setDestinationAddress(destinationIP);
    }

    @Override
    public void MIRROR() {
        Ethernet pkt = context.inPacket().parsed();
        IPv4 cntxIPv4 = (IPv4) pkt.getPayload();
        int source = getSrcIP();
        int destination = getDstIP();

        // Swap out the L3 infroamtion and let ifwd deal with it.
        cntxIPv4.setSourceAddress(destination);
        cntxIPv4.setDestinationAddress(source);
    }

    @Override
    public void QUARANTINE() {
        // Tag the packet and only allow it to contact certain hosts.
    }
}
