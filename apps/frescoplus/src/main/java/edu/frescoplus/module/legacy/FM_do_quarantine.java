package edu.frescoplus.module.legacy;

import edu.frescoplus.generic.AFP_Generic;
import edu.frescoplus.module.AFP_Module;

import java.net.Inet4Address;

public class FM_do_quarantine extends AFP_Module{
    Integer confidence_threshold;
    public FM_do_quarantine(String name, String next, AFP_Generic library,
                            int victim_ip,
                            int confidence_threshold)
    {
        super(name,library);

        this.confidence_threshold = confidence_threshold;
    }

    @Override
    public void run()
    {
        // Request confidence score from external source (SNORT etc)
        // score = externalRequest(victim_ip);
        int score = 0;

        if( score > confidence_threshold)
        {
           library.QUARANTINE();
        }
    }
}
