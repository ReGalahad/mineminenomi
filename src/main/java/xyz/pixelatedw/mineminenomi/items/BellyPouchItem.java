package xyz.pixelatedw.mineminenomi.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
import xyz.pixelatedw.mineminenomi.init.ModValues;
import xyz.pixelatedw.wypi.WyHelper;

public class BellyPouchItem extends Item
{

	public BellyPouchItem()
	{
		super(new Properties().group(ModCreativeTabs.MISC).maxStackSize(1));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
    { 
		IEntityStats props = EntityStatsCapability.get(player);
		
		if(!world.isRemote)
		{
			int amount = (int) WyHelper.randomWithRange(5, 100);		

			if(props.getBelly() <= ModValues.MAX_GENERAL - amount)
			{
				props.alterBelly(amount);
				WyHelper.sendMsgToPlayer(player, "You've obtained " + amount + " belly !");
				player.inventory.deleteStack(player.getHeldItemMainhand());
			}
			else
				props.setBelly(ModValues.MAX_GENERAL);	

		}
				
		return new ActionResult<>(ActionResultType.SUCCESS, player.getHeldItem(hand));
	}
}
