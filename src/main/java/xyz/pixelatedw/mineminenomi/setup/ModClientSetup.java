package xyz.pixelatedw.mineminenomi.setup;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import xyz.pixelatedw.mineminenomi.Env;
import xyz.pixelatedw.mineminenomi.api.json.WyJSONHelper;
import xyz.pixelatedw.mineminenomi.events.EventsCombatMode;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.init.ModKeybindings;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.init.ModRenderers;
import xyz.pixelatedw.mineminenomi.particles.SimpleParticle;

@Mod.EventBusSubscriber(modid = Env.PROJECT_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
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
		
		WyJSONHelper.runGenerators(false);
	}

	@SubscribeEvent
	public static void registerParticleFactories(ParticleFactoryRegisterEvent event)
	{
		Minecraft.getInstance().particles.registerFactory(ModParticleTypes.GENERIC_PARTICLE, new SimpleParticle.Factory());
	}
	
}