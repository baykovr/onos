package edu.frescoplus.runtime;

import java.util.logging.Logger;

import edu.frescoplus.generic.ITestGeneric;

// Runs on native linux host.
public class RunTimeTest{
	
	public static final Logger log = Logger.getLogger("RunTimeTest");
		
	public static void main(String[] args) {
		log.info("started fp");
		
		//FP_TestRTE rte = new FP_TestRTE( new ITestGeneric() );
		//rte.exec();
		
		log.info("done");
	}

}
