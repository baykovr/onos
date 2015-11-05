package edu.frescoplus.module;

import edu.frescoplus.generic.AFP_Generic;

// Grabs an open flow msg by parameter
public class FP_GetOFMsg_Module extends AFP_Module{

	// Input Ports
	// 

	// Output Ports
	// 0 : Data

	// Parameters
	String par_packetType,par_packetField;

	public FP_GetOFMsg_Module(String name, String next, AFP_Generic library) {
		super(name, next, library);
		
		out_ports.add( new Port<>());
		
		par_packetType  = "PACKET_IN";
		par_packetField = "SRC_HOST";
	}

	@Override
	public void run() {
		library.log.info("[FP]{FP_GetOFMsg_Module} enter.");
		switch(par_packetType)
		{
			case ("PACKET_IN"):
			{
				parsePacketIN();
				break;
			}
			default:
			{
				library.logModuleError("[FP]{FP_GetOFMsg_Module} unknown parameter packetType "+par_packetType);
				break;
			}
		}
	}

	private <T> void parsePacketIN()
	{
		if (library.isPacketIN() )
		{
			// Extract src host
			Object source_host = library.getSrcHostIP();
			if(source_host != null)
			{
				library.log.info("[FP]{FP_GetOFMsg_Module} enter."+source_host.toString());
				out_ports.get(0).data = source_host;
			}
		}
		// if ( library.isIPv4() )
		// {
		// 	library.print("[FP]{FP_GetOFMsg_Module} Got a ipv4 packet of some sort.");

		// }
	}
}
