package xyz.pixelatedw.MineMineNoMi3.abilities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.WeatherProjectiles;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.WeatherProjectiles.EntityMirageTempoCloud;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.misc.EntityBlackKnight;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.misc.EntityMirageClone;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.SniperProjectiles.EntityKemuriBoshiCloud;
import xyz.pixelatedw.MineMineNoMi3.items.weapons.ClimaTact;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;

public class WeatherAbilities
{
	
	public static Ability HEATBALL = new HeatBall();
	public static Ability COOLBALL = new CoolBall();
	public static Ability THUNDERBALL = new ThunderBall();
	
	public static Ability[] abilitiesArray = new Ability[] {HEATBALL, COOLBALL, THUNDERBALL};	

	public static class ThunderBall extends Ability
	{
		public ThunderBall() 
		{
			super(ListAttributes.THUNDERBALL); 
		}
		
		public void use(EntityPlayer player)
		{
			if(!this.isOnCooldown())
			{	
				ItemStack stack = player.getHeldItem();
				if(stack == null || !(stack.getItem() instanceof ClimaTact))
					return;
				
				ClimaTact climaTact = ((ClimaTact) stack.getItem());
				
				if(player.isSneaking())
				{	
					climaTact.chargeWeatherBall(stack, "T");
				}
				else
				{
					WeatherProjectiles.ThunderBall proj = new WeatherProjectiles.ThunderBall(player.worldObj, player, ListAttributes.THUNDERBALL);	
					proj.setLocationAndAngles(player.posX, player.posY + (double) player.getEyeHeight(), player.posZ, player.rotationYaw, player.rotationPitch);
					player.worldObj.spawnEntityInWorld(proj);
				}
				
				if(climaTact.checkCharge(stack).length() == 3)
				{
					String tempo = climaTact.checkCharge(stack);
					
					
					
					climaTact.emptyCharge(stack);
					super.use(player);
					return;
				}

				super.use(player);
			}
		}
	}
	
	public static class CoolBall extends Ability
	{
		public CoolBall() 
		{
			super(ListAttributes.COOLBALL); 
		}
		
		public void use(EntityPlayer player)
		{
			if(!this.isOnCooldown())
			{
				ItemStack stack = player.getHeldItem();
				if(stack == null || !(stack.getItem() instanceof ClimaTact))
					return;
				
				ClimaTact climaTact = ((ClimaTact) stack.getItem());
								
				if(player.isSneaking())
				{	
					climaTact.chargeWeatherBall(stack, "C");
				}
				else
				{
					WeatherProjectiles.CoolBall proj = new WeatherProjectiles.CoolBall(player.worldObj, player, ListAttributes.COOLBALL);	
					proj.setLocationAndAngles(player.posX, player.posY + (double) player.getEyeHeight(), player.posZ, player.rotationYaw, player.rotationPitch);
					player.worldObj.spawnEntityInWorld(proj);
				}
				
				if(climaTact.checkCharge(stack).length() == 3)
				{
					String tempo = climaTact.checkCharge(stack);
					
					
					
					climaTact.emptyCharge(stack);
					super.use(player);
					return;
				}
				
				super.use(player);
			}
		}
	}
	
	public static class HeatBall extends Ability
	{
		public HeatBall() 
		{
			super(ListAttributes.HEATBALL); 
		}
		
		public void use(EntityPlayer player)
		{
			if(!this.isOnCooldown())
			{
				ItemStack stack = player.getHeldItem();
				if(stack == null || !(stack.getItem() instanceof ClimaTact))
					return;
				
				ClimaTact climaTact = ((ClimaTact) stack.getItem());
				
				if(player.isSneaking())
				{	
					climaTact.chargeWeatherBall(stack, "H");
				}
				else
				{
					WeatherProjectiles.HeatBall proj = new WeatherProjectiles.HeatBall(player.worldObj, player, ListAttributes.HEATBALL);	
					proj.setLocationAndAngles(player.posX, player.posY + (double) player.getEyeHeight(), player.posZ, player.rotationYaw, player.rotationPitch);
					player.worldObj.spawnEntityInWorld(proj);
				}
				
				if(climaTact.checkCharge(stack).length() == 3)
				{
					String tempo = climaTact.checkCharge(stack);
					
					if(tempo.equalsIgnoreCase("CCH"))
					{
						EntityMirageTempoCloud smokeCloud = new EntityMirageTempoCloud(player.worldObj);
						smokeCloud.setLife(50);
						smokeCloud.setLocationAndAngles(player.posX, (player.posY + 1), player.posZ, 0, 0);
						smokeCloud.motionX = 0;
						smokeCloud.motionZ = 0;
						smokeCloud.motionY = 0;	
						smokeCloud.setThrower(player);
						player.worldObj.spawnEntityInWorld(smokeCloud);	
						
						for(int i = 0; i < 5; i++)
						{
							EntityMirageClone mirageClone = new EntityMirageClone(player.worldObj, player);
							mirageClone.setPositionAndRotation(player.posX, player.posY, player.posZ, 180, 0);
							player.worldObj.spawnEntityInWorld(mirageClone);							
						}
					}
					
					climaTact.emptyCharge(stack);				
					super.use(player);
					return;
				}
				
				super.use(player);
			}
		}
	}
	
}
