package xyz.pixelatedw.mineminenomi.init;

import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;

public class ModEffects
{
	public static Enchantment dialImpact, dialFlash, kairoseki;

	public static void init()
	{		
		dialImpact = WyRegistry.registerEnchantment("Impact Dial");
		kairoseki  = WyRegistry.registerEnchantment("Kairoseki");
		dialFlash = WyRegistry.registerEnchantment("Flash Dial");
	}
	
	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class Registry
	{
		@SubscribeEvent
		public static void registerEntityTypes(RegistryEvent.Register<Enchantment> event)
		{
			if (!event.getName().equals(ForgeRegistries.ENCHANTMENTS.getRegistryName()))
				return;

			event.getRegistry().registerAll
			(
				ModEffects.dialImpact, 
				ModEffects.dialFlash, 
				ModEffects.kairoseki
			);
		}	
	}
}
