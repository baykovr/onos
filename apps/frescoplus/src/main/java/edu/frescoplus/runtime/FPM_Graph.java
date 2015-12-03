package edu.frescoplus.runtime;

import java.util.ArrayList;
import java.util.Iterator;

import edu.frescoplus.module.AFP_Module;

/*
* Defines in which order we will execute fp modules
* Holds references to all modules.
* Using only a Single Entry point, i.e. a single module is marked as the entry
* point for the program.
* */

public class FPM_Graph implements Iterable<AFP_Module>{
	public final String name;

	private ArrayList<AFP_Module> modules;

	public FPM_Graph(String name) {
		assert (modules.size() > 0);
		this.name = name;
		this.modules = new ArrayList<AFP_Module>();
	}

	public void addModule(AFP_Module module) {
		modules.add(module);
	}

	public void exec() {
		for (AFP_Module module : modules) {
			module.run();
		}
	}

	@Override
	public Iterator<AFP_Module> iterator() {
		return modules.iterator();
	}
}
// Base case.
//		AFP_Module currentModule = modules.get(mainModule);
//		boolean hasNext = currentModule.hasNext();
//
//		// We need to run at least once for the main module.
//		do
//		{
//			// Execute module code
//			currentModule.run();
//
//			// Set next module, if we have it.
//			if ( currentModule.hasNext() )
//			{
//				currentModule = modules.get( currentModule.getNext() );
//				hasNext = true;
//			}
//			else
//			{
//				hasNext = false;
//			}
//		}while(hasNext);
//
//    @Override
//    public Iterator<AFP_Module> iterator()
//    {
//        return new FPM_Iterator<AFP_Module>();
//    }