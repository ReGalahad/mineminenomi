package xyz.pixelatedw.mineminenomi;

import net.minecraft.world.storage.loot.functions.LootFunctionManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.functions.RandomWantedPosterLootFunction;
import xyz.pixelatedw.mineminenomi.init.ModCapabilities;
import xyz.pixelatedw.mineminenomi.init.ModFeatures;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.init.ModQuests;

@Mod.EventBusSubscriber(modid = Env.PROJECT_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = { Dist.CLIENT, Dist.DEDICATED_SERVER })
public class CommonSetup
{

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void commonSetup(FMLCommonSetupEvent event)
	{
		CommonConfig.init();
		ModNetwork.init();
		ModQuests.init();
		
		ModFeatures.init();
		LootFunctionManager.registerFunction(new RandomWantedPosterLootFunction.Serializer());
		
		ModCapabilities.init();
	}
	
}