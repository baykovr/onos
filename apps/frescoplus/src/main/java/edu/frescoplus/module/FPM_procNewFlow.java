package edu.frescoplus.module;

import edu.frescoplus.generic.AFP_Generic;

/*
* FrescoPlus Module (Event) NewFlow:
* Provides event binding (entry point) for applications which process new flows.
*
* Input Ports:
* none
*
* Output Ports:
* 0 Packet
*
* Configuration Parameters:
* TODO: Traffic selector (only recieve certain types of packets).
* */
public class FPM_procNewFlow extends AFP_Module{
	// Parameters
	String par_packetType,par_packetField;

	public FPM_procNewFlow(String name, String next, AFP_Generic library) {
		super(name, next, library);
		
		out_ports.add( new Port<>());
		
		par_packetType  = "PACKET_IN";
		par_packetField = "SRC_HOST";
	}

	@Override
	public void run() {
		library.log.info("[FP]{FPM_procNewFlow} enter.");

		/*
		* Use lib. to grab packet descriptor and output it to out_port 0.
		* */

		// library.context.CurrentPacket
		// out_ports.get(0).data = library.context.CurrentPacket
//
//		switch(par_packetType)
//		{
//			case ("PACKET_IN"):
//			{
//				parsePacketIN();
//				break;
//			}
//			default:
//			{
//				library.logModuleError("[FP]{FPM_procNewFlow} unknown parameter packetType "+par_packetType);
//				break;
//			}
//		}
	}
// We will abstract this away in the library code of OFL,
// and it does not make sense in ONOS anyhow.
//	private <T> void parsePacketIN()
//	{
//		if (library.isPacketIN() )
//		{
//			// Extract src host
//			Object source_host = library.getSrcHostIP();
//			if(source_host != null)
//			{
//				library.log.info("[FP]{FPM_procNewFlow} enter."+source_host.toString());
//				out_ports.get(0).data = source_host;
//			}
//		}
//		// if ( library.isIPv4() )
//		// {
//		// 	library.print("[FP]{FPM_procNewFlow} Got a ipv4 packet of some sort.");
//
//		// }
//	}
}
