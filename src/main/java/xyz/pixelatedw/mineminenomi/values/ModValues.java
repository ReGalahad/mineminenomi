package xyz.pixelatedw.mineminenomi.values;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModItems;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;

public class ModValues
{
	// Arrays, Lists and HashMaps
	public static List<AkumaNoMiItem> devilfruits = new ArrayList();
	public static List<AkumaNoMiItem> logias = new ArrayList();
	public static List<Object[]> customDFs = new ArrayList();
	public static HashMap<String, String[]> abilityWebAppExtraParams = new HashMap<String, String[]>();
	public static Object[] KAIROSEKI_ITEMS = new Object[]
		{
				ModItems.kairoseki, ModItems.kairosekiBullets, ModItems.denseKairoseki, ModBlocks.kairosekiBlock, ModBlocks.kairosekiOre, ModBlocks.kairosekiBars
		};
	
	// Network related stuff
	public static String urlConnection = "https://pixelatedw.xyz/api";
	public static Gson gson = new GsonBuilder()
            .disableHtmlEscaping()
            .setPrettyPrinting()
            .create();
	
	// Variables	
	public static int
	
	MAX_QUESTS = 4;
	
	// Consts
	public static final int 
	
	MAX_DORIKI = 10000,
	MAX_ULTRACOLA = 10,
	MAX_GENERAL = 999999999,
	MAX_CREW = 50;
	
	public static final long 
	
	MAX_BOUNTY = 100000000000L;

}
