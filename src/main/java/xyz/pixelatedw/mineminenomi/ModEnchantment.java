package xyz.pixelatedw.mineminenomi;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import xyz.pixelatedw.mineminenomi.values.ModValuesEnv;

public class ModEnchantment extends Enchantment
{
    public ModEnchantment(String name, Enchantment.Rarity rarityIn, EquipmentSlotType... slots)
    {
        super(rarityIn, EnchantmentType.WEAPON, slots);
        this.setRegistryName(ModValuesEnv.PROJECT_ID, name);
    }
    
    @Override
	public int getMaxLevel()
    {
        return 1;
    }
}