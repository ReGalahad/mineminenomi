package xyz.pixelatedw.mineminenomi.quests.swordsman.objectives;

import java.util.Map.Entry;

import com.google.common.collect.Multimap;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
import xyz.pixelatedw.wypi.quests.objectives.IObtainItemObjective;
import xyz.pixelatedw.wypi.quests.objectives.Objective;

public class StrongSwordObjective extends Objective implements IObtainItemObjective
{

	public StrongSwordObjective()
	{
		super("Find a sword that has over 7 damage");
	}

	@Override
	public boolean checkItem(ItemStack stack)
	{
		if(!ItemsHelper.isSword(stack))
			return false;
		
		Multimap<String, AttributeModifier> multimap = stack.getAttributeModifiers(EquipmentSlotType.MAINHAND);

		for(Entry<String, AttributeModifier> entry : multimap.entries())
		{
			AttributeModifier attr = entry.getValue();
			if (attr.getName().equalsIgnoreCase(SharedMonsterAttributes.ATTACK_DAMAGE.getName()))
			{
				double damage = attr.getAmount();
				if(damage >= 7)
				{
					return true;
				}
			}
		}
		
		return true;
	}

}
