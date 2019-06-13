package xyz.pixelatedw.MineMineNoMi3.items.weapons;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.debug.WyDebug;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.WeatherProjectiles.WeatherBall;

public class ClimaTact extends Item
{

	public ClimaTact()
	{
		
	}
	
	
	public void emptyCharge(ItemStack itemStack)
	{
		if(!itemStack.hasTagCompound())
			itemStack.setTagCompound(new NBTTagCompound());
		
		itemStack.getTagCompound().setString("firstSlot", "");
		itemStack.getTagCompound().setString("secondSlot", "");
		itemStack.getTagCompound().setString("thirdSlot", "");
		
		WyDebug.debug("Empty Charge");
	}
	
	public String checkCharge(ItemStack itemStack)
	{
		if(!itemStack.hasTagCompound())
			itemStack.setTagCompound(new NBTTagCompound());
		
		StringBuilder sb = new StringBuilder();
		
		if(!WyHelper.isNullOrEmpty(itemStack.getTagCompound().getString("firstSlot")))
			sb.append(itemStack.getTagCompound().getString("firstSlot"));
		
		if(!WyHelper.isNullOrEmpty(itemStack.getTagCompound().getString("secondSlot")))
			sb.append(itemStack.getTagCompound().getString("secondSlot"));
		
		if(!WyHelper.isNullOrEmpty(itemStack.getTagCompound().getString("thirdSlot")))
			sb.append(itemStack.getTagCompound().getString("thirdSlot"));

		return sb.toString();
	}
	
	public void chargeWeatherBall(ItemStack itemStack, String ball)
	{
		if(!itemStack.hasTagCompound())
			itemStack.setTagCompound(new NBTTagCompound());
		
		if(WyHelper.isNullOrEmpty(itemStack.getTagCompound().getString("firstSlot")))
			itemStack.getTagCompound().setString("firstSlot", ball);
		
		else if(WyHelper.isNullOrEmpty(itemStack.getTagCompound().getString("secondSlot")))
			itemStack.getTagCompound().setString("secondSlot", ball);
		
		else if(WyHelper.isNullOrEmpty(itemStack.getTagCompound().getString("thirdSlot")))
			itemStack.getTagCompound().setString("thirdSlot", ball);	
	}
}
