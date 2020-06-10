package xyz.pixelatedw.mineminenomi.init;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.api.GenericEnchantment;
import xyz.pixelatedw.wypi.WyRegistry;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEnchantments {
	public static final Enchantment DIAL_IMPACT = new GenericEnchantment("Impact Dial", Enchantment.Rarity.COMMON, EquipmentSlotType.MAINHAND);
	public static final Enchantment DIAL_FLASH = new GenericEnchantment("Flash Dial", Enchantment.Rarity.COMMON, EquipmentSlotType.MAINHAND);
	public static final Enchantment KAIROSEKI = new GenericEnchantment("Kairoseki", Enchantment.Rarity.COMMON, EquipmentSlotType.MAINHAND);

	static {
		WyRegistry.registerEnchantment(DIAL_IMPACT, "Dial Impact");
		WyRegistry.registerEnchantment(DIAL_FLASH, "Dial Flash");
		WyRegistry.registerEnchantment(KAIROSEKI, "Kairoseki");
	}

}
