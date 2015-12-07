package edu.frescoplus.core.app.fresco.modules;

import edu.frescoplus.core.lib.AFP_Library;

import java.util.Date;

public class FM_monitor extends AFresco_Module {
    public FM_monitor(String name, AFP_Library library)
    {
        super(name,library);
    }

    @Override
    public void run() {
        // Log a timestamp and address of the current packet.
        Integer time = (int) (new Date().getTime()/1000);
        Integer ip_address = library.getSrcIP();

        // TODO : use storage service.
        //library.database.put(time.toString(),ip_address.toString() );
    }
}
