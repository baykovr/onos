package edu.frescoplus.core.app.fresco;

import edu.frescoplus.core.app.AFP_App;
import edu.frescoplus.core.app.fresco.modules.AFresco_Module;
import edu.frescoplus.core.app.fresco.modules.FM_blacklist_check;
import edu.frescoplus.core.app.fresco.modules.FM_do_action;
import edu.frescoplus.core.app.fresco.modules.FM_select;
import edu.frescoplus.core.event.FP_Event;
import edu.frescoplus.core.lib.AFP_Library;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * FrescoScript Application
 *
 * The default entry point is the first module, by design the fresco application subscribes to our event model
 * based on the *processed script*, for now we will simply statically subscribe.
 * FrescoModules are implemented as standalone pieces of code which utilize the common library.
 * (e.g. script will dictate what events to use and how)
 */
public class FrescoApp extends AFP_App<AFP_Library,FP_Event>
{
    // fresco is composed of interchangeable modules.
    private ArrayList<AFresco_Module> modules;

    public FrescoApp(String name, AFP_Library library) {
        super(name,library);
        subscribedEvents = new ArrayList<FP_Event>();
        subscribedEvents.add(FP_Event.PACKET);
        modules = new ArrayList<>();
    }

    private void staticTest()
    {
        FM_select ip_selector = new FM_select("ip-selector",library,"srcIP");
        FM_blacklist_check blacklist = new FM_blacklist_check("ip-blacklist",library, ip_selector.result);
        FM_do_action dropper = new FM_do_action("dropper",library, blacklist.result);

        addModule(ip_selector);
        addModule(blacklist);
        addModule(dropper);

    }

    // Process a script from file, create a fresco script applications.
    private void procScriptFile(String filename)
    {
        // todo : port floodlight parser.
    }

    private void addModule(AFresco_Module module) {
        modules.add(module);
    }

    // we can grab the module iterator to customize the entry point of fp scripts.
    public Iterator<AFresco_Module> iterator() {
        return modules.iterator();
    }

    @Override
    public void run() {
        library.log.info("fresco got a packet");
        // fresco uses a simple "sequential" execution model which does not
        // provide explicit control flow betwixt the modules.
        for (AFresco_Module module : modules) {
            module.run();
        }
    }
}
