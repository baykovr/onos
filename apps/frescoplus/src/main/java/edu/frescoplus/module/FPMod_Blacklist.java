package edu.frescoplus.module;

import java.util.HashSet;

import edu.frescoplus.generic.AFP_Generic;


public class FPMod_Blacklist extends AFP_Module{

	// in_ports  
	//	[0] = a data item to check
	
	// out_ports
	//	[0] = returns true false if item is in the blacklist
	
	public HashSet<String> blacklist;
	
	public FPMod_Blacklist(String name, String next, AFP_Generic library,
			Port<?> dataPort,
			Port<?> resultPort) 
	{
		super(name, next, library);
		
		in_ports.add( dataPort ); 
		
		if(resultPort == null)
		{
			out_ports.add(new Port() );
		}
		else
		{
			out_ports.add( resultPort );
		}
		
		blacklist = new HashSet<String>();
		
	}

	public void addBlackItem(String item)
	{
		blacklist.add(item);
	}
	
	@Override
	public void run() {
		library.log.info("[FP]{FP_BlacklistModule} enter.");
		
		if(in_ports.get(0).data == null)
		{
			library.log.info("[FP]{FP_BlacklistModule} aborting on null in port.");
			return;
		}
		
		if(blacklist.contains( in_ports.get(0).data.toString() ))
		{
			library.log.info("[FP]{FP_BlacklistModule} has a match on "+ in_ports.get(0).data.toString() );
			out_ports.get(0).data = true;
		}
		else
		{
			out_ports.get(0).data = false;
		}
		
	}
}
