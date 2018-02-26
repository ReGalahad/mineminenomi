package xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFireworkSparkFX;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.api.EnumParticleTypes;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.AbilityAttribute;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.AbilityProjectile;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;

public class MeraProjectiles
{
	public static ArrayList<Object[]> abilitiesClassesArray = new ArrayList();
	
	static
	{
		abilitiesClassesArray.add(new Object[] {Hiken.class, ListAttributes.HIKEN});
		abilitiesClassesArray.add(new Object[] {Higan.class, ListAttributes.HIGAN});
		abilitiesClassesArray.add(new Object[] {DaiEnkaiEntei.class, ListAttributes.DAIENKAIENTEI});
		abilitiesClassesArray.add(new Object[] {Hidaruma.class, ListAttributes.HIDARUMA});
		abilitiesClassesArray.add(new Object[] {Jujika.class, ListAttributes.JUJIKA});
	}
	
	public static class Hiken extends AbilityProjectile
	{
		private Random r = new Random();
		
		public Hiken(World world)
		{super(world);}
		
		public Hiken(World world, double x, double y, double z)
		{super(world, x, y, z);}
		
		public Hiken(World world, EntityLivingBase player, AbilityAttribute attr) 
		{		
			super(world, player, attr);		
		}
		
		public void onUpdate()
		{				
			for (int i = 0; i < 25; i++)
			{
				double offsetX = (new Random().nextInt(50) + 1.0D - 25.0D) / 30.0D;
				double offsetY = (new Random().nextInt(50) + 1.0D - 25.0D) / 30.0D;
				double offsetZ = (new Random().nextInt(50) + 1.0D - 25.0D) / 30.0D;
		      
				this.worldObj.spawnParticle(EnumParticleTypes.FLAME.getParticleName(), this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ, 0.0D, 0.03D, 0.0D);
				this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL.getParticleName(), this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ, 0.0D, 0.03D, 0.0D);
			}
			
			super.onUpdate();
		}
		
	}
	
	public static class Higan extends AbilityProjectile
	{
		public Higan(World world)
		{super(world);}
		
		public Higan(World world, double x, double y, double z)
		{super(world, x, y, z);}
		
		public Higan(World world, EntityLivingBase player, AbilityAttribute attr) 
		{		
			super(world, player, attr);		
		}
		
		public void tasksImapct(MovingObjectPosition hit)
		{
			this.worldObj.setBlock((int)this.posX, (int)this.posY, (int)this.posZ, Blocks.fire);
		};
		
		public void onUpdate()
		{	
			this.worldObj.spawnParticle(EnumParticleTypes.FLAME.getParticleName(), this.posX, this.posY, this.posZ, 0.0D, 0.03D, 0.0D);
			this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL.getParticleName(), this.posX, this.posY, this.posZ, 0.0D, 0.03D, 0.0D);
			
			super.onUpdate();
		}
	}
	
	public static class DaiEnkaiEntei extends AbilityProjectile
	{
		public DaiEnkaiEntei(World world)
		{super(world);}
		
		public DaiEnkaiEntei(World world, double x, double y, double z)
		{super(world, x, y, z);}
		
		public DaiEnkaiEntei(World world, EntityLivingBase player, AbilityAttribute attr) 
		{		
			super(world, player, attr);		
		}
		
		public void onUpdate()
		{	
			for (int i = 0; i < 75; i++)
			{
				double offsetX = (new Random().nextInt(40) + 2.0D - 20.0D) / 10.0D;
				double offsetY = (new Random().nextInt(40) + 2.0D - 20.0D) / 10.0D;
				double offsetZ = (new Random().nextInt(40) + 2.0D - 20.0D) / 10.0D;
		      
				this.worldObj.spawnParticle(EnumParticleTypes.FLAME.getParticleName(), this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ, 0.0D, 0.03D, 0.0D);
				this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL.getParticleName(), this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ, 0.0D, 0.03D, 0.0D);
			}
			
			super.onUpdate();
		}
	}
	
	public static class Hidaruma extends AbilityProjectile
	{
		public Hidaruma(World world)
		{super(world);}
		
		public Hidaruma(World world, double x, double y, double z)
		{super(world, x, y, z);}
		
		public Hidaruma(World world, EntityLivingBase player, AbilityAttribute attr) 
		{		
			super(world, player, attr);		
		}
		
		public void tasksImapct(MovingObjectPosition hit)
		{
			if(hit.entityHit != null)
				hit.entityHit.setFire(this.ticks);

			this.worldObj.setBlock((int)this.posX, (int)this.posY, (int)this.posZ, Blocks.fire);
		};
		
		public void onUpdate()
		{	
			for (int i = 0; i < 25; i++)
			{
				double offsetX = (new Random().nextInt(10) + 1.0D - 5.0D) / 10.0D;
				double offsetY = (new Random().nextInt(10) + 1.0D - 5.0D) / 10.0D;
				double offsetZ = (new Random().nextInt(10) + 1.0D - 5.0D) / 10.0D;
		      
				this.worldObj.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY.getParticleName(), this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ, 0.0D, 0.05D, 0.0D);
			}
			
			super.onUpdate();
		}
	}
	
	public static class Jujika extends AbilityProjectile
	{
		public Jujika(World world)
		{super(world);}
		
		public Jujika(World world, double x, double y, double z)
		{super(world, x, y, z);}
		
		public Jujika(World world, EntityLivingBase player, AbilityAttribute attr) 
		{		
			super(world, player, attr);		
		}
		
		public void tasksImapct(MovingObjectPosition hit)
		{
			if(hit.entityHit != null)
			{
				hit.entityHit.setFire(this.ticks);
				for(int j = -2; j <= 2; j++)
				{
					for(int i = -5; i <= 5; i++)
						if(this.worldObj.isAirBlock((int)hit.entityHit.posX + i, (int)hit.entityHit.posY + j, (int)hit.entityHit.posZ))
							this.worldObj.setBlock((int)hit.entityHit.posX + i, (int)hit.entityHit.posY + j, (int)hit.entityHit.posZ, Blocks.fire);
						
					for(int i = -5; i <= 5; i++)
						if(this.worldObj.isAirBlock((int)hit.entityHit.posX, (int)hit.entityHit.posY + j, (int)hit.entityHit.posZ + i))
							this.worldObj.setBlock((int)hit.entityHit.posX, (int)hit.entityHit.posY + j, (int)hit.entityHit.posZ + i, Blocks.fire);
				}
			}
			
			for(int j = -2; j <= 2; j++)
			{
				for(int i = -5; i <= 5; i++)
					if(this.worldObj.isAirBlock((int)this.posX + i, (int)this.posY + j, (int)this.posZ))
						this.worldObj.setBlock((int)this.posX + i, (int)this.posY + j, (int)this.posZ, Blocks.fire);
					
				for(int i = -5; i <= 5; i++)
					if(this.worldObj.isAirBlock((int)this.posX, (int)this.posY + j, (int)this.posZ + i))
						this.worldObj.setBlock((int)this.posX, (int)this.posY + j, (int)this.posZ + i, Blocks.fire);
			}
		};
	}
}

