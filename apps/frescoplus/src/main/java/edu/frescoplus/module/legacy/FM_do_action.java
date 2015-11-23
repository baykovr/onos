package edu.frescoplus.module.legacy;

import edu.frescoplus.generic.AFP_Generic;
import edu.frescoplus.module.AFP_Module;

public class FM_do_action extends AFP_Module{
    boolean doAction;
    public FM_do_action(String name, AFP_Generic library,
                        boolean doAction)
    {
        super(name,library);
        this.doAction = doAction;
    }

    @Override
    public void run() {
       if(doAction)
       {
           library.DROP();
       }
        else
       {
           library.FORWARD();
       }
    }
}
