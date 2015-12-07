package edu.frescoplus.core.app.fresco.modules;

import edu.frescoplus.core.lib.AFP_Library;

public class FM_do_reflect extends AFresco_Module {
    boolean doReflect;
    public FM_do_reflect(String name, AFP_Library library, boolean doReflect)
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
