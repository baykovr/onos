package edu.frescoplus.core.runtime;

import edu.frescoplus.core.runtime.models.AFP_AppModel;

import java.util.ArrayList;

public abstract class AFP_RTE{

	// Application Model

	//
	//public AFP_AppModel<AFP_Modular>

	public ArrayList<AFP_AppModel> fpApps;

	public AFP_RTE()
	{
		fpApps = new ArrayList<AFP_AppModel>();
	}


	public void exec()
	{
		if ( fpApps.size() > 0)
		{
			//library.log.info("[FP] Executing : {} \n", fpApps.get(0).name);
			for(AFP_AppModel app : fpApps)
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
