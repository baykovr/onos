package edu.frescoplus.core.common.app.frescomodules;

import edu.frescoplus.core.common.app.AFP_Module;
import edu.frescoplus.core.common.lib.AFP_Library;

public class FM_do_reflect extends AFP_Module {
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
