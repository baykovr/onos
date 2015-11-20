package edu.frescoplus.module;

import edu.frescoplus.generic.AFP_Generic;

/**
 * FrescoPlus Module Packet Field Selector
 * Extracts a specific field from a packet.
 *
 * Input Ports:
 * 0 Packet Data (e.g. a tcp packet)
 *
 * Output Port:
 * 0 Packet Field (e.g. some tcp field, such as source port)
 *
 * Configuration Parameters:
 * 0 Packet Type
 * 1 Field Specification
 */
public class FPM_PktFieldSelector extends AFP_Module{

    Class<?> pktField;

    public FPM_PktFieldSelector(String name, String next, AFP_Generic library,
                                Port pktData, Class<?> pktField)
    {
        super(name,next,library);
        this.pktField = pktField;

        in_ports.add(pktData);
        out_ports.add(new Port<>());
    }
    @Override
    public void run()
    {
        // TODO Library should provide packet abstraction, cast to lib.Packet.
        Object packet = in_ports.get(0).data;
        // Check type and extract field
        // check field, actually field implies packet type
        // library.packet.ipv4.source_address
        // ex:    if(pktType == int.class)
        //        {
        //
        //        }


    }
}
