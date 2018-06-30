package xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.MainMod;
import xyz.pixelatedw.MineMineNoMi3.api.EnumParticleTypes;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.AbilityAttribute;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.AbilityProjectile;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;
import xyz.pixelatedw.MineMineNoMi3.lists.ListParticleEffects;

public class MokuProjectiles 
{

	public static ArrayList<Object[]> abilitiesClassesArray = new ArrayList();
	
	static
	{
		abilitiesClassesArray.add(new Object[] {WhiteSnake.class, ListAttributes.WHITESNAKE});
		abilitiesClassesArray.add(new Object[] {WhiteOut.class, ListAttributes.WHITEOUT});
	}
	
	public static class WhiteSnake extends AbilityProjectile
	{
		public WhiteSnake(World world)
		{super(world);}
		
		public WhiteSnake(World world, double x, double y, double z)
		{super(world, x, y, z);}
		
		public WhiteSnake(World world, EntityLivingBase player, AbilityAttribute attr) 
		{		
			super(world, player, attr);		
		}
		
		public void onUpdate()
		{	
			for(int i = 0; i < 5; i++)
			{
				double offsetX = (new Random().nextInt(20) + 1.0D - 10.0D) / 18.0D;
				double offsetY = (new Random().nextInt(20) + 1.0D - 10.0D) / 18.0D;
				double offsetZ = (new Random().nextInt(20) + 1.0D - 10.0D) / 18.0D;
			      
				MainMod.proxy.spawnCustomParticles(this, ID.PARTICLE_NAME_MOKU2, this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ, 0.0D, -2.01D, 0.0D);
			}

			super.onUpdate();
		}
	}	
	
	public static class WhiteOut extends AbilityProjectile
	{
		public WhiteOut(World world)
		{super(world);}
		
		public WhiteOut(World world, double x, double y, double z)
		{super(world, x, y, z);}
		
		public WhiteOut(World world, EntityLivingBase player, AbilityAttribute attr) 
		{		
			super(world, player, attr);		
		}
		
		public void onUpdate()
		{	
			double offsetX = (new Random().nextInt(20) + 1.0D - 10.0D) / 18.0D;
			double offsetY = (new Random().nextInt(20) + 1.0D - 10.0D) / 18.0D;
			double offsetZ = (new Random().nextInt(20) + 1.0D - 10.0D) / 18.0D;
			      
			MainMod.proxy.spawnCustomParticles(this, ID.PARTICLE_NAME_MOKU, this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ, 0.0D, -0.01D, 0.0D);
			
			super.onUpdate();
		}
		
		public void tasksImapct(MovingObjectPosition hit)
		{
			if(hit.entityHit != null && !hit.entityHit.isDead)
			{
				((EntityLivingBase) hit.entityHit).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 240, 1));
				((EntityLivingBase) hit.entityHit).addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 240, 1));
			}
		}
	}
}
