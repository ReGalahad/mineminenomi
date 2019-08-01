package xyz.pixelatedw.MineMineNoMi3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.Gson;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import xyz.pixelatedw.MineMineNoMi3.api.debug.WyDebug;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.misc.EntityDojoSensei;
import xyz.pixelatedw.MineMineNoMi3.items.AkumaNoMi;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;
import xyz.pixelatedw.MineMineNoMi3.quests.EnumQuestlines;

public class Values 
{
	public static List<AkumaNoMi> devilfruits = new ArrayList<AkumaNoMi>();
	public static List<AkumaNoMi> logias = new ArrayList<AkumaNoMi>();
	public static List<Item> miscItems = new ArrayList<Item>();
	public static List<Block> miscBlocks = new ArrayList<Block>();
	public static List<Object[]> customDFs = new ArrayList<Object[]>();
	
	public static final int MAX_DORIKI = 10000;
	public static final int MAX_ULTRACOLA = 10;
	public static final int MAX_GENERAL = 999999999;
	public static final long MAX_BOUNTY = 100000000000L;
	public static final int MAX_CREW = 50;
	public static final int MAX_ACTIVITIES = 4;
	
	// Network related stuff
	public static String urlConnection;
	public static HttpClient httpClient = HttpClientBuilder.create().build();
	public static Gson gson = new Gson();
	static
	{
		if (WyDebug.isDebug())
			urlConnection = "http://localhost/mmnm-webserver/api";
		else
			urlConnection = "http://pixelatedw.xyz/api";
	}
	
	public static String RESOURCES_FOLDER;
	
	public static Item[] KAIROSEKI_ITEMS = new Item[] {ListMisc.Kairoseki, ListMisc.KairosekiBullets, ListMisc.DenseKairoseki};
	
	public static HashMap<Class, EnumQuestlines> questGivers = createQuestGiversMap();

	private static HashMap<Class, EnumQuestlines> createQuestGiversMap()
	{
		HashMap<Class, EnumQuestlines> map = new HashMap<Class, EnumQuestlines>();
		
		map.put(EntityDojoSensei.class, EnumQuestlines.SWORDSMANPROGRESSION);
		
		return map;
	}
	
	public static HashMap<String, String[]> abilityWebAppExtraParams = new HashMap<String, String[]>();

}
