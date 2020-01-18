package xyz.pixelatedw.mineminenomi.setup;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import xyz.pixelatedw.mineminenomi.Env;
import xyz.pixelatedw.mineminenomi.commands.AbilityProtectionCommand;
import xyz.pixelatedw.mineminenomi.commands.BellyCommand;
import xyz.pixelatedw.mineminenomi.commands.BountyCommand;
import xyz.pixelatedw.mineminenomi.commands.DorikiCommand;
import xyz.pixelatedw.mineminenomi.commands.ExtolCommand;
import xyz.pixelatedw.mineminenomi.commands.GetWantedPosterCommand;
import xyz.pixelatedw.mineminenomi.commands.IssueBountyCommand;
import xyz.pixelatedw.mineminenomi.commands.RemoveDFCommand;

@Mod.EventBusSubscriber(modid = Env.PROJECT_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = { Dist.CLIENT, Dist.DEDICATED_SERVER })
public class ForgeCommonSetup
{
	@SubscribeEvent
	public static void serverStarting(FMLServerStartingEvent event)
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