package edu.frescoplus.runtime;

import java.util.HashMap;

import edu.frescoplus.generic.IFP_Generic;
import edu.frescoplus.generic.ITestGeneric;
import edu.frescoplus.module.AFP_Module;
import edu.frescoplus.module.FP_GetOFMsg_Module;
//public class FP_TestRTE extends AFP_RTE{
public class FP_TestRTE{
//
//	public FP_TestRTE(IFP_Generic library) {
//		library = new ITestGeneric();
//		
//		this.addStaticApp();
//	}
//	
//	public void addStaticApp()
//	{
//		//String name, String mainModule ,HashMap<String,AFP_Module modules
//		HashMap<String,AFP_Module> modules = new HashMap<String,AFP_Module>();
//		
//		// This test app has a single module, next is null since this is the one and only module
//		
//		AFP_Module mod1 = new FP_GetOFMsg_Module("GetOF1",null,super.library); 
//		
//		modules.put(mod1.getName(),mod1);
//		
//		FP_CallGraph testApp = new FP_CallGraph("static-app-test",mod1.getName(),modules);
//		
//		// add this app to the list of all loaded apps.
//		// 
//		super.fpApps.add(testApp);
//	}
//	public void exec()
//	{
//		// Run the first app in fpApps
//		if ( fpApps.size() > 0)
//		{
//			super.fpApps.get(0).exec();
//		}
//		else
//		{
//			super.library.logModuleError("No modules to run.");
//		}
//	}
}
