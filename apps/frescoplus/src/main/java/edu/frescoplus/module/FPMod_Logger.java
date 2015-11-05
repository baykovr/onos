package edu.frescoplus.module;

import edu.frescoplus.generic.AFP_Generic;

// This is a prototype module only.

// The logging module logs some data to file.

public class FPMod_Logger extends AFP_Module {
	// Has one input port.
	public FPMod_Logger(String name, String next, AFP_Generic library, Port<?> in_port){
		super(name, next, library);
		in_ports.add(in_port);
	}

	@Override
	public void run() {
		library.log.info("[FP]{FP_LoggingModule} enter.");
		// print the data from in port 0 to stdout.
		library.log.info("[FP]{FP_LoggingModule} "+in_ports.get(0).data );
		
		// write data to file
		// TODO : some file operation.
	}
}
