package xyz.pixelatedw.mineminenomi.setup;

import net.minecraft.world.storage.loot.functions.LootFunctionManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import xyz.pixelatedw.mineminenomi.Env;
import xyz.pixelatedw.mineminenomi.data.functions.RandomWantedPosterLootFunction;
import xyz.pixelatedw.mineminenomi.init.ModCapabilities;
import xyz.pixelatedw.mineminenomi.init.ModFeatures;

@Mod.EventBusSubscriber(modid = Env.PROJECT_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = { Dist.CLIENT, Dist.DEDICATED_SERVER })
public class ModCommonSetup
{
	@SubscribeEvent
	public static void commonSetup(FMLCommonSetupEvent event)
	{
		ModFeatures.init();
		LootFunctionManager.registerFunction(new RandomWantedPosterLootFunction.Serializer());
			
		ModCapabilities.init();
	}
}