package xyz.pixelatedw.mineminenomi.api;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;

public enum AbilityItemTier implements IItemTier {
	DORU(500, 8, 3f, 2,10);
	
	private int maxUses;
	private float efficiency;
	private float attackDamage;
	private int harvestLevel;
	private int enchantability;
	private AbilityItemTier(int maxUses, float efficiency, float attackDamage, int harvestLevel, int enchantability) {
		this.maxUses = maxUses;
		this.efficiency = efficiency;
		this.attackDamage = attackDamage;
		this.harvestLevel = harvestLevel;
		this.enchantability = enchantability;
	}

	@Override
	public int getMaxUses() {
		return this.maxUses;
	}

	@Override
	public float getEfficiency() {
		return this.efficiency;
	}

	@Override
	public float getAttackDamage() {
		return this.attackDamage;
	}

	@Override
	public int getHarvestLevel() {
		return this.harvestLevel;
	}

	@Override
	public int getEnchantability() {
		return this.enchantability;
	}

	@Override
	public Ingredient getRepairMaterial() {
		return null;
	}

}
