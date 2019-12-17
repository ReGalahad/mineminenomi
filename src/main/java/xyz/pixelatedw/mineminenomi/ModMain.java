package xyz.pixelatedw.mineminenomi;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.world.storage.loot.functions.LootFunctionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent;
import xyz.pixelatedw.mineminenomi.api.debug.WyDebug;
import xyz.pixelatedw.mineminenomi.commands.AbilityProtectionCommand;
import xyz.pixelatedw.mineminenomi.commands.BellyCommand;
import xyz.pixelatedw.mineminenomi.commands.BountyCommand;
import xyz.pixelatedw.mineminenomi.commands.DorikiCommand;
import xyz.pixelatedw.mineminenomi.commands.ExtolCommand;
import xyz.pixelatedw.mineminenomi.commands.GetWantedPosterCommand;
import xyz.pixelatedw.mineminenomi.commands.IssueBountyCommand;
import xyz.pixelatedw.mineminenomi.commands.RemoveDFCommand;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.functions.RandomWantedPosterLootFunction;
import xyz.pixelatedw.mineminenomi.init.ModCapabilities;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModFeatures;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.init.ModQuests;
import xyz.pixelatedw.mineminenomi.proxy.ClientProxy;
import xyz.pixelatedw.mineminenomi.proxy.IProxy;
import xyz.pixelatedw.mineminenomi.proxy.ServerProxy;
import xyz.pixelatedw.mineminenomi.values.ModValuesEnv;

@Mod(ModValuesEnv.PROJECT_ID)
@Mod.EventBusSubscriber(modid = ModValuesEnv.PROJECT_ID, bus = Bus.MOD)
public class ModMain
{
	public static ModMain instance;
	public static IProxy proxy;
	public static final Logger LOGGER = LogManager.getLogger();

	public ModMain()
	{
		if (WyDebug.isDebug())
		{
			String basicPath = System.getProperty("java.class.path");
			ModValuesEnv.projectResourceFolder = basicPath.substring(0, basicPath.indexOf("\\bin")).replace("file:/", "").replace("%20", " ") + "/src/main/resources";
		}

		instance = this;
		proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());

		CommonConfig.init();
		ModNetwork.init();
		ModQuests.init();
		ModEffects.init();

		MinecraftForge.EVENT_BUS.addListener(this::serverAboutToStart);
	}

	@SubscribeEvent
	public static void commonSetup(final FMLCommonSetupEvent event)
	{
		ModFeatures.init();
		LootFunctionManager.registerFunction(new RandomWantedPosterLootFunction.Serializer());
		
		ModCapabilities.init();	
	}

	private void serverAboutToStart(FMLServerAboutToStartEvent event)
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
