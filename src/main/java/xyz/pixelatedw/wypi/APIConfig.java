package xyz.pixelatedw.wypi;

import xyz.pixelatedw.wypi.debug.WyDebug;

public class APIConfig
{
	/*
	 * Generic Mod Enviromental Constants
	 */

	public static final String PROJECT_ID = "mineminenomi";
	public static final String PROJECT_NAME = "Mine Mine no Mi";
	public static final String PROJECT_VERSION = "0.6.2";
	public static final String PROJECT_MINECRAFT_VERSION = "1.14.4";

	private static String projectResourceFolder;

	/*
	 * Mod Setup Methods
	 */

	public static void setupResourceFolderPath()
	{
		if (!WyDebug.isDebug())
			return;

		String basicPath = System.getProperty("user.dir");
		projectResourceFolder = basicPath.replace("/run", "") + "/src/main/resources";
	}

	public static String getResourceFolderPath()
	{
		return projectResourceFolder;
	}

	/*
	 * Generic API Enviromental Constants
	 */

	public static final String API_VERSION = "1.3.0";
	public static final BuildMode BUILD_MODE = BuildMode.DEV;

	// Build Types
	public static enum BuildMode
	{
		FINAL, DEV, EARLY_ACCESS
	}

	/*
	 * Ability Constants
	 */

	public static final int MAX_SELECTED_ABILITIES = 9;

	// Ability categories
	public static enum AbilityCategory
	{
		ALL,
		
		DEVIL_FRUIT,
		RACIAL,
		HAKI
	}

	
	/*
	 * Quest Constants
	 */

	public static final int MAX_IN_PROGRESS_QUESTS = 4;

}
