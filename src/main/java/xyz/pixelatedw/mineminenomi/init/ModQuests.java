package xyz.pixelatedw.mineminenomi.init;

import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.quests.swordsman.SwordsmanTrial01;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyRegistry;
import xyz.pixelatedw.wypi.quests.Quest;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModQuests
{

	public static final Quest SWORDSMAN_TRIAL_01 = new SwordsmanTrial01();

	
	static
	{
		WyRegistry.registerQuest(SWORDSMAN_TRIAL_01);
	}
}
