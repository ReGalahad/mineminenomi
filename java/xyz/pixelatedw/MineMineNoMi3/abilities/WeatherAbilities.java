package xyz.pixelatedw.MineMineNoMi3.abilities;

import net.minecraft.entity.player.EntityPlayer;
import xyz.pixelatedw.MineMineNoMi3.abilities.SwordsmanAbilities.ShiShishiSonson;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.AbilityProjectile;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.JuryoProjectiles;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.WeatherProjectiles;
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
				AbilityProjectile proj = new WeatherProjectiles.ThunderBall(player.worldObj, player, ListAttributes.THUNDERBALL);	
				proj.setLocationAndAngles(player.posX, player.posY + (double) player.getEyeHeight(), player.posZ, player.rotationYaw, player.rotationPitch);
				player.worldObj.spawnEntityInWorld(proj);

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
				AbilityProjectile proj = new WeatherProjectiles.CoolBall(player.worldObj, player, ListAttributes.COOLBALL);	
				proj.setLocationAndAngles(player.posX, player.posY + (double) player.getEyeHeight(), player.posZ, player.rotationYaw, player.rotationPitch);
				player.worldObj.spawnEntityInWorld(proj);

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
				AbilityProjectile proj = new WeatherProjectiles.HeatBall(player.worldObj, player, ListAttributes.HEATBALL);	
				proj.setLocationAndAngles(player.posX, player.posY + (double) player.getEyeHeight(), player.posZ, player.rotationYaw, player.rotationPitch);
				player.worldObj.spawnEntityInWorld(proj);

				super.use(player);
			}
		}
	}
	
}
