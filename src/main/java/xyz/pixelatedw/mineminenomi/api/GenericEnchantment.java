package xyz.pixelatedw.mineminenomi.api;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import xyz.pixelatedw.mineminenomi.Env;

public class GenericEnchantment extends Enchantment
{
    public GenericEnchantment(String name, Enchantment.Rarity rarityIn, EquipmentSlotType... slots)
    {
        super(rarityIn, EnchantmentType.WEAPON, slots);
        this.setRegistryName(Env.PROJECT_ID, name);
    }
    
    @Override
	public int getMaxLevel()
    {
        return 1;
    }
}