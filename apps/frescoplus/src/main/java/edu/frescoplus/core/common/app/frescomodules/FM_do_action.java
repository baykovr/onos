package edu.frescoplus.core.common.app.frescomodules;

import edu.frescoplus.core.common.app.AFP_Module;
import edu.frescoplus.core.common.lib.AFP_Library;

public class FM_do_action extends AFP_Module {
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
