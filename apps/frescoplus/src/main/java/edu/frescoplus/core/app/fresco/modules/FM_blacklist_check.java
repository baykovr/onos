package edu.frescoplus.core.app.fresco.modules;

import edu.frescoplus.core.lib.AFP_Library;

import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.HashSet;

/*
* Example blacklist module utilizing hashset to match ip addresses.
* */

public class FM_blacklist_check extends AFresco_Module {
    public boolean result;
    Integer ip_to_check;
    public HashSet<Integer> blacklist;

    public FM_blacklist_check(String name, AFP_Library library,
                              int ip_address)
    {
        super(name,library);
        ip_to_check = ip_address;
    }

    private void populateBlacklist()
    {
        try {
            InetAddress addr = InetAddress.getByName("10.0.0.1");
            int address = ByteBuffer.wrap(addr.getAddress()).getInt();
            blacklist.add(address);
        }
        catch(Exception e) {
            library.logModuleError(e.getMessage());
        }
    }
    @Override
    public void run()
    {
        result = (blacklist.contains(ip_to_check));
    }
}
