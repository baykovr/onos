package edu.frescoplus.runtime;

import java.util.ArrayList;

public abstract class AFP_RTE{

	// Application Model
	public ArrayList<FPM_Graph> fpApps;

	public AFP_RTE()
	{
		fpApps = new ArrayList<FPM_Graph>();
	}


	public void exec()
	{
		if ( fpApps.size() > 0)
		{
			//library.log.info("[FP] Executing : {} \n", fpApps.get(0).name);
			for(FPM_Graph app : fpApps)
			{
				app.exec();
			}
		}
		else
		{
			//library.logModuleError("No applicatios or modules to run.");
		}
	}
}
