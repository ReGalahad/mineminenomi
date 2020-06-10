package xyz.pixelatedw.mineminenomi.api;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;

public class GenericEnchantment extends Enchantment
{
    public GenericEnchantment(String name, Enchantment.Rarity rarityIn, EquipmentSlotType... slots)
    {
        super(rarityIn, EnchantmentType.WEAPON, slots);
    }
    
    @Override
	public int getMaxLevel()
    {
        return 1;
    }
}