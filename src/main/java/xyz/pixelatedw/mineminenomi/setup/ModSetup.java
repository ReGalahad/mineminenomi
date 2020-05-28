package xyz.pixelatedw.mineminenomi.setup;

import net.minecraft.client.Minecraft;
import net.minecraft.command.arguments.ArgumentSerializer;
import net.minecraft.command.arguments.ArgumentTypes;
import net.minecraft.item.IDyeableArmorItem;
import net.minecraft.world.storage.loot.functions.LootFunctionManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import xyz.pixelatedw.mineminenomi.api.AbilityArgument;
import xyz.pixelatedw.mineminenomi.data.functions.RandomWantedPosterFunction;
import xyz.pixelatedw.mineminenomi.data.functions.SetInfiniteStockFunction;
import xyz.pixelatedw.mineminenomi.data.functions.SetPriceFunction;
import xyz.pixelatedw.mineminenomi.init.ModArmors;
import xyz.pixelatedw.mineminenomi.init.ModCapabilities;
import xyz.pixelatedw.mineminenomi.init.ModFeatures;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.init.ModKeybindings;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.init.ModRenderers;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.json.WyJSON;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModSetup
{
	@SubscribeEvent
	public static void commonSetup(FMLCommonSetupEvent event)
	{
		ModCapabilities.init();
		
		ModNetwork.init();
		
		ModFeatures.init();
		LootFunctionManager.registerFunction(new RandomWantedPosterFunction.Serializer());
		LootFunctionManager.registerFunction(new SetPriceFunction.Serializer());
		LootFunctionManager.registerFunction(new SetInfiniteStockFunction.Serializer());
		
		ArgumentTypes.register("ability", AbilityArgument.class, new ArgumentSerializer<>(AbilityArgument::instance));
	}
	
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event)
	{
		//  Keybindings
		ModKeybindings.init();	
		
		// Static strings
		ModI18n.init();
				
		// Renderers
		ModRenderers.registerRenderers();

		WyJSON.runGenerators(false);
		
		// Registering mod items that can be dyed, must be registered client sided only
		Minecraft.getInstance().getItemColors().register((itemStack, i) -> {
	         return i > 0 ? -1 : ((IDyeableArmorItem)itemStack.getItem()).getColor(itemStack);
		}, ModArmors.CAPTAIN_CAPE);
		
		// Registering Containers and Screens together so when the container is opened the screen will also be shown
		// ScreenManager.registerFactory(ModContainers.TRADER, TraderScreen::new);
	}
}