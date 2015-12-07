package edu.frescoplus.core.app.fresco.modules;

import edu.frescoplus.core.lib.AFP_Library;

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
public class FM_a_cluster extends AFresco_Module {

    Integer score_threshold = 5;

    public FM_a_cluster(String name, String next, AFP_Library library)
    {
        super(name,library);
    }

    public Integer getExternalAScore(Inet4Address address)
    {
        // Ask external source for a score.
        // a plane clustering requires snort like functionality.
        //

        return 0;
    }

    @Override
    public void run()
    {

    }
}
