package edu.frescoplus.core.runtime;

import edu.frescoplus.core.app.AFP_App;
import edu.frescoplus.core.event.FP_Event;
import edu.frescoplus.core.lib.AFP_Library;
import edu.frescoplus.core.models.AFP_AppModel;
import edu.frescoplus.core.service.FP_ServiceManager;

/*
* @param environment the environment where this runtime is executing, for example onos,floodlight,daylight*/

public class FP_RTE<Model extends AFP_AppModel,
		Library extends AFP_Library> {
	// We can utilize different execution models for our applications.
	protected Model model;

	// We can use different library implementations too.
	public Library library;

	// For example, new packet, or topology update, etc.
	protected FP_Event currEvent;

	protected FP_ServiceManager serviceManager;

	public FP_RTE(Model model, Library library) {
		this.library = library;
		this.serviceManager = new FP_ServiceManager();
		setModel(model);
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public void addApp(AFP_App app) {
		model.addApp(app);
	}

	public void exec(FP_Event event) {
		// Apply checks here before passing event to model.
		library.log.info("passing event to model");
		model.procEvent(event);
	}
}