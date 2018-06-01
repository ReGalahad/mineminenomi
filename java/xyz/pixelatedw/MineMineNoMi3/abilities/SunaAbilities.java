package xyz.pixelatedw.MineMineNoMi3.abilities;

import java.util.Timer;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import xyz.pixelatedw.MineMineNoMi3.api.EnumParticleTypes;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper.Direction;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.api.math.WyMathHelper;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.SunaProjectiles;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;
import xyz.pixelatedw.MineMineNoMi3.lists.ListParticleEffects;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketParticles;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketPlayer;

public class SunaAbilities 
{

	public static Ability[] abilitiesArray = new Ability[] {new Barjan(), new Sables(), new GroundDeath(), new DesertSpada()};

	public static class Barjan extends Ability
	{
		public Barjan() 
		{
			super(ListAttributes.BARJAN); 
		}
		
		public void use(EntityPlayer player)
		{		
			this.projectile = new SunaProjectiles.Barjan(player.worldObj, player, attr);
			super.use(player);
		} 
	}
	
	public static class Sables extends Ability
	{
		public Sables() 
		{
			super(ListAttributes.SABLES); 
		}	
		
		public void hitEntity(EntityPlayer player, EntityLivingBase target) 
		{	
			double newPosX = 0, newPosY = 0, newPosZ = 0;
			
			newPosY += 10;
			target.addPotionEffect(new PotionEffect(Potion.hunger.id, 500, 1));
			Direction dir = WyHelper.get4Directions(player);
			if(dir == WyHelper.Direction.SOUTH)
				newPosX += WyMathHelper.randomWithRange(-10, 10);
			else if(dir == WyHelper.Direction.EAST)
				newPosX -= WyMathHelper.randomWithRange(-10, 10);
			else if(dir == WyHelper.Direction.NORTH)
				newPosZ += WyMathHelper.randomWithRange(-10, 10);
			else if(dir == WyHelper.Direction.WEST)  
				newPosZ -= WyMathHelper.randomWithRange(-10, 10);

			WyNetworkHelper.sendTo(new PacketParticles("sables", target), (EntityPlayerMP) player);
			target.setPositionAndUpdate(target.posX + newPosX, target.posY + newPosY, target.posZ + newPosZ);
			
			super.hitEntity(player, target);
		}
	}
	
	public static class GroundDeath extends Ability
	{
		public GroundDeath() 
		{
			super(ListAttributes.GROUNDDEATH); 
		}
		
		public void use(EntityPlayer player)
		{	
			if(!isOnCooldown)
			{
				for(EntityLivingBase l : WyHelper.getEntitiesNear(player, 25))
				{
					for(int i = -2; i < 2; i++)
					for(int j = -3; j < 3; j++)
					for(int k = -2; k < 2; k++)
					{
						l.worldObj.setBlock((int) l.posX + k, (int) l.posY - j, (int) l.posZ + i, Blocks.air);
						l.worldObj.setBlock((int) l.posX + k, (int) l.posY + j, (int) l.posZ + i, Blocks.sand);
					}				
				}	
				
				WyNetworkHelper.sendTo(new PacketParticles("groundDeath", player.posX, player.posY, player.posZ), (EntityPlayerMP) player);

				super.use(player);
			}
		}
	}
	
	public static class DesertSpada extends Ability
	{
		public DesertSpada() 
		{
			super(ListAttributes.DESERTSPADA); 
		}
		
		public void use(EntityPlayer player)
		{		
			if(!isOnCooldown)
			{
				if(WyHelper.get4Directions(player) == WyHelper.Direction.NORTH)
				{
					for(int i = -3; i < 3; i++)
					for(int j = 0; j < 5; j++)
					for(int k = 0; k < 12; k++)		
					{
						player.worldObj.setBlock((int) player.posX + i, (int) player.posY - (j + 1), (int) player.posZ - (k + 2), Blocks.air);
						player.worldObj.setBlock((int) player.posX + i, (int) player.posY + (j + 1), (int) player.posZ - (k + 2), Blocks.sand);
					}
				}
				else if(WyHelper.get4Directions(player) == WyHelper.Direction.SOUTH)
				{
					for(int i = -3; i < 3; i++)
					for(int j = 0; j < 5; j++)
					for(int k = 0; k < 12; k++)		
					{
						player.worldObj.setBlock((int) player.posX + i, (int) player.posY - (j + 1), (int) player.posZ + (k + 2), Blocks.air);
						player.worldObj.setBlock((int) player.posX + i, (int) player.posY + (j + 1), (int) player.posZ + (k + 2), Blocks.sand);
					}
				}
				else if(WyHelper.get4Directions(player) == WyHelper.Direction.EAST)
				{
					for(int i = 0; i < 12; i++)
					for(int j = 0; j < 5; j++)
					for(int k = -3; k < 3; k++)		
					{
						player.worldObj.setBlock((int) player.posX + (i + 2), (int) player.posY - (j + 1), (int) player.posZ + k, Blocks.air);
						player.worldObj.setBlock((int) player.posX + (i + 2), (int) player.posY + (j + 1), (int) player.posZ + k, Blocks.sand);
					}
				}
				else if(WyHelper.get4Directions(player) == WyHelper.Direction.WEST)
				{
					for(int i = 0; i < 12; i++)
					for(int j = 0; j < 5; j++)
					for(int k = -3; k < 3; k++)		
					{
						player.worldObj.setBlock((int) player.posX - (i + 2), (int) player.posY - (j + 1), (int) player.posZ + k, Blocks.air);
						player.worldObj.setBlock((int) player.posX - (i + 2), (int) player.posY + (j + 1), (int) player.posZ + k, Blocks.sand);
					}
				}				
				super.use(player);
			}
		}
	}
	
}
