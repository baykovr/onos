package edu.frescoplus.module;

import edu.frescoplus.generic.IFP_Generic;
import edu.frescoplus.module.AFP_Module.Port;

// Grabs an open flow msg by parameter
public class FPMod_OFProc extends AFP_Module{

	// Input Ports
	// 

	// Output Ports
	// 0 : Data

	// Parameters
	String par_packetType;
	String par_packetField;

	public FPMod_OFProc(String name, String next, IFP_Generic library) {
		super(name, next, library);
		
		out_ports.add( new Port<>());
		
		par_packetType  = "PACKET_IN";
		par_packetField = "SRC_HOST";
	}

	@Override
	public void run() 
	{
		library.print("[FP]{FP_GetOFMsg_Module} enter.");
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
	// Assumption that getSrcHost has toString() method.
	private <T> void parsePacketIN()
	{
		if (library.isPacketIN() )
		{
			T source_host = library.getSrcHostIP();
			if(source_host != null)
			{
				library.print("[FP]{FP_GetOFMsg_Module} enter."+source_host.toString());
				out_ports.get(0).data = source_host;
			}
		}
	}
}
