package edu.frescoplus.onos;

import edu.frescoplus.generic.AFP_Generic;
import edu.frescoplus.database.FP_DBEntry;
import org.omg.CORBA.Object;
import org.onlab.packet.*;
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

import javax.crypto.Mac;

public class FP_LibONOS extends AFP_Generic {
    private ApplicationId appId;
    private PacketContext context;
    private FlowObjectiveService flowObjectiveService;

    private Ethernet ethPacket;

    public FP_LibONOS(Logger log) {
        super(log);
    }

    public void setContext(PacketContext context)
    {
        this.context = context;
        ethPacket = context.inPacket().parsed();
    }
    @Override
    public MacAddress getSrcMAC() {
        return ethPacket.getDestinationMAC();
    }

    @Override
    public MacAddress getDstMAC() {
        return ethPacket.getSourceMAC();
    }

    @Override
    public boolean isIPv4() {
        return ethPacket.getEtherType() == Ethernet.TYPE_IPV4;
    }

    @Override
    public boolean isICMP() {
        return ethPacket.getEtherType() == Ethernet.TYPE_IPV4 &&
                ((IPv4) ethPacket.getPayload()).getProtocol() == IPv4.PROTOCOL_ICMP;
    }

    @Override
    public Integer getSrcAddr() {
        if( isIPv4() )
        {
            return ((IPv4)ethPacket.getPayload()).getSourceAddress();
        }
        else
        {
            return null;
        }
    }

    @Override
    public Integer getDstAddr() {
        if( isIPv4() )
        {
            return ((IPv4)ethPacket.getPayload()).getDestinationAddress();
        }
        else
        {
            return null;
        }
    }

    @Override
    public boolean isTCP() {
        return ethPacket.getEtherType() == Ethernet.TYPE_IPV4 &&
                ((IPv4) ethPacket.getPayload()).getProtocol() == IPv4.PROTOCOL_TCP;
    }

    @Override
    public boolean isUDP() {
        return ethPacket.getEtherType() == Ethernet.TYPE_IPV4 &&
                ((IPv4) ethPacket.getPayload()).getProtocol() == IPv4.PROTOCOL_UDP;
    }

    @Override
    public Integer getDstPort() {
        if( isTCP() )
        {
            // I love inlining too but someone needs to read this eventually.
            IPv4 ipv4 = (IPv4) ethPacket.getPayload();
            TCP  tcp  = (TCP) ipv4.getPayload();
            return tcp.getDestinationPort();
        }
        else if( isUDP() )
        {
            IPv4 ipv4 = (IPv4) ethPacket.getPayload();
            UDP udp  = (UDP) ipv4.getPayload();
            return udp.getDestinationPort();
        }
        else
        {
            return null;
        }
    }

    @Override
    public Integer getSrcPort() {
        if( isTCP() )
        {
            // I love inlining too but someone needs to read this eventually.
            IPv4 ipv4 = (IPv4) ethPacket.getPayload();
            TCP  tcp  = (TCP) ipv4.getPayload();
            return tcp.getSourcePort();
        }
        else if( isUDP() )
        {
            IPv4 ipv4 = (IPv4) ethPacket.getPayload();
            UDP udp  = (UDP) ipv4.getPayload();
            return udp.getSourcePort();
        }
        else
        {
            return null;
        }
    }

    @Override
    public boolean isHTTP() {
        return false;
    }

    @Override
    public <T> T getPayload() {
        return null;
    }


    @Override
    public <T> void blockMAC(T mac)
    {
        DeviceId deviceId = context.inPacket().receivedFrom().deviceId();

        MacAddress src = (MacAddress) mac;

        // Matcher
        TrafficSelector selector = DefaultTrafficSelector.builder()
                .matchEthSrc(src).build();

        // Action
        TrafficTreatment drop = DefaultTrafficTreatment.builder()
                .drop().build();

        //Forward request to service
        flowObjectiveService.forward(deviceId, DefaultForwardingObjective.builder()
                .fromApp(appId)
                .withSelector(selector)
                .withTreatment(drop)
                .withFlag(ForwardingObjective.Flag.VERSATILE)
                .withPriority(128) //integer value
                .makeTemporary(60) //expiration (optional)
                .add());
    }

    @Override
    public <T> void blockIPv4(T ipv4) {
        // Match on IPv4 and install a dropper rule.
    }

    @Override
    public <T> void redirectMAC(T src, T dst) {

    }

    @Override
    public <T> void redirectIPv4(T src, T dst) {

    }
}
