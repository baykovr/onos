package edu.frescoplus.core.common.service;

import java.util.Iterator;
import java.util.List;

/**
 * Defines an abstract fresco service.
 */
public abstract class FP_ServiceManager<T extends AFP_Service>
        implements Iterable<T>
{
    List<T> services;

    public FP_ServiceManager(List<T> services)
    {
        this.services = services;
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

    public void add(T service)
    {
        services.add(service);

    }
    public void remove(T service)
    {
        services.remove(service);
    }

    @Override
    public Iterator<T> iterator() {
        return services.iterator();
    }

}
