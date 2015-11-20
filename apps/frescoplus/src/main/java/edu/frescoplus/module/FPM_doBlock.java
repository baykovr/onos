package edu.frescoplus.module;

import edu.frescoplus.generic.AFP_Generic;

/**
 * FrescoPlus Module, block a host by mac or ipv4 address.
 *
 * Input Ports:
 *
 * 1 Data
 *
 * Output Ports:
 * none
 *
 * Configuration Parameters:
 * 0 Data Type { "MAC" , "IPv4" }
 *
 */
public class FPM_doBlock extends AFP_Module {

	String dataType;

	public FPM_doBlock(String name, String next, AFP_Generic library,
					   String dataType, Port data) {
		super(name, next, library);
		this.dataType = dataType;
		in_ports.add(data);
	}
	
	@Override
	public void run() {
		Port data    = in_ports.get(1);
		if ( !data.Empty() )
		{
			if( dataType == "MAC")
			{
				library.blockMAC(data.data);
			}
			else if (dataType == "IPv4")
			{
				library.blockIPv4(data.data);
			}
			else
			{
				library.logModuleError("Unknown data type "+dataType);
			}
		}

	}

}
