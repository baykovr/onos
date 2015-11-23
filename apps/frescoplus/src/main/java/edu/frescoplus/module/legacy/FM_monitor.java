package edu.frescoplus.module.legacy;

import edu.frescoplus.generic.AFP_Generic;
import edu.frescoplus.module.AFP_Module;

import java.util.Date;

public class FM_monitor extends AFP_Module{
    public FM_monitor(String name, AFP_Generic library)
    {
        super(name,library);
    }

    @Override
    public void run() {
        // Log a timestamp and address of the current packet.
        Integer time = (int) (new Date().getTime()/1000);
        Integer ip_address = library.getSrcIP();
        library.database.put(time.toString(),ip_address.toString() );
    }
}
