package edu.frescoplus.core.common.app.frescomodules;

import edu.frescoplus.core.common.app.AFP_Module;
import edu.frescoplus.core.common.lib.AFP_Library;

/**
 * FrescoModule : takes an incoming flow,
 * extracts the port and compares to parameter.
 * Outputs a boolean result of the comparison.
 *
 */
public class FM_port_cmp extends AFP_Module {
    public int port;
    public boolean result;

    public FM_port_cmp(String name, AFP_Library library,
                       int port)
    {
        super(name,library);
        this.port = port;
    }

    @Override
    public void run()
    {
        // grab a packet and check the TCP port.
        // implying TCP packet.
        if ( library.isTCP() )
        {
            result = (library.getDstPort() == port);
        }
        else
        {
            result = false;
        }
    }
}
