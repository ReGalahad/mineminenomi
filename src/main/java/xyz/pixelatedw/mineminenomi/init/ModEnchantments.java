package xyz.pixelatedw.mineminenomi.init;

import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;

public class ModEnchantments
{	
	public static final Enchantment DIAL_IMPACT = WyRegistry.registerEnchantment("Impact Dial");
	public static final Enchantment DIAL_FLASH = WyRegistry.registerEnchantment("Kairoseki");
	public static final Enchantment KAIROSEKI = WyRegistry.registerEnchantment("Flash Dial");
	
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
				DIAL_IMPACT, DIAL_FLASH,
				
				KAIROSEKI
			);
		}	
	}
}
