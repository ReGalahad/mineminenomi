package xyz.pixelatedw.mineminenomi.api.debug;

import java.lang.management.ManagementFactory;
import java.util.logging.Level;
import java.util.logging.Logger;

import xyz.pixelatedw.mineminenomi.Env;

public class WyDebug
{
	private static Logger logger = Logger.getLogger(Env.PROJECT_ID);
	
	public static boolean isDebug()
	{
		return ManagementFactory.getRuntimeMXBean().getInputArguments().toString().indexOf("-agentlib:jdwp") > 0;
	}

	public static void info(Object msg)
	{
		logger.info("[WYPI] [" + Env.PROJECT_ID.toUpperCase() + "] " + String.valueOf(msg));
	}

	public static void warn(Object msg)
	{
		logger.warning("[WYPI] [" + Env.PROJECT_ID.toUpperCase() + "] " + String.valueOf(msg));
	}

	public static void error(Object msg)
	{
		logger.log(Level.SEVERE, "[WYPI] [" + Env.PROJECT_ID.toUpperCase() + "] " + String.valueOf(msg));
	}
	
	public static void debug(Object msg)
	{
		if(isDebug())
			logger.log(Level.INFO, "[WYPI] [" + Env.PROJECT_ID.toUpperCase() + "] " + String.valueOf(msg));
	}
}
