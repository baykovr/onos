package edu.frescoplus.module.legacy;

import edu.frescoplus.generic.AFP_Generic;
import edu.frescoplus.module.AFP_Module;

import java.net.Inet4Address;


public class FM_find_scan extends AFP_Module{

    public FM_find_scan(String name, String next, AFP_Generic library,
                         Port<Inet4Address> sourceIP)
    {
        super(name, next, library);

        in_ports.add(sourceIP);
        out_ports.add( new Port<Inet4Address>() ); // The source IP.
        out_ports.add( new Port<Boolean>() );      // Result of scan.
    }

        @Override
    public void run() {
            // Ask the external scan detector service for a value.

            // Based on response set the output port.

    }
}
