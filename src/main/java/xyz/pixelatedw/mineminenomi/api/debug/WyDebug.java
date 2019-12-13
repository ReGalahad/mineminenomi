package xyz.pixelatedw.mineminenomi.api.debug;

import java.lang.management.ManagementFactory;
import java.util.logging.Level;
import java.util.logging.Logger;

import xyz.pixelatedw.mineminenomi.values.ModValuesEnv;

public class WyDebug
{
	private static Logger logger = Logger.getLogger(ModValuesEnv.PROJECT_ID);
	
	public static boolean isDebug()
	{
		return ManagementFactory.getRuntimeMXBean().getInputArguments().toString().indexOf("-agentlib:jdwp") > 0;
	}

	public static void info(Object msg)
	{
		logger.info("[WYPI] [" + ModValuesEnv.PROJECT_ID.toUpperCase() + "] " + String.valueOf(msg));
	}

	public static void warn(Object msg)
	{
		logger.warning("[WYPI] [" + ModValuesEnv.PROJECT_ID.toUpperCase() + "] " + String.valueOf(msg));
	}

	public static void error(Object msg)
	{
		logger.log(Level.SEVERE, "[WYPI] [" + ModValuesEnv.PROJECT_ID.toUpperCase() + "] " + String.valueOf(msg));
	}
	
	public static void debug(Object msg)
	{
		if(isDebug())
			logger.log(Level.INFO, "[WYPI] [" + ModValuesEnv.PROJECT_ID.toUpperCase() + "] " + String.valueOf(msg));
	}
}
