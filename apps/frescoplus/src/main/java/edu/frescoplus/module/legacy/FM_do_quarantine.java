package edu.frescoplus.module.legacy;

import edu.frescoplus.generic.AFP_Generic;
import edu.frescoplus.module.AFP_Module;

import java.net.Inet4Address;

public class FM_do_quarantine extends AFP_Module{

    Integer confidence_threshold;
    public FM_do_quarantine(String name, String next, AFP_Generic library,
                            Port<Integer> confidence, Port<Inet4Address> vicitim_ip,
                            Integer confidence_threshold)
    {
        super(name,next,library);
        in_ports.add(confidence);
        in_ports.add(vicitim_ip);
        this.confidence_threshold = confidence_threshold;
    }

    @Override
    public void run() {
        if( (Integer)in_ports.get(0).data > confidence_threshold)
        {
            library.quarantineIPv4( in_ports.get(1) );

        }
    }
}
