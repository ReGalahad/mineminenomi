package xyz.pixelatedw.mineminenomi.quests.objectives;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;

public class ObtainEnchantedItemObjective extends ObtainItemObjective
{
	private Enchantment target;
	private int targetLevel;
	
	public ObtainEnchantedItemObjective(String title, Enchantment target, int count)
	{
		this(title, target, 0, count);
	}
	
	public ObtainEnchantedItemObjective(String title, Enchantment target, int level, int count)
	{
		super(title, null, count);
		this.target = target;
		this.targetLevel = level;
	}
	
	@Override
	public boolean checkItem(ItemStack stack)
	{
		return EnchantmentHelper.getEnchantmentLevel(target, stack) > this.targetLevel;
	}
}
