package edu.frescoplus.core.app;

import edu.frescoplus.core.event.FP_Event;
import edu.frescoplus.core.lib.AFP_Library;

import java.util.Collection;

/**
 *
 */
public abstract class AFP_App <L extends AFP_Library, E extends FP_Event>
{
    final String name;
    protected L library;

    public Collection<E> subscribedEvents;

    public AFP_App(String name, L library)
    {
        this.name = name;
        this.library = library;
    }

    public void setLibrary(L library)
    {
        this.library = library;
    }

    public boolean subscribes(E event)
    {
        return subscribedEvents.contains(event);
        /*
        for( E e : subscribedEvents )
        {
            if( e.equals(event) )
            {
                return true;
            }
        }
        return false;
        */
    }

    public abstract void run();
}
