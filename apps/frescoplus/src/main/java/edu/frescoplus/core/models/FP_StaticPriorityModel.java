package edu.frescoplus.core.models;

import edu.frescoplus.core.app.AFP_App;
import edu.frescoplus.core.event.FP_Event;

import java.util.ArrayList;

/*
* As in floodlight, the user will statically configure/specify the application execution sequence.
*
* */
public class FP_StaticPriorityModel extends AFP_AppModel
{
    public FP_StaticPriorityModel()
    {
        // We will use a simple static priority like floodlight.
        applications = new ArrayList<>();
    }

    @Override
    public <E extends FP_Event> void procEvent(E event)
    {
        // Loop through the applications to see who subscribes to this event
        // by order of who is "first", execute the application if it subscribes
        // to an event.
        for(AFP_App app : applications) {
            if(  app.subscribes(event) )
            {
                app.run();
            }
        }
    }

    @Override
    public void addApp(AFP_App app) {
        applications.add(app);
    }
}
