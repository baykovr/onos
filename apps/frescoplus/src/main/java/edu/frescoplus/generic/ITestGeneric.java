package edu.frescoplus.generic;

import java.util.logging.Logger;

// This generic test runs in a standard java (no controller nbi)
//public class ITestGeneric implements IFP_Generic
public class ITestGeneric implements IFP_Generic{
	
	@Override
	public <T> void print(T data) {
		// TODO Auto-generated method stub
		System.out.format("ITest %s",data);
	}

	// Java does not support specialization so 
	// so handle things by switching on error.
	@Override
	public <T> void logModuleError(T error) {
		Logger log = Logger.getLogger("TestLibrary");
		
		// TODO : switch on type, currently expect string
		//		: note we can pass a custom error class here.
		log.severe(error.toString());
	}

	@Override
	public boolean isPacketIN() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isIPv4() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isICMP() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTCP() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isUDP() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> T getSrcHostIP() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T getDstHostIP() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getDstPort() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getSrcPort() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void blockPacket() {
		// TODO Auto-generated method stub
		
	}
}
