package edu.frescoplus.core.app.fresco.modules;

import edu.frescoplus.core.lib.AFP_Library;

/*
* A traffic selector/filter.
* */

public class FM_select extends AFresco_Module {
    public int result;
    String packet_attribute;

    public FM_select(String name, AFP_Library library,
                     String packet_attribute){
        super(name,library);
        this.packet_attribute = packet_attribute;
    }

    @Override
    public void run() {
        switch(packet_attribute)
        {
            case "srcIP":
                result = library.getSrcIP();
                break;
            case "dstIP":
                result = library.getDstIP();
                break;
            case "srcPort":
                result = library.getSrcPort();
                break;
            case "dstPort":
                result = library.getDstPort();
                break;
            default:
                result = 0;
        }
    }
}
