package edu.frescoplus.module;

import edu.frescoplus.module.AFP_Module.Port;

public class FP_ModuleConnection {
	public Port<?> src,dst;
	
	public FP_ModuleConnection(Port<?> src, Port<?> dst)
	{
		this.src = src;
		this.dst = dst;
	}
}
