package edu.frescoplus.module.legacy;

import edu.frescoplus.generic.AFP_Generic;
import edu.frescoplus.module.AFP_Module;

import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.HashSet;

/*
* Example blacklist module utilizing hashset to match ip addresses.
* */

public class FM_blacklist_check extends AFP_Module{
    public boolean result;
    Integer ip_to_check;
    public HashSet<Integer> blacklist;

    public FM_blacklist_check(String name, AFP_Generic library,
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
