package edu.frescoplus.module.legacy;

import edu.frescoplus.generic.AFP_Generic;
import edu.frescoplus.module.AFP_Module;

import java.net.Inet4Address;

public class FM_do_reflect extends AFP_Module{
    boolean doReflect;
    public FM_do_reflect(String name,AFP_Generic library, boolean doReflect)
    {
        super(name, library);
        this.doReflect = doReflect;
    }
    @Override
    public void run() {
        if(doReflect)
        {
            library.MIRROR();
        }
        else
        {
            library.FORWARD();
        }
    }
}
