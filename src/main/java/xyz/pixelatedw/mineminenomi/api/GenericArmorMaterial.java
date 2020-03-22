package xyz.pixelatedw.mineminenomi.api;

import java.util.function.Supplier;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyLoadBase;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class GenericArmorMaterial implements IArmorMaterial
{
	private static final int[] MAX_DAMAGE = new int[]
		{
				13, 15, 16, 11
		};
	private final String name;
	private final int maxDamageFactor;
	private final int[] damageReductionAmountArray;
	private final int enchantability;
	private final SoundEvent soundEvent;
	private final float toughness;
	private final LazyLoadBase<Ingredient> repairMaterial;

	public GenericArmorMaterial(String name, int maxDamageFactor, int[] damageReductionAmountArray, int enchantability, SoundEvent soundEvent, float toughness, Supplier<Ingredient> supplier)
	{
		this.name = name;
		this.maxDamageFactor = maxDamageFactor;
		this.damageReductionAmountArray = damageReductionAmountArray;
		this.enchantability = enchantability;
		this.soundEvent = soundEvent;
		this.toughness = toughness;
		this.repairMaterial = new LazyLoadBase<Ingredient>(supplier);
	}

	@Override
	public int getDurability(EquipmentSlotType slotIn)
	{
		return MAX_DAMAGE[slotIn.getIndex()] * this.maxDamageFactor;
	}

	@Override
	public int getDamageReductionAmount(EquipmentSlotType slotIn)
	{
		return this.damageReductionAmountArray[slotIn.getIndex()];
	}

	@Override
	public int getEnchantability()
	{
		return this.enchantability;
	}

	@Override
	public SoundEvent getSoundEvent()
	{
		return this.soundEvent;
	}

	@Override
	public Ingredient getRepairMaterial()
	{
		return this.repairMaterial.getValue();
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public String getName()
	{
		return this.name;
	}

	@Override
	public float getToughness()
	{
		return this.toughness;
	}
}
