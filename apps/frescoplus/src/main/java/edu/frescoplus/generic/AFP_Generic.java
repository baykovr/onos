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
	public abstract <T> void hostRedirect(T host_x, T host_y);

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
}

