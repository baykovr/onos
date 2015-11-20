package edu.frescoplus.module;

import edu.frescoplus.generic.AFP_Generic;

public class FP_DropperModule extends AFP_Module {

	// Inpurt Ports
	// 0 : if true will attempt to use aboard broadcast method.
	
	public FP_DropperModule(String name, String next, AFP_Generic library,
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
				//library.blockPacket();
			}
		}
		
	}

}
