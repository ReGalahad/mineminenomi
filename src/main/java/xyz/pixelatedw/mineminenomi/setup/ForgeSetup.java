package xyz.pixelatedw.mineminenomi.setup;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import xyz.pixelatedw.mineminenomi.commands.AbilityCommand;
import xyz.pixelatedw.mineminenomi.commands.AbilityProtectionCommand;
import xyz.pixelatedw.mineminenomi.commands.BellyCommand;
import xyz.pixelatedw.mineminenomi.commands.BountyCommand;
import xyz.pixelatedw.mineminenomi.commands.CheckFruitsCommand;
import xyz.pixelatedw.mineminenomi.commands.DorikiCommand;
import xyz.pixelatedw.mineminenomi.commands.ExtolCommand;
import xyz.pixelatedw.mineminenomi.commands.FGCommand;
import xyz.pixelatedw.mineminenomi.commands.GetWantedPosterCommand;
import xyz.pixelatedw.mineminenomi.commands.HakiExpCommand;
import xyz.pixelatedw.mineminenomi.commands.IssueBountyCommand;
import xyz.pixelatedw.mineminenomi.commands.PouchCommand;
import xyz.pixelatedw.mineminenomi.commands.RemoveDFCommand;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.APIConfig.BuildMode;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeSetup
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
		AbilityCommand.register(dispatcher);
		HakiExpCommand.register(dispatcher);
		PouchCommand.register(dispatcher);
		CheckFruitsCommand.register(dispatcher);
		//DamageMultiplierCommand.register(dispatcher);
		if(APIConfig.BUILD_MODE != BuildMode.FINAL)
			FGCommand.register(dispatcher);
	}
}