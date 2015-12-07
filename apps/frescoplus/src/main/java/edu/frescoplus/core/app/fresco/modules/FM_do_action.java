package edu.frescoplus.core.app.fresco.modules;

import edu.frescoplus.core.lib.AFP_Library;

public class FM_do_action extends AFresco_Module {
    boolean doAction;
    public FM_do_action(String name, AFP_Library library,
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
