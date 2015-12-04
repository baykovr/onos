package edu.frescoplus.core.common.app.frescomodules;

import edu.frescoplus.core.common.app.AFP_Module;
import edu.frescoplus.core.common.lib.AFP_Library;

import java.util.Date;

public class FM_monitor extends AFP_Module {
    public FM_monitor(String name, AFP_Library library)
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
