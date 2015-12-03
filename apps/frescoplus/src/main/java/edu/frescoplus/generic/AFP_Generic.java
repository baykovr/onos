package edu.frescoplus.generic;

import edu.frescoplus.database.FP_DB;
import org.slf4j.Logger;

import java.net.Inet4Address;
import java.util.HashMap;

public abstract class AFP_Generic
{
	public final Logger log;
	public HashMap<String,String> database;

	public AFP_Generic(Logger log)
	{
		this.log = log;
		database = new HashMap<>();
	}

	//<editor-fold desc="Helpers">
	public <T extends String> void logModuleError(T error)
	{
		log.error(error);
	}
	//</editor-fold>


	// Packet Abstractions
	public abstract boolean isIPv4();

	public abstract boolean isICMP();
	public abstract boolean isTCP();
	public abstract boolean isUDP();

	public abstract int getSrcIP();
	public abstract int getDstIP();

	public abstract int getDstPort();
	public abstract int getSrcPort();

	// Statistics
	// Query the open flow table for # of packets and # of bytes for a given host.
	public abstract int getPacketCount(int ip);
	public abstract int getByteCount(int ip);

	// ACTIONS

	// Install a drop flow rule for this packet.
	public abstract void DROP();

	// Forward packet as normal.
	public abstract void FORWARD();

	// Overwrite the intended L3 destination of the packet.
	public abstract void REDIRECT(int destinationIP);

	// Reflect packet back at the origin.
	public abstract void MIRROR();

	// Tag the packet and only allow it to contact certain hosts.
	public abstract void QUARANTINE();

	// External Events
	public int getTcpFailCount(int src_address)
	{
		// Query external event monitor.
		return 0;
	}
	public boolean isTcpFail(int dst_address)
	{
		// Query external event monitor.
		return false;
	}
}

