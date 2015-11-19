package edu.frescoplus.module;

import edu.frescoplus.generic.AFP_Generic;

/**
 *
 * FrescoPlus Module, Check if database contains entry.
 *
 * Input Ports:
 * 0 Data Entry
 *
 * Output Ports:
 * 0 Boolean result
 *
 * Configuration Parameters:
 * 0 DB Selector, which data stream do we need to access
 *
 * TODO : This module has a conditional, we should handle this better.
 */
public class FPM_dbContains extends AFP_Module{

    public FPM_dbContains(String name,String next, AFP_Generic library,
                          Port dataEntry, String nextTrue, String nextFalse)
    {
        super(name,next,library);
        in_ports.add(dataEntry);
    }

    @Override
    public void run()
    {
        // if library.database.contains( in_port.get(0) )
        // next = nextTrue
        // else
        // next = nextFalse
    }
}
