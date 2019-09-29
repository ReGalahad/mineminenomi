package xyz.pixelatedw.MineMineNoMi3.items;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.telemetry.WyTelemetry;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;

public class ItemSakeBottle extends ItemBlock
{

	public ItemSakeBottle(Block block)
	{
		super(ListMisc.SakeBottle);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
	{
		if(itemStack.getTagCompound() == null)
		{
			itemStack.setTagCompound(new NBTTagCompound());
			itemStack.getTagCompound().setInteger("Amount", 5);
		}
		
		if(itemStack.getTagCompound() != null && itemStack.getTagCompound().getInteger("Amount") > 0 && itemStack.getTagCompound().getInteger("Timer") <= 0 && !world.isRemote)
		{
			player.setItemInUse(itemStack, 16);
			
			WyHelper.sendMsgToPlayer(player, "Stuff happens now too");

			if (!player.capabilities.isCreativeMode)
				WyTelemetry.addMiscStat("sakeBottlesDrank", "Sake Bottles Drank", 1);
			
			itemStack.getTagCompound().setInteger("Amount", itemStack.getTagCompound().getInteger("Amount") - 1);
			
			if(itemStack.getTagCompound().getInteger("Amount") <= 0)
				itemStack.setStackDisplayName("Empty Sake Bottle");
			
			itemStack.getTagCompound().setInteger("Timer", 50);
		}
		
		return itemStack;
	}

    @Override
	public void onUpdate(ItemStack itemStack, World world, Entity entity, int i, boolean b) 
    {
		if(itemStack.getTagCompound() == null)
			return;
		
		int timer = itemStack.getTagCompound().getInteger("Timer");

		if(timer > 0)
		{
			timer--;		
			itemStack.getTagCompound().setInteger("Timer", timer);
		}
    }

    @Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int posX, int posY, int posZ, int side, float hitX, float hitY, float hitZ)
    {
		if(itemStack.getTagCompound() == null)
		{
			itemStack.setTagCompound(new NBTTagCompound());
			itemStack.getTagCompound().setInteger("Amount", 5);
		}
		
    	if(itemStack.getTagCompound().getInteger("Amount") > 0)
    		return super.onItemUse(itemStack, player, world, posX, posY, posZ, side, hitX, hitY, hitZ);
    	else
    		return false;
    }
	
    @Override
	public EnumAction getItemUseAction(ItemStack p_77661_1_)
    {
        return EnumAction.eat;
    }

}
