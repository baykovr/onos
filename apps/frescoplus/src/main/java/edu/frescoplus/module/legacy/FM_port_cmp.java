package edu.frescoplus.module.legacy;

import edu.frescoplus.generic.AFP_Generic;
import edu.frescoplus.module.AFP_Module;

/**
 * FrescoModule : takes an incoming flow,
 * extracts the port and compares to parameter.
 * Outputs a boolean result of the comparison.
 *
 */
public class FM_port_cmp extends AFP_Module {
    Integer port;

    public FM_port_cmp(String name, String next, AFP_Generic library,
                       Integer port, Port<Boolean> result)
    {
        super(name,next,library);
        out_ports.add(result);
    }

    @Override
    public void run() {
        // grab a packet and check the TCP port.
        // implying TCP packet.
        if ( library.isTCP() )
        {
                out_ports.get(0).data = ( port == (Integer) library.getDstPort() );
        }
        else
        {
            // Abort execution, since this packet does not match.
            super.setNext(null);
        }
    }
}
