package edu.frescoplus.core.common.app.frescomodules;

import edu.frescoplus.core.common.app.AFP_Module;
import edu.frescoplus.core.common.lib.AFP_Library;

public class FM_do_quarantine extends AFP_Module {
    Integer confidence_threshold;
    public FM_do_quarantine(String name, String next, AFP_Library library,
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
