package edu.frescoplus.core.app.fresco.modules;

import edu.frescoplus.core.lib.AFP_Library;

/*
* @param next The name of next module to be invoked, null if none.
* @param name The name of this module.
* @param library The common library interface implementation to use (e.g. floodlight, onos libs)
* */
public abstract class AFresco_Module {
	public String name;
	protected AFP_Library library;

	public AFresco_Module(String name, AFP_Library library)
	{
		this.name = name;
		this.library = library;
	}
	public abstract void run();
}
