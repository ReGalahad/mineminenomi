package xyz.pixelatedw.MineMineNoMi3.items.devilfruitextras;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class WateringCan extends Item
{

    public ItemStack onEaten(ItemStack itemStack, World world, EntityPlayer player)
    {
		if (!world.isRemote)
			player.heal(4);
		
		return itemStack;
    }

    public int getMaxItemUseDuration(ItemStack p_77626_1_)
    {
        return 32;
    }
    
    public EnumAction getItemUseAction(ItemStack p_77661_1_)
    {
        return EnumAction.drink;
    }
    
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
        player.setItemInUse(itemStack, this.getMaxItemUseDuration(itemStack));
        return itemStack;
    }
    
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4)
	{
		list.add("Tears : 2");
	}
	
}
