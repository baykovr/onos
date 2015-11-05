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
	
	public abstract void exec();
}
