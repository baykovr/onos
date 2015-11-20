package edu.frescoplus.module;

import edu.frescoplus.generic.AFP_Generic;

/**
 * FrescoPlus Module, do an action based on conditional
 *
 * Input Ports:
 * 0 Boolean Value
 *
 * Output Ports:
 * none
 *
 * TODO : We should create an action class inside of library and pass that on an input port.
 * Configuration Parameters:
 * 0 action if True
 * 1 action if False
 *
 */
public class FPM_doAction extends AFP_Module {

    public FPM_doAction(String name, String next, AFP_Generic library) {
        super(name, next, library);

    }

    @Override
    public void run()
    {

    }
}
