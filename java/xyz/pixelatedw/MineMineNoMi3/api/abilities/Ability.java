package xyz.pixelatedw.MineMineNoMi3.api.abilities;

import java.lang.reflect.InvocationTargetException;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.DevilFruitsHelper;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability.Update;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.api.telemetry.WyTelemetry;
import xyz.pixelatedw.MineMineNoMi3.ieep.ExtendedEntityStats;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketPlayer;

public class Ability 
{
	
	protected AbilityProjectile projectile;
	protected AbilityAttribute attr;
	protected boolean isOnCooldown = false, isCharging = false, isRepeating = false, passiveActive = false, isDisabled = false;
	private int ticksForCooldown, ticksForCharge, ticksForRepeater;
	
	private int dummy_projectileNumber = 1;
	
	public Ability(AbilityAttribute attr)
	{
		this.attr = new AbilityAttribute(attr);
		ticksForCooldown = this.attr.getAbilityCooldown();
		ticksForCharge = this.attr.getAbilityCharges();
		ticksForRepeater = this.attr.getAbilityCooldown();
	}

	public AbilityAttribute getAttribute() { return attr; }
	
	public void use(EntityPlayer player)
	{		
		System.out.println(this.attr);
		if(!isOnCooldown)
		{
			if(projectile != null)
			{
				if(this.attr.isRepeater())
					startRepeater(player);
				else
					player.worldObj.spawnEntityInWorld(projectile);
			}
			
			if(this.attr.getPotionEffectsForUser() != null)
				for(PotionEffect p : this.attr.getPotionEffectsForUser())				
					player.addPotionEffect(new PotionEffect(p));
					 
			if(this.attr.getPotionEffectsForAoE() != null) 
				for(PotionEffect p : this.attr.getPotionEffectsForAoE())
					for(EntityLivingBase l : WyHelper.getEntitiesNear(player, this.attr.getEffectRadius())) 
						l.addPotionEffect(new PotionEffect(p));
	
			if(!(this.attr.getAbilityCharges() > 0) && this.attr.getAbilityExplosionPower() > 0)
				player.worldObj.newExplosion(player, player.posX, player.posY, player.posZ, this.attr.getAbilityExplosionPower(), this.attr.canAbilityExplosionSetFire(), this.attr.canAbilityExplosionDestroyBlocks());		
			
			if(!player.getDisplayName().equals(FMLCommonHandler.instance().getMinecraftServerInstance().getServerOwner()))	
				WyNetworkHelper.sendTo(new PacketPlayer("clientUpdateIsCooldown" + this.getAttribute().getAttributeName(), true), (EntityPlayerMP) player);

	    	if(!ID.DEV_EARLYACCESS && !player.capabilities.isCreativeMode)
	    		WyTelemetry.addStat("abilityUsed_" + WyHelper.getFancyName(this.getAttribute().getAttributeName()), 1);
	    	
	    	ExtendedEntityStats props = ExtendedEntityStats.get(player);
	    	props.setTempPreviousAbility(WyHelper.getFancyName(this.attr.getAttributeName()));
	    	
	    	duringRepeater(player);
			startCooldown();
			(new Update(player, attr)).start();
		}
	}
	
	public void duringRepeater(EntityPlayer player)
	{
		if(isRepeating)
		{
			try 
			{
				player.worldObj.spawnEntityInWorld(projectile.getClass().getDeclaredConstructor(World.class, EntityLivingBase.class, AbilityAttribute.class).newInstance(player.worldObj, player, attr));
				System.out.println(" i = " + dummy_projectileNumber);
				dummy_projectileNumber++;
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	protected void startRepeater(EntityPlayer player)
	{
		this.isRepeating = true;
	}
	
	public boolean isRepeating()
	{
		return this.isRepeating;
	}
	
	public void passive(EntityPlayer player)
	{
		if(!isOnCooldown)
		{
			if(passiveActive)
			{
				passiveActive = false;
				if(!player.getDisplayName().equals(FMLCommonHandler.instance().getMinecraftServerInstance().getServerOwner()))
					WyNetworkHelper.sendTo(new PacketPlayer("clientUpdateIsPassive" + this.getAttribute().getAttributeName(), false), (EntityPlayerMP) player);
				if(this.attr.getPotionEffectsForUser() != null)
					for(PotionEffect p : this.attr.getPotionEffectsForUser())	
						player.removePotionEffect(p.getPotionID());
				
				endPassive(player);
			}
			else
			{
				passiveActive = true;
				if(!player.getDisplayName().equals(FMLCommonHandler.instance().getMinecraftServerInstance().getServerOwner()))
					WyNetworkHelper.sendTo(new PacketPlayer("clientUpdateIsPassive" + this.getAttribute().getAttributeName(), true), (EntityPlayerMP) player);
				if(this.attr.getPotionEffectsForUser() != null)
					for(PotionEffect p : this.attr.getPotionEffectsForUser())				
						player.addPotionEffect(new PotionEffect(p.getPotionID(), Integer.MAX_VALUE, p.getAmplifier(), true));
				
				startPassive(player);
				(new Update(player, attr)).start();
			}			
		}
	}
	
	public boolean isDisabled()
	{
		return isDisabled;
	}
	
	public void disable(EntityPlayer player, boolean bool) 
	{
		if(bool)
			(new ResetDisable(player, attr)).start();
		isDisabled = bool;
	}
	
	public void endPassive(EntityPlayer player) {}
	
	public void startPassive(EntityPlayer player) {}
		
	public void duringPassive(EntityPlayer player, int passiveTimer) {}
	
	public boolean isPassiveActive()
	{
		return this.passiveActive;
	}

	public void setPassiveActive(boolean b)
	{
		this.passiveActive = b;
	}
	
	public void setChargeActive(boolean b)
	{
		this.isCharging = b;
	}
	
	public void setCooldownActive(boolean b)
	{
		this.isOnCooldown = b;
	}
	
	
	
	public void duringCharging(EntityPlayer player, int currentCharge)
	{

	}
	
	public void startCharging(EntityPlayer player)
	{
		if(!isOnCooldown)
		{
			isCharging = true;
	    	if(!ID.DEV_EARLYACCESS && !player.capabilities.isCreativeMode)
	    		WyTelemetry.addStat("abilityUsed_" + WyHelper.getFancyName(this.getAttribute().getAttributeName()), 1);
			if(!player.getDisplayName().equals(FMLCommonHandler.instance().getMinecraftServerInstance().getServerOwner()))
				WyNetworkHelper.sendTo(new PacketPlayer("clientUpdateIsCharging" + this.getAttribute().getAttributeName(), true), (EntityPlayerMP) player);
			(new Update(player, attr)).start();
		}
	}
	
	public void endCharging(EntityPlayer player)
	{
		isCharging = false;
		isOnCooldown = true;
		
		if(projectile != null)
			player.worldObj.spawnEntityInWorld(projectile);
		
		if(!player.getDisplayName().equals(FMLCommonHandler.instance().getMinecraftServerInstance().getServerOwner()))
		{
			WyNetworkHelper.sendTo(new PacketPlayer("clientUpdateIsCharging" + this.getAttribute().getAttributeName(), false), (EntityPlayerMP) player);
			WyNetworkHelper.sendTo(new PacketPlayer("clientUpdateIsCooldown" + this.getAttribute().getAttributeName(), true), (EntityPlayerMP) player);
		}
		
		startCooldown();
		(new Update(player, attr)).start();
	}
	
	public boolean isCharging()
	{
		return isCharging;
	}
	
	public boolean isOnCooldown()
	{
		return isOnCooldown;
	}
	
	public void duringCooldown(EntityPlayer player, int currentCooldown) {}
	
	public void hitEntity(EntityPlayer player, EntityLivingBase target) 
	{
		if(this.attr.getPotionEffectsForProjectile() != null)
			for(PotionEffect p : this.attr.getPotionEffectsForProjectile())				
				target.addPotionEffect(new PotionEffect(p.getPotionID(), p.getDuration(), p.getAmplifier(), true));
		
		passiveActive = false;
		startCooldown();
		(new Update(player, attr)).start();
	}
	
	protected void startCooldown()
	{
		isOnCooldown = true;
	}
	
	protected void startExtUpdate(EntityPlayer player)
	{
		(new Update(player, attr)).start();
	}
	
	public void reset()
	{
		isOnCooldown = false;
		isCharging = false;
		isRepeating = false;
		passiveActive = false;			
	}
	
	class ResetDisable extends Thread
	{
		private EntityPlayer player;
		private ExtendedEntityStats props;
		private AbilityAttribute attr;
		
		public ResetDisable(EntityPlayer user, AbilityAttribute attribute)
		{
			this.player = user;
			this.props = ExtendedEntityStats.get(player);
			this.attr = attribute;
			this.setName("ResetThread Thread for " + attr.getAttributeName());
		}
		
		public void run()
		{
			while(isDisabled)
			{
				if( (!player.isInsideOfMaterial(Material.water) && !player.isWet() && player.worldObj.getBlock((int) player.posX, (int) player.posY - 1, (int) player.posZ) != Blocks.water 
						&& player.worldObj.getBlock((int) player.posX, (int) player.posY - 1, (int) player.posZ) != Blocks.flowing_water 
						&& !WyHelper.isBlockNearby(player, 3, ListMisc.KairosekiBlock, ListMisc.KairosekiOre) && !DevilFruitsHelper.hasKairosekiItem(player) && !player.inventory.hasItem( Item.getItemFromBlock(ListMisc.KairosekiBlock))
						&& !player.inventory.hasItem( Item.getItemFromBlock(ListMisc.KairosekiOre)))  )
				{
			    	disable(player, false);
					setCooldownActive(false);
					try
					{
						this.join();
					} 
					catch (Exception e) 
					{
						e.printStackTrace();
					}
				}
				try 
				{
					Thread.sleep(24);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		}
		
		private boolean abilityCounterpart(String ablNameForCheck)
		{
			return WyHelper.getFancyName(this.attr.getAttributeName()).equals(WyHelper.getFancyName(ablNameForCheck));
		}
	}
	
	
	class Update extends Thread
	{
		private EntityPlayer player;
		private AbilityAttribute attr;
		
		public Update(EntityPlayer user, AbilityAttribute attribute)
		{
			this.player = user;
			this.attr = attribute;
			this.setName("Update Thread for " + attr.getAttributeName());
		}
		
		public void run()
		{
			if(passiveActive)
			{
				int passiveTimer = 0;
				while(passiveActive)
				{
					duringPassive(player, passiveTimer++/24);
					try 
					{
						Thread.sleep(24);
					} 
					catch (InterruptedException e) 
					{
						e.printStackTrace();
					}
				}
			}
			
			if(isOnCooldown)
			{
				while(isOnCooldown)
				{
					if(ticksForCooldown > 0)
					{
						ticksForCooldown--;
						if(isRepeating)
						{
							ticksForRepeater--;
							if(ticksForRepeater > this.attr.getAbilityCooldown() - (this.attr.getAbilityCooldown() / this.attr.getAbilityRepeaterFrequency()) && projectile != null)
							{
								
							}
							else
							{
								isRepeating = false;
								dummy_projectileNumber = 1;
								ticksForRepeater = attr.getAbilityCooldown();
							}
						}
						duringCooldown(player, ticksForCooldown);
						try 
						{
							Thread.sleep(24);
						} 
						catch (InterruptedException e) 
						{
							e.printStackTrace();
						}
					}
					else
					{
						ticksForCooldown = this.attr.getAbilityCooldown();
						isOnCooldown = false;
						if(!player.getDisplayName().equals(FMLCommonHandler.instance().getMinecraftServerInstance().getServerOwner()))
							WyNetworkHelper.sendTo(new PacketPlayer("clientUpdateIsCooldown" + attr.getAttributeName(), false), (EntityPlayerMP) player);
						try 
						{
							this.join();
						} 
						catch (InterruptedException e) 
						{
							e.printStackTrace();
						}
					}
				}	
			}
			else if(isCharging)
			{
				while(isCharging)
				{
					if(ticksForCharge > 0)
					{
						ticksForCharge--;	
						duringCharging(player, ticksForCharge);
						try 
						{
							Thread.sleep(24);
						} 
						catch (InterruptedException e) 
						{
							e.printStackTrace();
						}
					}
					else
					{
						ticksForCharge = this.attr.getAbilityCharges();
						endCharging(player);
					}
				}
			}
		}
	}
}
