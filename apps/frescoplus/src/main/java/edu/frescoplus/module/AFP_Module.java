package edu.frescoplus.module;

import java.util.ArrayList;

import edu.frescoplus.generic.AFP_Generic;

/*
*
* @param next The name of next module to be invoked, null if none.
* @param name The name of this module.
* @param library The generic library interface implementation to use (e.g. floodlight, onos libs)
* */
public abstract class AFP_Module {
	public String name;
	protected  AFP_Generic library;

	public AFP_Module(String name, AFP_Generic library)
	{
		this.name = name;
		this.library = library;
	}
	public abstract void run();
	// Inputs
	// Outputs
	// Parameters
}
