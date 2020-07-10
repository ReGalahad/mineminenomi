package xyz.pixelatedw.mineminenomi.quests.objectives.swordsman;

import java.util.Map.Entry;
import java.util.UUID;

import com.google.common.collect.Multimap;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
import xyz.pixelatedw.wypi.quests.objectives.IObtainItemObjective;
import xyz.pixelatedw.wypi.quests.objectives.Objective;

public class ObtainStrongSwordObjective extends Objective implements IObtainItemObjective
{	
	public ObtainStrongSwordObjective()
	{
		super("Find a sword that has over 7 damage");
		this.setMaxProgress(1);
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

			if (attr.getID().equals(UUID.fromString("cb3f55d3-645c-4f38-a497-9c13a33db5cf")))
			{
				double damage = attr.getAmount();
				if(damage >= 7)
				{
					return true;
				}
			}
		}
		
		return false;
	}

}
