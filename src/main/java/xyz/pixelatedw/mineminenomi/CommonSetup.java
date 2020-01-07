package xyz.pixelatedw.mineminenomi;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.world.storage.loot.functions.LootFunctionManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent;
import xyz.pixelatedw.mineminenomi.commands.AbilityProtectionCommand;
import xyz.pixelatedw.mineminenomi.commands.BellyCommand;
import xyz.pixelatedw.mineminenomi.commands.BountyCommand;
import xyz.pixelatedw.mineminenomi.commands.DorikiCommand;
import xyz.pixelatedw.mineminenomi.commands.ExtolCommand;
import xyz.pixelatedw.mineminenomi.commands.GetWantedPosterCommand;
import xyz.pixelatedw.mineminenomi.commands.IssueBountyCommand;
import xyz.pixelatedw.mineminenomi.commands.RemoveDFCommand;
import xyz.pixelatedw.mineminenomi.data.functions.RandomWantedPosterLootFunction;
import xyz.pixelatedw.mineminenomi.init.ModCapabilities;
import xyz.pixelatedw.mineminenomi.init.ModFeatures;

@Mod.EventBusSubscriber(modid = Env.PROJECT_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = { Dist.CLIENT, Dist.DEDICATED_SERVER })
public class CommonSetup
{

	@SubscribeEvent
	public static void commonSetup(FMLCommonSetupEvent event)
	{
		ModFeatures.init();
		LootFunctionManager.registerFunction(new RandomWantedPosterLootFunction.Serializer());
		
		ModCapabilities.init();
	}
	
	@SubscribeEvent
	public static void serverAboutToStart(FMLServerAboutToStartEvent event)
	{
		CommandDispatcher dispatcher = event.getServer().getCommandManager().getDispatcher();

		AbilityProtectionCommand.register(dispatcher);
		DorikiCommand.register(dispatcher);
		BountyCommand.register(dispatcher);
		BellyCommand.register(dispatcher);
		ExtolCommand.register(dispatcher);
		IssueBountyCommand.register(dispatcher);
		GetWantedPosterCommand.register(dispatcher);
		RemoveDFCommand.register(dispatcher);
	}
	
}