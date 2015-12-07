package edu.frescoplus.core.lib;

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
	public abstract fMac getMAC();
	public abstract fTCP getTCP();
	public abstract fUDP getUDP();


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
	// todo : provided by service
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


	public class fMac<Address>
	{
		Address source;
		Address destination;

		public fMac(Address s, Address d)
		{
			this.source = s;
			this.destination = d;
		}

		public boolean equals(fMac mac)
		{
			return (mac.source == this.source &&
					mac.destination == this.destination);
		}

		public Address getSource() {
			return source;
		}

		public Address getDestination() {
			return destination;
		}
	}

	public class fL3Packet<Address,Port>
	{
		Address srcAddr,dstAddr;
		Port srcPort,dstPort;

		public fL3Packet(Address srcAddr, Address dstAddr, Port srcPort, Port dstPort)
		{
			this.srcAddr = srcAddr;
			this.dstAddr = dstAddr;

			this.srcPort = srcPort;
			this.dstPort = dstPort;
		}

		public boolean equals(fL3Packet pkt)
		{
			return (pkt.srcAddr == this.srcAddr &&
				    pkt.dstAddr == this.dstAddr &&
					pkt.srcPort == this.srcPort &&
					pkt.dstPort == this.dstPort);
		}

		public Address getSrcAddr() {
			return srcAddr;
		}

		public Address getDstAddr() {
			return dstAddr;
		}

		public Port getSrcPort() {
			return srcPort;
		}

		public Port getDstPort() {
			return dstPort;
		}
	}

	public class fTCP<Address,Port> extends fL3Packet<Address,Port>
	{
		public fTCP(Address srcAddr, Address dstAddr, Port srcPort, Port dstPort) {
			super(srcAddr, dstAddr, srcPort, dstPort);
		}
	}

	public class fUDP<Address,Port> extends fL3Packet<Address,Port>
	{
		public fUDP(Address srcAddr, Address dstAddr, Port srcPort, Port dstPort) {
			super(srcAddr, dstAddr, srcPort, dstPort);
		}
	}
}

