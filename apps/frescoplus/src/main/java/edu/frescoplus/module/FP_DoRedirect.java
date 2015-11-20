package edu.frescoplus.module;

import edu.frescoplus.generic.AFP_Generic;

/**
 * Created by baykovr on 11/10/15.
 *
 * Redirect traffic from host X to Y
 * where hosts are identified by IPv4 Address
 * PORTS_IN
 * 0 : host X ip
 * 1 : host Y ip
 * PORTS_OUT
 * none
 */
public class FP_DoRedirect extends AFP_Module {
    public FP_DoRedirect(String name, String next, AFP_Generic library,
                         Port<?> host_x,
                         Port<?> host_y)
    {
        super(name, next, library);
        in_ports.add(host_x);
        in_ports.add(host_y);
    }
    @Override
    public void run() {
        // insert flow mod to redirect traffic from X to Y based on port values.
        Object host_x = in_ports.get(0);
        Object host_y = in_ports.get(1);

        //library.hostRedirect(host_x,host_y);

    }
}
