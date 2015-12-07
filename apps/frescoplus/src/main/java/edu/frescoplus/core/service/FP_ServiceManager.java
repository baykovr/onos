package edu.frescoplus.core.service;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines an abstract fresco service.
 */
public class FP_ServiceManager
{
    List<AFP_Service> services;

    public FP_ServiceManager()
    {
        this.services = new ArrayList<AFP_Service>();
    }

    public void load_all()
    {
        services.forEach( service ->{
            if( !service.loaded() )
            {
                service.load();
            }
        });
    }

    public void run_all()
    {
        services.forEach( service ->{
            if( service.loaded() && !service.running())
            {
                service.run();
            }
        });
    }

    public void stop_all()
    {
        services.forEach( service ->{
            if( service.loaded() && service.running())
            {
                service.stop();
            }
        });
    }
    public void unload_all()
    {
        services.forEach( service ->{
            if( service.loaded() && !service.running())
            {
                service.unload();
            }
        });
    }
}
