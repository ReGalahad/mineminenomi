package xyz.pixelatedw.MineMineNoMi3;

import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class MainConfig 
{
	
	private static Configuration config;
	
	public static String enableKeepIEEPAfterDeath;
	
	public static boolean enableShips;
	public static boolean enableCamps;
	public static boolean enableDFtoDrop;
	public static boolean enableLogiaInvulnerability;	
	public static boolean enableExtraHearts;
	public static boolean enableTelemetry;
	public static boolean enableUpdateMsg;
	public static boolean enableFOVModifier;
	
	public static double rateDFDrops;
	public static double rateShipsSpawn;
	
	public static void init(File configFile)
	{
		config = new Configuration(configFile);
		
		config.load();
		
		enableKeepIEEPAfterDeath = config.get(Configuration.CATEGORY_GENERAL, "Keep stats after death", "auto").getString();
		
		enableShips = config.get(Configuration.CATEGORY_GENERAL, "Allow Ships to Spawn", true).getBoolean();
		rateShipsSpawn = config.get(Configuration.CATEGORY_GENERAL, "Modifier for Spawning Ships", 1).getDouble();
		//enableCamps = config.get(Configuration.CATEGORY_GENERAL, "Allow Camps to Spawn", true).getBoolean();

		enableDFtoDrop = config.get(Configuration.CATEGORY_GENERAL, "Allow Devil Fruits to drop from leaves", false).getBoolean();
		rateDFDrops = config.get(Configuration.CATEGORY_GENERAL, "Rate at which Devil Fruits drop from leaves", 1).getDouble();

		enableLogiaInvulnerability = config.get(Configuration.CATEGORY_GENERAL, "Allow Logia Invulnerability", true).getBoolean();
		enableExtraHearts = config.get(Configuration.CATEGORY_GENERAL, "Receive Extra Hearts", true).getBoolean();
		
		enableTelemetry = config.get("system", "Allow Telemetry", true).getBoolean();
		enableUpdateMsg = config.get("system", "Allow Update Message", true).getBoolean();	
		enableFOVModifier = config.get("system", "Allow FOV Modifiers", false).getBoolean();
		
		
		
		config.save();
	}
	 
	public static Configuration getConfig() { return config; }

}
