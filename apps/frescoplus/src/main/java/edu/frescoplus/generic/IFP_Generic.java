package edu.frescoplus.generic;

public interface IFP_Generic {
	// IO

	public boolean isPacketIN();

	public boolean isIPv4();
	
	public boolean isICMP();
	public boolean isTCP();
	public boolean isUDP();
	
	// IPv4 only.
	public <T> T getSrcHostIP();
	public <T> T getDstHostIP();
	
	public void getDstPort();
	public void getSrcPort();
	
	public <T> void print(T data);
	public <T> void logModuleError(T error);
	
	// 
	// Attempt to suppress (ignore) generating PACKET_OUT response to PACKET_IN
	public void blockPacket();
	
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

