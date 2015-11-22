package edu.frescoplus.module.legacy;

import edu.frescoplus.generic.AFP_Generic;
import edu.frescoplus.module.AFP_Module;

import java.net.Inet4Address;

public class FM_do_reflect <T,U>  extends AFP_Module{
    public FM_do_reflect(String name, String next, AFP_Generic library,
                         T scanResult, U sourceIP)
    {
        super(name, next, library);
        library.redirectMAC(scanResult,scanResult);
    }
    @Override
    public void run() {
        if( (Boolean)in_ports.get(0).data )
        {
            //library.reflectIPv4( in_ports.get(1) );
        }
        else
        {
            //fwd
        }

    }
}
