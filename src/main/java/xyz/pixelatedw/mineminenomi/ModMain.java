package xyz.pixelatedw.mineminenomi;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.world.storage.loot.functions.LootFunctionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
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
import xyz.pixelatedw.mineminenomi.events.EventsCore;
import xyz.pixelatedw.mineminenomi.events.EventsOnGain;
import xyz.pixelatedw.mineminenomi.events.abilities.common.EventsAbilities;
import xyz.pixelatedw.mineminenomi.events.abilities.common.EventsAbilityValidation;
import xyz.pixelatedw.mineminenomi.events.abilities.common.EventsDFWeaknesses;
import xyz.pixelatedw.mineminenomi.events.abilities.common.EventsSpecialFlying;
import xyz.pixelatedw.mineminenomi.events.abilities.common.EventsZoanPassives;
import xyz.pixelatedw.mineminenomi.init.ModCapabilities;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModFeatures;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.init.ModQuests;
import xyz.pixelatedw.mineminenomi.proxy.ClientProxy;
import xyz.pixelatedw.mineminenomi.proxy.IProxy;
import xyz.pixelatedw.mineminenomi.proxy.ServerProxy;
import xyz.pixelatedw.mineminenomi.values.ModValuesEnv;
import xyz.pixelatedw.mineminenomi.world.ModOreGenerator;

@Mod(ModValuesEnv.PROJECT_ID)
public class ModMain
{
	public static ModMain instance;
	public static IProxy proxy;
	public static final Logger LOGGER = LogManager.getLogger();
	
	public ModMain()
	{
		if(WyDebug.isDebug())
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
		
		FMLJavaModLoadingContext.get().getModEventBus().addListener(ModMain::commonSetup);	
		MinecraftForge.EVENT_BUS.addListener(this::serverAboutToStart);
	}
	
	private static void commonSetup(FMLCommonSetupEvent event)
	{
		ModOreGenerator.setupOreGen();
		LootFunctionManager.registerFunction(new RandomWantedPosterLootFunction.Serializer());
		
		MinecraftForge.EVENT_BUS.register(new ModCapabilities());
		ModCapabilities.init();
		
		// Handles some core features of the mod, update notifications or Early Access protection
		MinecraftForge.EVENT_BUS.register(new EventsCore());

		// Handles all the custom onGain events added by this mod
		MinecraftForge.EVENT_BUS.register(new EventsOnGain());
		
		// Core devil fruit events
		MinecraftForge.EVENT_BUS.register(new EventsAbilityValidation());
		MinecraftForge.EVENT_BUS.register(new EventsDFWeaknesses());
		MinecraftForge.EVENT_BUS.register(new EventsSpecialFlying());
		MinecraftForge.EVENT_BUS.register(new EventsZoanPassives());
		MinecraftForge.EVENT_BUS.register(new EventsAbilities());
		
		ModFeatures.init();
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
