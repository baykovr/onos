package edu.frescoplus.module;

import java.util.ArrayList;

import edu.frescoplus.generic.AFP_Generic;

/*


*
* @param next The name of next module to be invoked, null if none.
* @param name The name of this module.
* @param library The generic library interface implementation to use (e.g. floodlight, onos libs)
* TODO: Define parameter class, since the prototype is just strings, this inefficient.
* */
public abstract class AFP_Module {
	
	// list of input ports
	// list of output ports
	// ports should hold a value (its either the input argument to a module or return statement from a module, conceptually)
	
	final String name; // must be unique.
	String next; 
	
	AFP_Generic library;
	
	public ArrayList<Port> in_ports;
	public ArrayList<Port> out_ports;
	
	public AFP_Module(String name, String next, AFP_Generic library)
	{
		this.name    = name;
		this.next    = next;
		this.library = library;
		
		in_ports  = new ArrayList<Port>();
		out_ports = new ArrayList<Port>();
	}
	
	public String getNext()
	{
		return next;
	}
	public String getName()
	{
		return name;
	}
	
	public boolean hasNext()
	{
		if(next == null || next.isEmpty() )
		{	
			return false;
		}
		else
		{
			return true;
		}
	}

	public abstract void run();
	
	public class Port <T>
	{
		T data;

		public Port()
		{
			this.data = null;
		}
		
		public Port(T data)
		{
			this.data = data;
		}

		public boolean Empty()
		{
			return (data == null);
		}
	}
}
