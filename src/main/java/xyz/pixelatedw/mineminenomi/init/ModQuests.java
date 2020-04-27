package xyz.pixelatedw.mineminenomi.init;

import net.minecraftforge.fml.common.Mod;
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

	
	// Medic
	
	
	
	static
	{
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
	}
}
