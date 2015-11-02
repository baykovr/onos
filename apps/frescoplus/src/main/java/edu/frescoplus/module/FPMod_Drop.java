package edu.frescoplus.module;

import edu.frescoplus.generic.IFP_Generic;

public class FPMod_Drop extends AFP_Module {

	// Inpurt Ports
	// 0 : if true will attempt to use aboard broadcast method.
	
	public FPMod_Drop(String name, String next, IFP_Generic library,
			Port doAbort) {
		super(name, next, library);
		in_ports.add(doAbort);
	}
	
	@Override
	public void run() {
		if(in_ports.get(0).data != null)
		{
			if ( (boolean)in_ports.get(0).data )
			{
				library.blockPacket();
			}
		}
		
	}

}
