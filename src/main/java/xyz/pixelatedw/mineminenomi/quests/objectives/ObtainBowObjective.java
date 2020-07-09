package xyz.pixelatedw.mineminenomi.quests.objectives;

import net.minecraft.item.ItemStack;
import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
import xyz.pixelatedw.wypi.quests.objectives.IObtainItemObjective;
import xyz.pixelatedw.wypi.quests.objectives.Objective;

public class ObtainBowObjective extends Objective implements IObtainItemObjective
{	
	public ObtainBowObjective()
	{
		super("Find a bow");
		this.setMaxProgress(1);
	}

	@Override
	public boolean checkItem(ItemStack stack)
	{
		return ItemsHelper.isBow(stack);
	}

}
