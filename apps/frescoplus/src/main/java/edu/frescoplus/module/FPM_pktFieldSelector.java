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
 * 0 Packet Type { "ETH", "IPv4" , }
 * 1 Field Specification
 *  ETH | IPv4 { SRC_ADDR, DST_ADDR }
 */
public class FPM_pktFieldSelector extends AFP_Module{

    String pktHeader,pktField;

    public FPM_pktFieldSelector(String name, String next, AFP_Generic library,
                                Port pktData, String pktHeader, String pktField)
    {
        super(name,next,library);

        in_ports.add(pktData);

        this.pktHeader = pktHeader;
        this.pktField = pktField;

        out_ports.add(new Port<>());
    }
    @Override
    public void run()
    {
        switch( pktHeader )
        {
            case "ETH":
            {
                // Select field
                if ( pktField == "SRC_ADDR" )
                {
                    out_ports.get(0).data = library.getSrcMAC();
                }
            }
            default:
            {
                library.logModuleError("Unknown packet type "+in_ports.get( 1 ).data.toString());
                break;
            }
        }
    }

}
