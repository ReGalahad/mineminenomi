package xyz.pixelatedw.MineMineNoMi3.abilities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.api.math.ISphere;
import xyz.pixelatedw.MineMineNoMi3.api.math.Sphere;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.YukiProjectiles;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketParticles;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketPlayer;

public class YukiAbilities 
{

	public static Ability[] abilitiesArray = new Ability[] {new Kamakura(), new TabiraYuki(), new YukiRabi(), new KamakuraJussoshi(), new Fubuki()};	
		

	public static class TabiraYuki extends Ability
	{
		public TabiraYuki()
		{
			super(ListAttributes.TABIRAYUKI); 
		}
		
		public void startPassive(EntityPlayer player) 
		{
			if(player.inventory.getCurrentItem() == null)
				player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(ListMisc.TabiraYuki));
			else
			{
				WyHelper.sendMsgToPlayer(player, "Cannot equip " + this.getAttribute().getAttributeName() + " while holding another item in hand !");
				this.passive(player);
			}
		}
		
		public void endPassive(EntityPlayer player) 
		{
			player.inventory.clearInventory(ListMisc.TabiraYuki, -1);
		}
	}
	
	public static class Fubuki extends Ability
	{
		public Fubuki() 
		{
			super(ListAttributes.FUBUKI); 
		}
		
		public void use(final EntityPlayer player)
		{				
			if(!isOnCooldown)
			{
				for(EntityLivingBase e : WyHelper.getEntitiesNear(player, 25))
				{
					e.attackEntityFrom(DamageSource.causePlayerDamage(player), 8);
					e.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 200, 2));
					
					Sphere.generate((int)(int) player.posX, (int)(int) player.posY, (int)(int) player.posZ, 25, new ISphere()
				    { 
						public void call(int x, int y, int z)
						{
			    			for(int i = -4; i <= 4; i++)
					    		if(player.worldObj.isAirBlock(x, y, z) && player.worldObj.getBlock(x, y - 1, z) != Blocks.air && player.worldObj.getBlock(x, y - 1, z) != Blocks.snow_layer)
					    			player.worldObj.setBlock(x, y, z, Blocks.snow_layer);
						}
				    });
				}
				
				WyNetworkHelper.sendTo(new PacketParticles("fubuki", player.posX, player.posY, player.posZ), (EntityPlayerMP) player);
				super.use(player);
			}
		}
	}
	
	public static class KamakuraJussoshi extends Ability
	{
		public KamakuraJussoshi() 
		{
			super(ListAttributes.KAMAKURAJUSSOSHI); 
		}
		
		public void use(EntityPlayer player)
		{	
			if(!isOnCooldown)
			{
				
				MovingObjectPosition mop = WyHelper.rayTraceBlocks(player);
				
				if(mop != null)
				{
					WyHelper.createSphere(player, mop.blockX, mop.blockY, mop.blockZ, 4, Blocks.snow);
					WyHelper.createSphere(player, mop.blockX, mop.blockY, mop.blockZ, 6, Blocks.snow);
					WyHelper.createSphere(player, mop.blockX, mop.blockY, mop.blockZ, 8, Blocks.snow);
				}
				else
				{
					WyHelper.createSphere(player, 4, Blocks.snow);
					WyHelper.createSphere(player, 6, Blocks.snow);
					WyHelper.createSphere(player, 8, Blocks.snow);				
				}
				
				super.use(player);
			}
		}
	}
	
	public static class YukiRabi extends Ability
	{
		public YukiRabi() 
		{
			super(ListAttributes.YUKIRABI); 
		}
		
		public void use(EntityPlayer player)
		{	
			this.projectile = new YukiProjectiles.YukiRabi(player.worldObj, player, attr);
			super.use(player);
		}
	}
	
	public static class Kamakura extends Ability
	{
		public Kamakura() 
		{
			super(ListAttributes.KAMAKURA); 
		}
		
		public void use(EntityPlayer player)
		{	
			if(!isOnCooldown)
			{
				
				MovingObjectPosition mop = WyHelper.rayTraceBlocks(player);
				
				if(mop != null)
					WyHelper.createSphere(player, mop.blockX, mop.blockY, mop.blockZ, 4, Blocks.snow);
				else
					WyHelper.createSphere(player, 4, Blocks.snow);		

				super.use(player);
			}
		} 
	}
	
}
