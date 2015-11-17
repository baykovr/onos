package edu.frescoplus.onos;

import edu.frescoplus.generic.AFP_Generic;
import edu.frescoplus.database.FP_DBEntry;
import org.onlab.packet.Ethernet;
import org.onlab.packet.IPv4;
import org.onlab.packet.MacAddress;
import org.onosproject.core.ApplicationId;
import org.onosproject.net.DeviceId;
import org.onosproject.net.flow.DefaultTrafficSelector;
import org.onosproject.net.flow.DefaultTrafficTreatment;
import org.onosproject.net.flow.TrafficSelector;
import org.onosproject.net.flow.TrafficTreatment;
import org.onosproject.net.flowobjective.DefaultForwardingObjective;
import org.onosproject.net.flowobjective.FlowObjectiveService;
import org.onosproject.net.flowobjective.ForwardingObjective;
import org.onosproject.net.packet.PacketContext;

import org.slf4j.Logger;

/**
 * Created by baykovr on 10/27/15.
 */
public class FpLibONOS extends AFP_Generic {

    private ApplicationId appId;
    private PacketContext context;
    private FlowObjectiveService flowObjectiveService;

    public FpLibONOS(Logger log)
    {
        super(log);

        FP_DBEntry<Integer> tem_istr = new FP_DBEntry<Integer>(1,"test");

        FP_DBEntry<String> tem_iint = new FP_DBEntry<String>("test",2);

        db.data.add(tem_istr);
        db.data.add(tem_iint);

        log.info( db.data.get(0).data.toString() );
        System.out.println(db.data.get(1).data);
    }

    public void setContext(PacketContext context)
    {
        this.context = context;
    }
    public void setFlowObjectiveService(FlowObjectiveService flowObjectiveService)
    {
        this.flowObjectiveService = flowObjectiveService;
    }


    @Override
    public boolean isPacketIN() {
        return false;
    }

    @Override
    public boolean isIPv4() {
        Ethernet eth = this.context.inPacket().parsed();
        return eth.getEtherType() == Ethernet.TYPE_IPV4;
    }

    @Override
    public boolean isICMP() {
        Ethernet eth = this.context.inPacket().parsed();
        return eth.getEtherType() == Ethernet.TYPE_IPV4 &&
                ((IPv4) eth.getPayload()).getProtocol() == IPv4.PROTOCOL_ICMP;
    }

    @Override
    public boolean isTCP() {
        Ethernet eth = this.context.inPacket().parsed();
        return eth.getEtherType() == Ethernet.TYPE_IPV4 &&
                ((IPv4) eth.getPayload()).getProtocol() == IPv4.PROTOCOL_TCP;
    }

    @Override
    public boolean isUDP() {
        Ethernet eth = this.context.inPacket().parsed();
        return eth.getEtherType() == Ethernet.TYPE_IPV4 &&
                ((IPv4) eth.getPayload()).getProtocol() == IPv4.PROTOCOL_UDP;
    }

    @Override
    public void getDstPort() {

    }

    @Override
    public void getSrcPort() {

    }


    @Override
    public void blockPacket() {

    }

    @Override
    public void blockSrcHost() {
        DeviceId deviceId = context.inPacket().receivedFrom().deviceId();
        Ethernet eth = this.context.inPacket().parsed();
        MacAddress src = eth.getSourceMAC();
        MacAddress dst = eth.getDestinationMAC();

        // Matcher
        TrafficSelector selector = DefaultTrafficSelector.builder()
                .matchEthSrc(src).matchEthDst(dst).build();

        // Action
        TrafficTreatment drop = DefaultTrafficTreatment.builder()
                .drop().build();

        // Forward request to service
        flowObjectiveService.forward(deviceId, DefaultForwardingObjective.builder()
                .fromApp(appId)
                .withSelector(selector)
                .withTreatment(drop)
                .withFlag(ForwardingObjective.Flag.VERSATILE)
                .withPriority(128) //integer value
                .makeTemporary(60) //expiration
                .add());
    }

    @Override
    public Integer getDstHostIP() {
        Ethernet eth = this.context.inPacket().parsed();
        if (isTCP() )
        {
            return ((IPv4)eth.getPayload()).getDestinationAddress();
        }
        else
        {
            return null;
        }
    }

    @Override
    public Integer getSrcHostIP() {
        Ethernet eth = this.context.inPacket().parsed();
        if (isTCP() )
        {
            return ((IPv4)eth.getPayload()).getSourceAddress();
        }
        else
        {
            return null;
        }
    }

    // Traffic Shaping
    @Override
    public void hostRedirect(Object mac_x, Object mac_y)
    {
        DeviceId deviceId = context.inPacket().receivedFrom().deviceId();
        Ethernet eth = this.context.inPacket().parsed();
        MacAddress src = eth.getSourceMAC();
        MacAddress dst = eth.getDestinationMAC();

        // Matcher
        TrafficSelector forward = DefaultTrafficSelector.builder()
                .matchEthSrc(src).matchEthDst(dst).build();

        // Action
        //TrafficTreatment drop = DefaultTrafficTreatment.builder().setTcpDst()

        // Forward request to service
        flowObjectiveService.forward(deviceId, DefaultForwardingObjective.builder()
                .fromApp(appId)
                .withSelector(selector)
                .withTreatment(forward)
                .withFlag(ForwardingObjective.Flag.VERSATILE)
                .withPriority(128) //integer value
                .makeTemporary(60) //expiration
                .add());
    }

}
