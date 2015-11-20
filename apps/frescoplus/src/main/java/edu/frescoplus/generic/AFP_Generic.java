package edu.frescoplus.generic;

import edu.frescoplus.database.FP_DB;
import org.slf4j.Logger;

public abstract class AFP_Generic<L,C> {
	// IO
	public final Logger log;
	public final FP_DB db;

	public AFP_Generic(Logger log)
	{
		this.log = log;
		db = new FP_DB();
	}
	// Packet Checkers
	public abstract boolean isPacketIN();
	public abstract boolean isIPv4();
	public abstract boolean isICMP();
	public abstract boolean isTCP();
	public abstract boolean isUDP();

	// Packet Attribute Retrieval (IPv4)
	public abstract <T> T getSrcHostIP();
	public abstract <T> T getDstHostIP();
	public abstract void getDstPort();
	public abstract void getSrcPort();

	// Traffic Shaping
	public abstract <T> void hostRedirect(T mac_x, T mac_y);

	 // Logging and Printing
	public <T extends String> void logModuleError(T error)
	{
		log.error(error);
	}

	// Attempt to suppress (ignore) generating PACKET_OUT response to PACKET_IN
	public abstract void blockPacket();

	public abstract void blockSrcHost();

	// State Tracking / Stateful
	// Device Management
	// Timers
	// Database
	// *-*

	//<editor-fold desc="Controller Specific Drivers">
	// e.g. controller execution context, states, etc.
	//</editor-fold>

	//<editor-fold desc="Control Plane Operations">
	//</editor-fold>

	/*
	* Packet operations, namely retrieval (reading) and modification (writing).
	*
	* Use Case: Reading is straight forward, all network logic relies on the
	* fundamental ability to read information from network flows.
	*
	* Modifying packets is necessary for higher order management,
	* e.g. beyond simple routing.
	* */
	//<editor-fold desc="L1 Operations">
	// e.g. Switch port number
	//</editor-fold>

	//<editor-fold desc="L2 Operations">
	// e.g. MAC
	//</editor-fold>

	//<editor-fold desc="L3 Operations">
	// e.g. HTTP
	//</editor-fold>

	//<editor-fold desc="L4 Operations">
	// e.g. HTTP
	//</editor-fold>

	//<editor-fold desc="L7 Operations">
	// e.g. HTTP
	//</editor-fold>

	/*
	* Host operations, abstractions above flows.
	*
	* We may wish to track a host by some attribute (typically MAC)
	* across the network.
	* */
	//<editor-fold desc="Host Operations">
	// e.g. discovery, quarantine, traffic isolation.
	//</editor-fold>

	/*
	* Database, flow logging and more.
	*
	* Storing network information is required in oder to implement
	* network state tracking applications (such as a load-balancer).
	*
	*
	* */
}

