package edu.frescoplus.core.models;

import edu.frescoplus.core.app.fresco.modules.AFresco_Module;

import java.util.ArrayList;
import java.util.Iterator;

/*
*
* Models application execution.
* */
public class AFP_TempAppModel implements Iterable<AFresco_Module>
{
	public final String name;

	private ArrayList<AFresco_Module> modules;

	public AFP_TempAppModel(String name) {
		assert (modules.size() > 0);
		this.name = name;
		this.modules = new ArrayList<AFresco_Module>();
	}

	public void addModule(AFresco_Module module) {
		modules.add(module);
	}

	public void exec() {
		for (AFresco_Module module : modules) {
			module.run();
		}
	}

	@Override
	public Iterator<AFresco_Module> iterator() {
		return modules.iterator();
	}
}
// Base case.
//		AFresco_Module currentModule = modules.get(mainModule);
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
//    public Iterator<AFresco_Module> iterator()
//    {
//        return new FPM_Iterator<AFresco_Module>();
//    }