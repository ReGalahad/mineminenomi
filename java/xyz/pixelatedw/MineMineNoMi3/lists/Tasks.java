package xyz.pixelatedw.MineMineNoMi3.lists;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import xyz.pixelatedw.MineMineNoMi3.DevilFruitsHelper;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper.Direction;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.AbilityAttribute;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.AbilityProjectile;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.AbilityTask;
import xyz.pixelatedw.MineMineNoMi3.api.math.ISphere;
import xyz.pixelatedw.MineMineNoMi3.api.math.Sphere;
import xyz.pixelatedw.MineMineNoMi3.ieep.ExtendedEntityStats;

public class Tasks 
{
/*
	public static AbilityAttribute requestNewChargedAttribute(AbilityAttribute aa, EntityPlayer player, int timeLeft)
	{	
		int power = (timeLeft - aa.getItemMaxCharges()) * -1;
		
		if(power == 0) power = aa.getItemMaxCharges();
		
		int newDamage = aa.getProjectileDamage() + power;
		int newExplosion;
		double newSizeX, newSizeY, newSizeZ;
		boolean newHasFire = aa.canExplosionSetFire(), newHasSmoke = aa.canExplosionDestroyBlocks();
		
		if(aa.getProjectileExplosionPower() > 0) newExplosion = aa.getProjectileExplosionPower() + (power / 3);
		else newExplosion = 0;

		AbilityAttribute newAA = new AbilityAttribute(aa.getAttributeName()).setProjectileModel(aa.getProjectileModel()).setProjectileColor(aa.getProjectileColor()).setProjectileDamage(newDamage).setProjectileExplosion(newExplosion, newHasFire, newHasSmoke);	
		return newAA;
	}*/
	
	public static AbilityTask changeItemForm = new AbilityTask()
	{
		public void onItemUse(ItemStack itemStack, EntityPlayer player)
		{
			if(!player.worldObj.isRemote)
			{
				if(itemStack.getTagCompound().getInteger("form") == 0)
					itemStack.getTagCompound().setInteger("form", 1);
				else
					itemStack.getTagCompound().setInteger("form", 0);
			}
		}
	};
	
	public static AbilityTask sagariNoRyusei = new AbilityTask()
	{
		public void onItemUse(ItemStack itemStack, EntityPlayer player) 
		{
			if(!player.worldObj.isRemote)
			{			
				//AbilityProjectile proj = new AbilityProjectile(player.worldObj, player, ListExtraAttributes.METEOR );
				//proj.setHeadingFromThrower(player, 90, 0, 0, 1.5F, 0);
				//proj.setPosition((int) player.posX, (int) player.posY + 100, (int) player.posZ);
				//proj.motionY = -1.9;
				//player.worldObj.spawnEntityInWorld(proj);
			}
		};
	};
	 
	public static AbilityTask moko = new AbilityTask()
	{
		public void onProjectileHit(AbilityProjectile abilityProjectile, MovingObjectPosition hit)
		{
			if(hit.entityHit != null)
			{
				int posX = (int) hit.entityHit.posX;
				int posY = (int) hit.entityHit.posY;
				int posZ = (int) hit.entityHit.posZ;

				/*for(int x = -5; x < 5; x++)
				for(int y = 0; y < 22; y++)
				for(int z = -5; z < 5; z++)
				{
					if( abilityProjectile.worldObj.getBlock(posX + x, posY - y, posZ + z) != ListMisc.Ope && abilityProjectile.worldObj.getBlock(posX + x, posY - y, posZ + z) != ListMisc.OpeMid 
							&& abilityProjectile.worldObj.getBlock(posX + x, posY - y, posZ + z) != Blocks.bedrock)
						abilityProjectile.worldObj.setBlock(posX + x, posY - y, posZ + z, Blocks.air);
				}*/
			}
		};
	};
	
	public static AbilityTask chiyuHormone = new AbilityTask()
	{
		public void onItemUse(ItemStack itemStack, EntityPlayer player) 
		{
			player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 200, 1));
		};
		
		public void onItemHit(ItemStack itemStack, EntityLivingBase target, EntityLivingBase attacker) 
		{
			target.addPotionEffect(new PotionEffect(Potion.regeneration.id, 200, 1));		
		};
	};
	
	public static AbilityTask tensionHormone = new AbilityTask()
	{
		public void onItemUse(ItemStack itemStack, EntityPlayer player) 
		{
			player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 300, 1));
			player.addPotionEffect(new PotionEffect(Potion.resistance.id, 300, 1));
		};
		
		public void onItemHit(ItemStack itemStack, EntityLivingBase target, EntityLivingBase attacker) 
		{
			target.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 300, 1));
			target.addPotionEffect(new PotionEffect(Potion.resistance.id, 300, 1));			
		};
	}; 
	
	public static AbilityTask rejectDial = new AbilityTask()
	{
		public void onItemUse(ItemStack itemStack, EntityPlayer player) 
		{
			for(EntityLivingBase target : WyHelper.getEntitiesNear(player, 10))
			{
				target.attackEntityFrom(DamageSource.generic, Integer.MAX_VALUE);
				player.attackEntityFrom(DamageSource.generic, Integer.MAX_VALUE);
			}
		}
	};
	
	public static AbilityTask breathDial = new AbilityTask()
	{
		public void onItemUse(ItemStack itemStack, EntityPlayer player) 
		{
			for(EntityLivingBase target : WyHelper.getEntitiesNear(player, 10))
			{
				Direction dir = WyHelper.get4Directions(target);
				if(dir == WyHelper.Direction.SOUTH)
					target.motionX -= 10;
				else if(dir == WyHelper.Direction.EAST)
					target.motionX += 10; 
				else if(dir == WyHelper.Direction.NORTH)
					target.motionZ -= 10;
				else if(dir == WyHelper.Direction.WEST)  
					target.motionZ += 10;	
			}
		}
	};
	
	public static AbilityTask consumable = new AbilityTask()
	{
		public void onItemUse(ItemStack itemStack, EntityPlayer player) 
		{
			itemStack.damageItem(2, player);
		}
	};
	
	public static AbilityTask swordsSpecialAbility = new AbilityTask()
	{
		public void onItemUse(ItemStack itemStack, EntityPlayer player) 
		{
			ExtendedEntityStats props = ExtendedEntityStats.get(player);
			
			if(!player.worldObj.isRemote && itemStack.getTagCompound().getInteger("specialCooldown") <= 0)
			{
				if(props.getUsedFruit().equals("opeope") && DevilFruitsHelper.isEntityInRoom(player))
				{
					//AbilityProjectile proj = new AbilityProjectile(player.worldObj, (EntityPlayer) player, ListAttributes.DIALAXE.getAttribute());
					//proj.setHeadingFromThrower(player, player.rotationPitch, player.rotationYaw, 0, 1.7F, 0);
					//player.worldObj.spawnEntityInWorld(proj);
					itemStack.getTagCompound().setInteger("specialCooldown", 80);
				}
				else if(props.getUsedFruit().equals("guragura"))
				{
					player.worldObj.newExplosion(player, (int) player.posX, (int) player.posY, (int) player.posZ, 5, false, false);
					itemStack.getTagCompound().setInteger("specialCooldown", 140);
				}
				else if(props.getUsedFruit().equals("jikijiki"))
				{			
					//AbilityProjectile proj = new AbilityProjectile(player.worldObj, (EntityPlayer) player, ListExtraAttributes.GRAVITO);
					//proj.setHeadingFromThrower(player, player.rotationPitch, player.rotationYaw, 0, 1.7F, 0);
					//player.worldObj.spawnEntityInWorld(proj);
					itemStack.getTagCompound().setInteger("specialCooldown", 180);
				}
			}
		};
		
		public void onItemTick(ItemStack itemStack, EntityPlayer player) 
		{
			if(itemStack.getTagCompound().getInteger("specialCooldown") > 0)
			{
				int t = itemStack.getTagCompound().getInteger("specialCooldown");
				t--;
				itemStack.getTagCompound().setInteger("specialCooldown", t);
			}
		};
	};
	
	public static AbilityTask kilopress = new AbilityTask()
	{
		public void onItemUse(ItemStack itemStack, EntityPlayer player)
		{
			ExtendedEntityStats props = ExtendedEntityStats.get(player);
			if(!props.getKilo())
				props.setKilo(true);
			else
				props.setKilo(false);
		}
		
		public void onItemTick(ItemStack itemStack, EntityPlayer player) 
		{
			ExtendedEntityStats props = ExtendedEntityStats.get(player);	
			if(props.getKilo())
			{
				itemStack.setStackDisplayName("§r10,000 Kilo Press");
				if(player.onGround)
				{
					for(EntityLivingBase e : WyHelper.getEntitiesNear(player, 2))
						e.attackEntityFrom(DamageSource.causePlayerDamage(player), 25);
					props.setKilo(false);
				}
			}
			else
				itemStack.setStackDisplayName("§r1 Kilo Press");
		}
	};
	
	
	public static AbilityTask parfumefemur = new AbilityTask()
	{
		public void onItemHit(ItemStack itemStack, EntityLivingBase target, EntityLivingBase attacker) 
		{
			target.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 300, 1));
			target.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 300, 1));
		}
	};
	
	public static AbilityTask sparclaw = new AbilityTask()
	{
		public void onItemHit(ItemStack itemStack, EntityLivingBase target, EntityLivingBase attacker) 
		{
			target.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) attacker), 10);
		};
	};

	public static AbilityTask tabirayuki = new AbilityTask() {public void onItemHit(ItemStack itemStack, EntityLivingBase target, EntityLivingBase attacker) { target.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 1, 60));; }};
	
	public static AbilityTask milkydial = new AbilityTask()
	{
		public void onProjectileUpdate(AbilityProjectile a) 
		{
			if(a.worldObj.getBlock((int)a.posX, (int)a.posY - 1, (int)a.posZ) == Blocks.air && a.worldObj.getBlock((int)a.posX + 1, (int)a.posY - 1, (int)a.posZ) == Blocks.air
					&& a.worldObj.getBlock((int)a.posX - 1, (int)a.posY - 1, (int)a.posZ) == Blocks.air && a.worldObj.getBlock((int)a.posX, (int)a.posY - 1, (int)a.posZ + 1) == Blocks.air
					&& a.worldObj.getBlock((int)a.posX, (int)a.posY - 1, (int)a.posZ - 1) == Blocks.air && a.worldObj.getBlock((int)a.posX + 1, (int)a.posY - 1, (int)a.posZ + 1) == Blocks.air
					&& a.worldObj.getBlock((int)a.posX + 1, (int)a.posY - 1, (int)a.posZ - 1) == Blocks.air && a.worldObj.getBlock((int)a.posX - 1, (int)a.posY - 1, (int)a.posZ - 1) == Blocks.air
					&& a.worldObj.getBlock((int)a.posX - 1, (int)a.posY - 1, (int)a.posZ + 1) == Blocks.air)
			{
				a.worldObj.setBlock((int)a.posX, 		(int)a.posY - 1, (int)a.posZ, 		ListMisc.SkyBlock);
				a.worldObj.setBlock((int)a.posX + 1, 	(int)a.posY - 1, (int)a.posZ, 		ListMisc.SkyBlock);
				a.worldObj.setBlock((int)a.posX - 1, 	(int)a.posY - 1, (int)a.posZ, 		ListMisc.SkyBlock);
				a.worldObj.setBlock((int)a.posX, 		(int)a.posY - 1, (int)a.posZ + 1, 	ListMisc.SkyBlock);
				a.worldObj.setBlock((int)a.posX, 		(int)a.posY - 1, (int)a.posZ - 1, 	ListMisc.SkyBlock);
				a.worldObj.setBlock((int)a.posX + 1, 	(int)a.posY - 1, (int)a.posZ + 1, 	ListMisc.SkyBlock);
				a.worldObj.setBlock((int)a.posX + 1, 	(int)a.posY - 1, (int)a.posZ - 1, 	ListMisc.SkyBlock);
				a.worldObj.setBlock((int)a.posX - 1, 	(int)a.posY - 1, (int)a.posZ + 1, 	ListMisc.SkyBlock);
				a.worldObj.setBlock((int)a.posX - 1, 	(int)a.posY - 1, (int)a.posZ - 1, 	ListMisc.SkyBlock);
			}			
		};
	};

	public static AbilityTask doppelman = new AbilityTask()
	{ 
/*		//String[] actions = {"§rAggressive", "§rDefensive", "§rChange Positions", "§rFollow", "§rStay", "§rReturn"};
		String[] actions = {"§rChange Positions", "§rSelf-destruct", "§rReturn"};
		String currentAction = actions[0];
		
		public void onItemUse(ItemStack itemStack, EntityPlayer player)
		{	
			if(!player.worldObj.isRemote)
			{
				if(itemStack.getTagCompound().getBoolean("doppelman") == false)
				{
					Doppelman doppelman = new Doppelman(player.worldObj, player);
					doppelman.setLocationAndAngles((int) player.posX, (int) player.posY, (int) player.posZ, player.cameraYaw, player.cameraPitch);
					player.worldObj.spawnEntityInWorld(doppelman);
					itemStack.getTagCompound().setBoolean("doppelman", true);
				}
				else
				{
					if(MainKeys.isShiftKeyDown())
					{
						for(int i = 0; i < actions.length; i++)		
							if(currentAction.equals(actions[i]))
							{
								if(currentAction.equals(actions[actions.length - 1]))
									currentAction = actions[0];
								else
									currentAction = actions[i + 1];
								break;
							}
					}
					else
					{
						if(currentAction == actions[0])
						{
							for(EntityLivingBase dopp : WyHelper.getEntitiesNear(player, 840))
								if(dopp instanceof Doppelman && ((Doppelman) dopp).getOwner() == player)
								{
									BlockPos newPos = dopp.getPosition();
									dopp.setPositionAndUpdate((int) player.posX, (int) player.posY, (int) player.posZ);
									player.setPositionAndUpdate(newPos.getX(), newPos.getY(), newPos.getZ());
								}
						}
						if(currentAction == actions[1])
						{							
							for(EntityLivingBase dopp : WyHelper.getEntitiesNear(player, 840))
								if(dopp instanceof Doppelman && ((Doppelman) dopp).getOwner() == player)
								{
									dopp.worldObj.newExplosion(dopp, dopp.posX, dopp.posY, dopp.posZ, 5, false, false);
									for(EntityLivingBase target : WyHelper.getEntitiesNear(dopp, 10))
										if(target != ((Doppelman)dopp).getOwner())
											target.addPotionEffect(new PotionEffect(Potion.WITHER, 500, 1));
									dopp.setDead();
									itemStack.getTagCompound().setBoolean("doppelman", false);
								}
						}
						if(currentAction == actions[actions.length - 1])
						{
							for(EntityLivingBase dopp : WyHelper.getEntitiesNear(player, 840))
								if(dopp instanceof Doppelman && ((Doppelman) dopp).getOwner() == player)
									dopp.setDead();
							itemStack.getTagCompound().setBoolean("doppelman", false);
						}
					}		
				}
			}
		}

		public void onItemTick(ItemStack itemStack, EntityPlayer player) 
		{
			if(itemStack.getTagCompound().getBoolean("doppelman") == true)
			{
				if(MainKeys.isShiftKeyDown())
				{
					for(int i = 0; i < actions.length; i++)					
						if(currentAction.equals(actions[i]))
						{
							if(currentAction.equals(actions[actions.length - 1]))
								itemStack.setStackDisplayName(currentAction + " -> " + actions[0] );
							else
								itemStack.setStackDisplayName(currentAction + " -> " + actions[i + 1]);
						}
				}
				else
					itemStack.setStackDisplayName(currentAction);
			}
			else
				itemStack.setStackDisplayName("§rDoppelman");	
		};*/
		
	};

	public static AbilityTask bluesword = new AbilityTask() {public void onItemHit(ItemStack itemStack, EntityLivingBase target, EntityLivingBase attacker) {target.setFire(100);}};
	
}
