package xyz.pixelatedw.mineminenomi.init;

import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.quests.artofweather.ArtOfWeatherTrial01Quest;
import xyz.pixelatedw.mineminenomi.quests.artofweather.ArtOfWeatherTrial02Quest;
import xyz.pixelatedw.mineminenomi.quests.artofweather.ArtOfWeatherTrial03Quest;
import xyz.pixelatedw.mineminenomi.quests.artofweather.ArtOfWeatherTrial04Quest;
import xyz.pixelatedw.mineminenomi.quests.doctor.DoctorTrial01Quest;
import xyz.pixelatedw.mineminenomi.quests.doctor.DoctorTrial02Quest;
import xyz.pixelatedw.mineminenomi.quests.doctor.DoctorTrial03Quest;
import xyz.pixelatedw.mineminenomi.quests.sniper.SniperTrial01Quest;
import xyz.pixelatedw.mineminenomi.quests.sniper.SniperTrial02Quest;
import xyz.pixelatedw.mineminenomi.quests.sniper.SniperTrial03Quest;
import xyz.pixelatedw.mineminenomi.quests.sniper.SniperTrial04Quest;
import xyz.pixelatedw.mineminenomi.quests.swordsman.SwordsmanTrial01Quest;
import xyz.pixelatedw.mineminenomi.quests.swordsman.SwordsmanTrial02Quest;
import xyz.pixelatedw.mineminenomi.quests.swordsman.SwordsmanTrial03Quest;
import xyz.pixelatedw.mineminenomi.quests.swordsman.SwordsmanTrial04Quest;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyRegistry;
import xyz.pixelatedw.wypi.debug.WyDebug;
import xyz.pixelatedw.wypi.quests.Quest;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModQuests
{
	// Swordsman
	public static final Quest SWORDSMAN_TRIAL_01 = new SwordsmanTrial01Quest();
	public static final Quest SWORDSMAN_TRIAL_02 = new SwordsmanTrial02Quest();
	public static final Quest SWORDSMAN_TRIAL_03 = new SwordsmanTrial03Quest();
	public static final Quest SWORDSMAN_TRIAL_04 = new SwordsmanTrial04Quest();

	// Sniper
	public static final Quest SNIPER_TRIAL_01 = new SniperTrial01Quest();
	public static final Quest SNIPER_TRIAL_02 = new SniperTrial02Quest();
	public static final Quest SNIPER_TRIAL_03 = new SniperTrial03Quest();
	public static final Quest SNIPER_TRIAL_04 = new SniperTrial04Quest();

	// Doctor
	public static final Quest DOCTOR_TRIAL_01 = new DoctorTrial01Quest();
	public static final Quest DOCTOR_TRIAL_02 = new DoctorTrial02Quest();
	public static final Quest DOCTOR_TRIAL_03 = new DoctorTrial03Quest();

	// Art of Weather
	public static final Quest ART_OF_WEATHER_TRIAL_01 = new ArtOfWeatherTrial01Quest();
	public static final Quest ART_OF_WEATHER_TRIAL_02 = new ArtOfWeatherTrial02Quest();
	public static final Quest ART_OF_WEATHER_TRIAL_03 = new ArtOfWeatherTrial03Quest();
	public static final Quest ART_OF_WEATHER_TRIAL_04 = new ArtOfWeatherTrial04Quest();
	
	static
	{
		if(WyDebug.isDebug())
		{
			// Debug Quests
			
		}
		
		// Swordsman
		WyRegistry.registerQuest(SWORDSMAN_TRIAL_01);
		WyRegistry.registerQuest(SWORDSMAN_TRIAL_02);
		WyRegistry.registerQuest(SWORDSMAN_TRIAL_03);
		WyRegistry.registerQuest(SWORDSMAN_TRIAL_04);
		
		// Sniper
		WyRegistry.registerQuest(SNIPER_TRIAL_01);
		WyRegistry.registerQuest(SNIPER_TRIAL_02);
		WyRegistry.registerQuest(SNIPER_TRIAL_03);
		WyRegistry.registerQuest(SNIPER_TRIAL_04);
		
		// Doctor
		WyRegistry.registerQuest(DOCTOR_TRIAL_01);
		WyRegistry.registerQuest(DOCTOR_TRIAL_02);
		WyRegistry.registerQuest(DOCTOR_TRIAL_03);
		
		// Art of Weather
		WyRegistry.registerQuest(ART_OF_WEATHER_TRIAL_01);
		WyRegistry.registerQuest(ART_OF_WEATHER_TRIAL_02);
		WyRegistry.registerQuest(ART_OF_WEATHER_TRIAL_03);
		WyRegistry.registerQuest(ART_OF_WEATHER_TRIAL_04);
	}
}
