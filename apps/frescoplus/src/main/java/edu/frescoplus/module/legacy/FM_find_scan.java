package edu.frescoplus.module.legacy;

import edu.frescoplus.generic.AFP_Generic;
import edu.frescoplus.module.AFP_Module;

import java.net.Inet4Address;


public class FM_find_scan extends AFP_Module{
    public boolean result;
    int check_ip;
    public FM_find_scan(String name, AFP_Generic library,
                         int check_ip)
    {
        super(name,library);
        this.check_ip = check_ip;
    }

    @Override
    public void run()
    {
        // ask external source if the ip address is "scanning"
        result = false;
    }
}
