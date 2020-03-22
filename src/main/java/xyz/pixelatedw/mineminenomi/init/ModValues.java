package xyz.pixelatedw.mineminenomi.init;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
				ModItems.KAIROSEKI, ModItems.KAIROSEKI_BULLET, ModItems.DENSE_KAIROSEKI, ModBlocks.KAIROSEKI, ModBlocks.KAIROSEKI_ORE, ModBlocks.KAIROSEKI_BARS
		};
	
	// Network related stuff
	public static String urlConnection = "https://pixelatedw.xyz/api";
	public static Gson gson = new GsonBuilder()
            .disableHtmlEscaping()
            .setPrettyPrinting()
            .create();
	
	
	// Consts
	public static final int MAX_QUESTS = 4;
	public static final int MAX_DORIKI = 10000;
	public static final int MAX_ULTRACOLA = 10;
	public static final int MAX_GENERAL = 999999999;
	public static final int MAX_CREW = 50;
	
	public static final int CHARACTER_CREATOR = 0;
	public static final int WANTED_POSTER = 1;
	
	public static final long MAX_BOUNTY = 100000000000L;
	
	public static final String PIRATE = "pirate";
	public static final String MARINE = "marine";
	public static final String BOUNTY_HUNTER = "bounty_hunter";
	
	public static final String HUMAN = "human";
	public static final String FISHMAN = "fishman";
	public static final String CYBORG = "cyborg";
	
	public static final String SWORDSMAN = "swordsman";
	public static final String SNIPER = "sniper";
	public static final String DOCTOR = "doctor";
	public static final String ART_OF_WEATHER = "art_of_weather";	

}
