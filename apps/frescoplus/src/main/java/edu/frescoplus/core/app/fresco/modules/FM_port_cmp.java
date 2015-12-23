package edu.frescoplus.core.app.fresco.modules;

import edu.frescoplus.core.lib.AFP_Library;

/**
 * FrescoModule : takes an incoming flow,
 * extracts the port and compares to parameter.
 * Outputs a boolean result of the comparison.
 *
 */
public class FM_port_cmp extends AFresco_Module {
    public int port;
    public int portType; // source port (1) , destination port (0)
    public boolean result;

    public FM_port_cmp(String name, AFP_Library library,
                       int portType,
                       int port)
    {
        super(name,library);
        this.portType = portType;
        this.port = port;
    }

    @Override
    public void run()
    {
        // grab a packet and check the TCP port.
        // implying TCP packet.
        if ( library.isTCP() )
        {
            if(this.portType == 1) // source port
            {
                result = (library.getDstPort() == port);
            }
            else // destination port
            {
                result = (library.getSrcPort() == port);
            }
        }
        else
        {
            result = false;
        }
    }
}
