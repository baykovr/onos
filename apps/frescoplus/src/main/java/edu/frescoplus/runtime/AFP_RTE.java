package edu.frescoplus.runtime;

import java.util.ArrayList;

import edu.frescoplus.generic.AFP_Generic;

public abstract class AFP_RTE {
	public AFP_Generic library;
	public ArrayList<FPM_Graph> fpApps;
	
	
	public AFP_RTE()
	{
		fpApps = new ArrayList<FPM_Graph>();
	}
	
	public void exec()
	{
		if ( fpApps.size() > 0)
		{
			library.log.info("[FP] Executing : {} \n", fpApps.get(0).name);

			fpApps.get(0).exec();
		}
		else
		{
			library.logModuleError("No applicatios or modules to run.");
		}
	}
}
