package xyz.pixelatedw.MineMineNoMi3.items.weapons;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.MainKeys;
import xyz.pixelatedw.MineMineNoMi3.api.EnumParticleTypes;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.AbilityProjectile;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.api.telemetry.WyTelemetry;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.ExtraProjectiles;
import xyz.pixelatedw.MineMineNoMi3.lists.ListExtraAttributes;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketParticles;

public class Flintlock extends Item
{

	
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
	{		
		if(!world.isRemote)
		{
			if(player.isSneaking())
			{
				if(itemStack.getTagCompound().getInteger("bulletType") == 0 && itemStack.getTagCompound().getInteger("bullets") == 0) 
				{
					itemStack.getTagCompound().setInteger("bulletType", 1);
					itemStack.setStackDisplayName("§rFlintlock <Kairoseki>");
				}
				else if(itemStack.getTagCompound().getInteger("bulletType") == 1 && itemStack.getTagCompound().getInteger("bullets") == 0) 
				{
					itemStack.getTagCompound().setInteger("bulletType", 0);
					itemStack.setStackDisplayName("§rFlintlock <Normal>");
				}
			}
			else
			{
				if(itemStack.getTagCompound().getBoolean("canUse"))
				{
					if(itemStack.getTagCompound().getInteger("bullets") > 0)
					{				
						int b = itemStack.getTagCompound().getInteger("bullets");
						
						AbilityProjectile proj = null;
						if(itemStack.getTagCompound().getInteger("bulletType") == 0) proj = new ExtraProjectiles.NormalBullet(player.worldObj, player, ListExtraAttributes.NORMALBULLET);
						else if(itemStack.getTagCompound().getInteger("bulletType") == 1) proj = new ExtraProjectiles.KairosekiBullet(player.worldObj, player, ListExtraAttributes.KAIROSEKIBULLET);
						player.worldObj.spawnEntityInWorld(proj);
												
			    		WyTelemetry.addStat( (itemStack.getTagCompound().getInteger("bulletType") == 0 ? "normal" : "kairoseki") + "BulletsShot", 1);
						
			    		itemStack.getTagCompound().setBoolean("canUse", false);
			    		
						b--;
						itemStack.getTagCompound().setInteger("bullets", b);
						itemStack.setStackDisplayName("§rFlintlock <" + (itemStack.getTagCompound().getInteger("bulletType") == 0 ? "Normal" : "Kairoseki") + "> <" + b + ">");
					}
					else
					{
						for(int i = 0; i < player.inventory.getSizeInventory(); i++)
						{
							if(player.inventory.getStackInSlot(i) != null && ((player.inventory.getStackInSlot(i).getItem() == ListMisc.Bullets && itemStack.getTagCompound().getInteger("bulletType") == 0)
									|| (player.inventory.getStackInSlot(i).getItem() == ListMisc.KairosekiBullets && itemStack.getTagCompound().getInteger("bulletType") == 1)))
							{
								ItemStack is = player.inventory.getStackInSlot(i);
								if(is.stackSize > 16)
								{
									itemStack.getTagCompound().setInteger("bullets", 16);
									player.inventory.decrStackSize(i, 16);
									((EntityPlayerMP)player).sendContainerToPlayer(player.inventoryContainer);
									itemStack.setStackDisplayName("§rFlintlock <" + (itemStack.getTagCompound().getInteger("bulletType") == 0 ? "Normal" : "Kairoseki") + "> <" + 16 + ">");
								}
								else
								{
									itemStack.getTagCompound().setInteger("bullets", is.stackSize);
									WyHelper.removeStackFromInventory(player, is);
									((EntityPlayerMP)player).sendContainerToPlayer(player.inventoryContainer);
									itemStack.setStackDisplayName("§rFlintlock <" + (itemStack.getTagCompound().getInteger("bulletType") == 0 ? "Normal" : "Kairoseki") + "> <" + is.stackSize + ">");
								}
							}
						}
					}
				}
			}
		}
		return itemStack;
	}
	
	public void onUpdate(ItemStack itemStack, World world, Entity entity, int par4, boolean par5) 
	{
		if(!itemStack.hasTagCompound())
		{
			itemStack.setTagCompound(new NBTTagCompound());
			itemStack.getTagCompound().setInteger("bulletType", 0); // 0 - normal bullets; 1 - kairoseki bullets
			itemStack.getTagCompound().setInteger("bullets", 0);
			itemStack.getTagCompound().setBoolean("canUse", true);
			itemStack.getTagCompound().setInteger("cooldown", 15);
		}
		
		if(!itemStack.getTagCompound().getBoolean("canUse"))
		{
			int cd = itemStack.getTagCompound().getInteger("cooldown");
			if(cd > 0)
			{
				cd--;
				itemStack.getTagCompound().setInteger("cooldown", cd);
			}
			else
			{
				itemStack.getTagCompound().setInteger("cooldown", 15);
				itemStack.getTagCompound().setBoolean("canUse", true);
			}
		}
	}
	
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4)
	{
		if(itemStack.hasTagCompound())
			list.add("Bullets : " + itemStack.getTagCompound().getInteger("bullets"));
	}
	
}
