package edu.frescoplus.module.legacy.botminer;

import edu.frescoplus.generic.AFP_Generic;
import edu.frescoplus.module.AFP_Module;

import java.net.Inet4Address;

/*
*
* A (activity) plane, such as scanning, attacking and spamming.
* Built on top of Snort typically, scan detection done with SCADE.
*
* Main features, abnormally high scan rate and weighted failed connection rate.
*
* Original fresco employees a limited feature, just the failed connection rate (default 5) threshold,
* ask the external network monitor (TWR)
*
*
* Concretely, input in an IP address we wish to analyze.
* Output is the ip address and score.
* */
public class FP_a_cluster extends AFP_Module{

    Integer score_threshold = 5;

    public FP_a_cluster(String name, String next, AFP_Generic library,
                        Port<Inet4Address> ipv4, Port<Integer> score)
    {
        super(name, next, library);

        in_ports.add(ipv4);
        out_ports.add(score);

    }

    public Integer getExternalAScore(Inet4Address address)
    {
        // Ask external source for a score.
        // a plane clustering requires snort like functionality.
        //

        return 0;
    }

    @Override
    public void run() {

        if ( getExternalAScore( (Inet4Address) in_ports.get(0).data ) > score_threshold)
        {
            out_ports.get(0).data = in_ports.get(0).data;
        }
        else
        {
            out_ports.get(0).data = null;
        }

    }
}
