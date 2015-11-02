package edu.frescoplus.module;

import java.util.HashMap;

import edu.frescoplus.generic.IFP_Generic;

// This is a test DB implementation which holds strings in memory
// using a hash map.

public class FP_DBModule extends AFP_Module {

	// Input Ports
	// 0 : data in ( "data", "key" )
	// 1 : command ( "store" , "lookup" )
	
	// Output Ports
	// 0 : data out ( "string" )
	
	// in-memory data base
	HashMap<String,String> database;
	
	public FP_DBModule(String name, String next, IFP_Generic library) {
		super(name, next, library);
		
		in_ports.add( new Port());
		in_ports.add( new Port());
		out_ports.add( new Port());
		
		database = new HashMap<String,String>();
	}

	@Override
	public void run() {
		String command = (String)out_ports.get(0).data;
		
		switch(command)
		{
		case "store":
			break;
		case "lookup":
			break;
		default:
			// TODO : dynamically get calling class name
			library.logModuleError("FPDB unrecognized command "+command);
		}
	}
}
