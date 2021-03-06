package edu.frescoplus.generic;

import edu.frescoplus.database.FP_DB;
import org.slf4j.Logger;

public abstract class AFP_Generic {
	public AFP_Generic(Logger log)
	{
		this.log = log;
		db = new FP_DB();
	}

	//<editor-fold desc="Helpers">
	public final Logger log;

	public <T extends String> void logModuleError(T error)
	{
		log.error(error);
	}
	//</editor-fold>

	/*New Model Section*/


	//<editor-fold desc="Packet/Event Context">
	//
	//</editor-fold>

	//<editor-fold desc="Control Operations">
	// e.g. traffic shaping, blocking hosts, etc.

	public abstract <T> void blockMAC(T mac);
	public abstract void blockIPv4();

	public abstract <M> void redirectMAC(M src,M dst);
	public abstract <T> void redirectIPv4(T src,T dst);
	public abstract <T> void reflectIPv4(T address);
	public abstract <T> void quarantineIPv4(T address);

	//<editor-fold desc="Context Specific Operations">
	public abstract void dropSource();
	//</editor-fold>

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
	public abstract <T> T getSrcMAC();
	public abstract <T> T getDstMAC();
	//</editor-fold>

	//<editor-fold desc="L3 Operations">
	// e.g. IPv4, IPv6, ICMP
	public abstract boolean isIPv4();
	public abstract <T> T getSrcAddr();
	public abstract <T> T getDstAddr();
	//</editor-fold>

	//<editor-fold desc="L4 Operations">
	// e.g. TCP, UDP
	public abstract boolean isICMP();
	public abstract boolean isTCP();
	public abstract boolean isUDP();
	public abstract <T> T getDstPort();
	public abstract <T> T getSrcPort();
	//</editor-fold>

	//<editor-fold desc="L7 Operations">
	// e.g. HTTP
	public abstract boolean isHTTP();
	public abstract <T> T getPayload();

	//</editor-fold>

	//<editor-fold desc="Statistics">
	public abstract <T> Integer getPacketCount(T src, T dst);
	public abstract <T> Integer getByteCount(T src, T dst);
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
	public final FP_DB db;
}

