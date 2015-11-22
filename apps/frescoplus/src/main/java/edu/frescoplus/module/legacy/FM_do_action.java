package edu.frescoplus.module.legacy;

import edu.frescoplus.generic.AFP_Generic;
import edu.frescoplus.module.AFP_Module;

public class FM_do_action extends AFP_Module{

    public FM_do_action(String name, String next, AFP_Generic library,
                       Port<Boolean> comparison)
    {
        super(name,next,library);
       in_ports.add(comparison);
    }

    @Override
    public void run() {
        if ( (Boolean) in_ports.get(0).data )
        {
            // block the packet in the current context.
            library.dropSource();
        }
        else
        {
            // forward the packet (normally)
            // Typically do nothing, allow the packet to proceed to the
            // fwd app.
        }
    }
}
