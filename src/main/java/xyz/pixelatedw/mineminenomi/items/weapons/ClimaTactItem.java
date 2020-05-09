package xyz.pixelatedw.mineminenomi.items.weapons;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
import xyz.pixelatedw.wypi.WyHelper;

public class ClimaTactItem extends Item
{
	private int damage = 1;
	private int level = 1;
	
	public ClimaTactItem(int damage, int level)
	{
		super(new Properties().group(ModCreativeTabs.WEAPONS).maxStackSize(1));
		this.damage = damage;
		this.level = level;
	}
	
	public String checkCharge(ItemStack itemStack)
	{
		StringBuilder sb = new StringBuilder();
		
		if(!WyHelper.isNullOrEmpty(itemStack.getOrCreateTag().getString("firstSlot")))
			sb.append(itemStack.getOrCreateTag().getString("firstSlot"));
		
		if(!WyHelper.isNullOrEmpty(itemStack.getOrCreateTag().getString("secondSlot")))
			sb.append(itemStack.getOrCreateTag().getString("secondSlot"));
		
		if(!WyHelper.isNullOrEmpty(itemStack.getOrCreateTag().getString("thirdSlot")))
			sb.append(itemStack.getOrCreateTag().getString("thirdSlot"));

		return sb.toString();
	}
	
	public void chargeWeatherBall(ItemStack itemStack, String ball)
	{
		if(WyHelper.isNullOrEmpty(itemStack.getOrCreateTag().getString("firstSlot")))
			itemStack.getOrCreateTag().putString("firstSlot", ball);	
		else if(WyHelper.isNullOrEmpty(itemStack.getOrCreateTag().getString("secondSlot")))
			itemStack.getOrCreateTag().putString("secondSlot", ball);	
		else if(WyHelper.isNullOrEmpty(itemStack.getOrCreateTag().getString("thirdSlot")))
			itemStack.getOrCreateTag().putString("thirdSlot", ball);	
	}
	
	public void emptyCharge(ItemStack itemStack)
	{
		itemStack.getOrCreateTag().putString("firstSlot", "");
		itemStack.getOrCreateTag().putString("secondSlot", "");
		itemStack.getOrCreateTag().putString("thirdSlot", "");
	}
	
	public void setDamageModifier(ItemStack stack, double multiplier)
	{
		stack.getOrCreateTag().putDouble("multiplier", multiplier);
	}
	
	public int getLevel()
	{
		return this.level;
	}
	
	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot, ItemStack stack)
	{
		Multimap<String, AttributeModifier> multimap = HashMultimap.create();

		if(equipmentSlot == EquipmentSlotType.MAINHAND)
		{
			double multiplier = stack.getOrCreateTag().getDouble("multiplier");
			if(multiplier <= 0)
				multiplier = 1;
			multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", this.damage * multiplier, Operation.ADDITION));		
		}
		
		return multimap;
	}
}
