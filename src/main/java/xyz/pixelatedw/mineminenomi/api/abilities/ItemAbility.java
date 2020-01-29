package xyz.pixelatedw.mineminenomi.api.abilities;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.data.ability.AbilityDataCapability.Category;

public abstract class ItemAbility extends ContinuousAbility
{	
	public ItemAbility(String name, Category category)
	{
		super(name, category);
		
		this.onStartContinuityEvent = this::onStartContinuityEvent;
	}

	/*
	 *  Event Consumers
	 */
	private boolean onStartContinuityEvent(PlayerEntity player)
	{
		if (player.getHeldItemMainhand().isEmpty() && !this.getItemStack().isEmpty())
		{
			player.inventory.setInventorySlotContents(player.inventory.currentItem, this.getItemStack());
			return true;
		}
		else
		{
			if(this.getItemStack().isEmpty())
				WyHelper.sendMsgToPlayer(player, "Cannot equip " + this.getName() + " because it's an empty stack!");
			else
				WyHelper.sendMsgToPlayer(player, "Cannot equip " + this.getName() + " while holding another item in hand!");
			return false;
		}
	}
	
	/*
	 * 	Methods
	 */
	public abstract ItemStack getItemStack();
	public abstract boolean canBeActive(PlayerEntity player);
}
