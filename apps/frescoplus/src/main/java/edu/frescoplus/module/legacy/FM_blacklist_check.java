package edu.frescoplus.module.legacy;

import edu.frescoplus.generic.AFP_Generic;
import edu.frescoplus.module.AFP_Module;

import java.util.ArrayList;
import java.util.HashSet;

public class FM_blacklist_check extends AFP_Module{
    public HashSet<String> blacklist;

    public FM_blacklist_check(String name, String next, AFP_Generic library,
                         Port<String> ipAddress,
                         Port<Boolean> result)
    {
        super(name, next, library);
        in_ports.add( ipAddress );
        out_ports.add( result );
    }

    private void populateBlacklist()
    {
        blacklist.add("10.0.0.1");
    }
    @Override
    public void run() {
//        library.log.info("[FP]{FPM_Blacklist} enter.");

        if(in_ports.get(0).data == null)
        {
            library.log.info("[FP]{FPM_Blacklist} aborting on null in port.");
            return;
        }

        if(blacklist.contains( in_ports.get(0).data.toString() ))
        {
            library.log.info("[FP]{FPM_Blacklist} has a match on "+ in_ports.get(0).data.toString() );
            out_ports.get(0).data = true;
        }
        else
        {
            out_ports.get(0).data = false;
        }
    }
}
