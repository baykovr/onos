package edu.frescoplus.runtime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import edu.frescoplus.module.AFP_Module;
import edu.frescoplus.module.FP_ModuleConnection;

// Defines in which order we will execute fp modules
// Holds references to all modules.

// Using only a Single Entry point, i.e. a single module is marked as the entry 
// point for the program. 

public class FP_CallGraph {
	public final String name;
	final String mainModule;
	
	HashMap<String,AFP_Module> modules;
	ArrayList<FP_ModuleConnection> connections;
	
	public FP_CallGraph(String name, String mainModule ,HashMap<String,AFP_Module> modules)
	{
		assert(modules.size() > 0);
		this.name       = name;
		this.modules    = modules;
		this.mainModule = mainModule;
	}
	
	public void AddConnection(FP_ModuleConnection connection)
	{
		connections.add(connection);
	}

	public void exec()
	{
		// Base case.
		AFP_Module currentModule = modules.get(mainModule);
		boolean hasNext = currentModule.hasNext();
		
		// We need to run at least once for the main module.
		do
		{
			// Execute module code
			currentModule.run();
			
			// Set next module, if we have it.
			if ( currentModule.hasNext() )
			{
				currentModule = modules.get( currentModule.getNext() );
				hasNext = true;
			}
			else
			{
				hasNext = false;
			}
		}while(hasNext);
	}
}
