package edu.frescoplus.core.app.fresco.modules;

import edu.frescoplus.core.lib.AFP_Library;


public class FM_find_scan extends AFresco_Module {
    public boolean result;
    int check_ip;
    public FM_find_scan(String name, AFP_Library library,
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
