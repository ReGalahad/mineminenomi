package xyz.pixelatedw.mineminenomi.items;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;

public enum MItemTier implements IItemTier {
	DORU(500, 8, 3f, 2,10)
	;
	
	private int maxUses;
	private float efficiency;
	private float attackDamage;
	private int harvestLevel;
	private int enchantability;
	private MItemTier(int maxUses, float efficiency, float attackDamage, int harvestLevel, int enchantability) {
		this.maxUses = maxUses;
		this.efficiency = efficiency;
		this.attackDamage = attackDamage;
		this.harvestLevel = harvestLevel;
		this.enchantability = enchantability;
	}

	@Override
	public int getMaxUses() {
		// TODO Auto-generated method stub
		return this.maxUses;
	}

	@Override
	public float getEfficiency() {
		// TODO Auto-generated method stub
		return this.efficiency;
	}

	@Override
	public float getAttackDamage() {
		// TODO Auto-generated method stub
		return this.attackDamage;
	}

	@Override
	public int getHarvestLevel() {
		// TODO Auto-generated method stub
		return this.harvestLevel;
	}

	@Override
	public int getEnchantability() {
		// TODO Auto-generated method stub
		return this.enchantability;
	}

	@Override
	public Ingredient getRepairMaterial() {
		// TODO Auto-generated method stub
		return null;
	}

}
