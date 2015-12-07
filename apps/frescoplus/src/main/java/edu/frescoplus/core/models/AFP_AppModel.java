package edu.frescoplus.core.models;


import edu.frescoplus.core.app.AFP_App;
import edu.frescoplus.core.event.FP_Event;

import java.util.Collection;

public abstract class AFP_AppModel
{
    Collection<AFP_App> applications;

    /*
    * Takes an event as input and returns an application iterator,
    * the app's which are triggered by the event.*/
    // TODO : who's responsible for application invocation, the pipeline model or the is the pipeline just an ordering?
    //public abstract <E> Iterator<AFP_App> procEvent(E event);

    // Each app will hold a set of event's it subscribes to, loop through them.
    // todo : event listener model like actual controllers.
    public abstract <E  extends FP_Event> void procEvent(E event);

    public abstract void addApp(AFP_App app);
}
