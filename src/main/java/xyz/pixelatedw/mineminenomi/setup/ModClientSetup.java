package xyz.pixelatedw.mineminenomi.setup;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import xyz.pixelatedw.mineminenomi.events.EventsCombatMode;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.init.ModKeybindings;
import xyz.pixelatedw.mineminenomi.init.ModRenderers;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.json.WyJSON;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModClientSetup
{
	
	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event)
	{
		//  Keybindings
		ModKeybindings.init();	
		
		// Static strings
		ModI18n.init();
		
		// Renderers
		ModRenderers.registerRenderers();
		
		MinecraftForge.EVENT_BUS.register(new EventsCombatMode());

		WyJSON.runGenerators(false);
	}
	
}