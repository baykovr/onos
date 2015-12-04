package edu.frescoplus.core.common.lib;

import org.slf4j.Logger;

/*
* Generalized Application Programmable Interface
* */

public abstract class AFP_Library
{
	public final Logger log;

	public AFP_Library(Logger log)
	{
		this.log = log;
	}

	// context
	public abstract class Context
	{
		public abstract void setContext();
	}

	// Requirement
	//
	// Application Model
	// + How are apps hosted, executed.
	//
	// Service Subsystems
	//
	// Status Module
	// 	+ system statistics/reports
	//	+ network statistics/reports
	//
	// Topology Module
	//	+ how are devices connected on the network?

	// Device Module
	//	+ which devices are on the network?

	// Network Traffic Module
	//	1. Control Plane
	//		+ proc OF msg

	//	2. Data Plane
	//		+ proc packets

	// FS / Storage
	//  + how do applications store data?
	//	+ public & private
	//  + fancy data structure,
	//

	// Shell Module
	//	+ scripting, configuring, interacting with the user.

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

	// Actions
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

	//<editor-fold desc="Helpers">
	public <T extends String> void logModuleError(T error)
	{
		log.error(error);
	}
	//</editor-fold>
}

