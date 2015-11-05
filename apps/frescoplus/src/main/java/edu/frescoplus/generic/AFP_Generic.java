package edu.frescoplus.generic;

import org.slf4j.Logger;

public abstract class AFP_Generic<L,C> {
	// IO
	public final Logger log;
	public AFP_Generic(Logger log)
	{
		this.log = log;
	}

	public abstract boolean isPacketIN();

	public abstract boolean isIPv4();
	
	public abstract boolean isICMP();
	public abstract boolean isTCP();
	public abstract boolean isUDP();
	
	// IPv4 only.
	public abstract <T> T getSrcHostIP();
	public abstract <T> T getDstHostIP();
	
	public abstract void getDstPort();
	public abstract void getSrcPort();

	 // Logging and Printing
	public <T extends String> void logModuleError(T error)
	{
		log.error(error);
	}

	// Attempt to suppress (ignore) generating PACKET_OUT response to PACKET_IN
	public abstract void blockPacket();

	public abstract void blockSrcHost();

	
	// Database
	public class Database<T>
	{
		private T data;
		
	}

	public class DataEntry<K,V> 
	{
	    private K key;
	    private V value;
	    public DataEntry(K key,V value)
	    {
	    	this.key   = key;
	    	this.value = value;
	    }
	}

	
	// State Tracking / Stateful
	// Device Management
	// Timers
}

