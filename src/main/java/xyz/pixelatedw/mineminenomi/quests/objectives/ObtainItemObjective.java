package xyz.pixelatedw.mineminenomi.quests.objectives;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import xyz.pixelatedw.wypi.quests.objectives.IObtainItemObjective;
import xyz.pixelatedw.wypi.quests.objectives.Objective;

public class ObtainItemObjective extends Objective implements IObtainItemObjective
{
	private Item target;
	
	public ObtainItemObjective(String title, Item itemTarget, int count)
	{
		super(title);
		this.setMaxProgress(count);
		this.target = itemTarget;
	}

	@Override
	public boolean checkItem(ItemStack stack)
	{
		return stack.getItem() == this.target;
	}
}
