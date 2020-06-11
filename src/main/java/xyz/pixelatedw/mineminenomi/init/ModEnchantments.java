package xyz.pixelatedw.mineminenomi.init;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.api.GenericEnchantment;
import xyz.pixelatedw.wypi.WyRegistry;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEnchantments {
	public static final Enchantment DIAL_IMPACT = new GenericEnchantment(Enchantment.Rarity.COMMON, EquipmentSlotType.MAINHAND);
	public static final Enchantment DIAL_FLASH = new GenericEnchantment(Enchantment.Rarity.COMMON, EquipmentSlotType.MAINHAND);
	public static final Enchantment KAIROSEKI = new GenericEnchantment(Enchantment.Rarity.COMMON, EquipmentSlotType.MAINHAND);

	static {
		WyRegistry.registerEnchantment(DIAL_IMPACT, "Impact Dial");
		WyRegistry.registerEnchantment(DIAL_FLASH, "Flash Dial");
		WyRegistry.registerEnchantment(KAIROSEKI, "Kairoseki");
	}

}
