package xyz.pixelatedw.mineminenomi.init;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.api.GenericEnchantment;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEnchantments
{	
	public static final Enchantment DIAL_IMPACT = new GenericEnchantment("Impact Dial", Enchantment.Rarity.UNCOMMON, EquipmentSlotType.MAINHAND);
	public static final Enchantment DIAL_FLASH = new GenericEnchantment("Flash Dial", Enchantment.Rarity.UNCOMMON, EquipmentSlotType.MAINHAND);
	public static final Enchantment KAIROSEKI = new GenericEnchantment("Kairoseki", Enchantment.Rarity.UNCOMMON, EquipmentSlotType.MAINHAND);

	@SubscribeEvent
	public static void registerEnchantments(RegistryEvent.Register<Enchantment> event)
	{
		event.getRegistry().registerAll
		(
			DIAL_IMPACT, DIAL_FLASH,
			
			KAIROSEKI
		);
	}	
}
