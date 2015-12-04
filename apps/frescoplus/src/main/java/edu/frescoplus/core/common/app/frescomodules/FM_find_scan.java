package edu.frescoplus.core.common.app.frescomodules;

import edu.frescoplus.core.common.app.AFP_Module;
import edu.frescoplus.core.common.lib.AFP_Library;


public class FM_find_scan extends AFP_Module {
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
