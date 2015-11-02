package edu.frescoplus.runtime;

import java.util.ArrayList;

import edu.frescoplus.generic.IFP_Generic;

public abstract class AFP_RTE {
	public IFP_Generic library;
	public ArrayList<FP_CallGraph> fpApps;
	
	
	public AFP_RTE()
	{
		fpApps = new ArrayList<FP_CallGraph>();
	}
	
	public abstract void exec();
}
