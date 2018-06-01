package xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.api.EnumParticleTypes;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.AbilityAttribute;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.AbilityProjectile;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.GoeProjectiles.Todoroki;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;
import xyz.pixelatedw.MineMineNoMi3.lists.ListExtraAttributes;

public class GomuProjectiles 
{

	public static ArrayList<Object[]> abilitiesClassesArray = new ArrayList();
	
	static
	{
		abilitiesClassesArray.add(new Object[] {GomuGomuNoPistol.class, ListExtraAttributes.GOMUGOMUNOPISTOL});
		abilitiesClassesArray.add(new Object[] {GomuGomuNoJetPistol.class, ListExtraAttributes.GOMUGOMUNOJETPISTOL});
		abilitiesClassesArray.add(new Object[] {GomuGomuNoElephantGun.class, ListExtraAttributes.GOMUGOMUNOELEPHANTGUN});
		abilitiesClassesArray.add(new Object[] {GomuGomuNoKongGun.class, ListExtraAttributes.GOMUGOMUNOKONGGUN});
		
		abilitiesClassesArray.add(new Object[] {GomuGomuNoBazooka.class, ListExtraAttributes.GOMUGOMUNOBAZOOKA});
		abilitiesClassesArray.add(new Object[] {GomuGomuNoJetBazooka.class, ListExtraAttributes.GOMUGOMUNOJETBAZOOKA});
		abilitiesClassesArray.add(new Object[] {GomuGomuNoGrizzlyMagnum.class, ListExtraAttributes.GOMUGOMUNOGRIZZLYMAGNUM});
		abilitiesClassesArray.add(new Object[] {GomuGomuNoLeoBazooka.class, ListExtraAttributes.GOMUGOMUNOLEOBAZOOKA});
		
		abilitiesClassesArray.add(new Object[] {GomuGomuNoGatling.class, ListExtraAttributes.GOMUGOMUNOGATLING});
		abilitiesClassesArray.add(new Object[] {GomuGomuNoJetGatling.class, ListExtraAttributes.GOMUGOMUNOJETGATLING});
		abilitiesClassesArray.add(new Object[] {GomuGomuNoElephantGatling.class, ListExtraAttributes.GOMUGOMUNOELEPHANTGATLING});
		abilitiesClassesArray.add(new Object[] {GomuGomuNoKongOrgan.class, ListExtraAttributes.GOMUGOMUNOKONGORGAN});
	}

	public static class GomuGomuNoKongOrgan extends AbilityProjectile
	{
		public GomuGomuNoKongOrgan(World world)
		{super(world);}
		
		public GomuGomuNoKongOrgan(World world, double x, double y, double z)
		{super(world, x, y, z);}
		
		public GomuGomuNoKongOrgan(World world, EntityLivingBase player, AbilityAttribute attr) 
		{		
			super(world, player, attr);		
		}	
	}
	
	
	public static class GomuGomuNoElephantGatling extends AbilityProjectile
	{
		public GomuGomuNoElephantGatling(World world)
		{super(world);}
		
		public GomuGomuNoElephantGatling(World world, double x, double y, double z)
		{super(world, x, y, z);}
		
		public GomuGomuNoElephantGatling(World world, EntityLivingBase player, AbilityAttribute attr) 
		{		
			super(world, player, attr);		
		}	
	}
	
	public static class GomuGomuNoJetGatling extends AbilityProjectile
	{
		public GomuGomuNoJetGatling(World world)
		{super(world);}
		
		public GomuGomuNoJetGatling(World world, double x, double y, double z)
		{super(world, x, y, z);}
		
		public GomuGomuNoJetGatling(World world, EntityLivingBase player, AbilityAttribute attr) 
		{		
			super(world, player, attr);		
		}	
			
		public void onUpdate()
		{	
			for (int i = 0; i < 2; i++)
			{
				double offsetX = (new Random().nextInt(5) + 1.0D - 2.5D) / 5.0D;
				double offsetY = (new Random().nextInt(5) + 1.0D - 2.5D) / 5.0D;
				double offsetZ = (new Random().nextInt(5) + 1.0D - 2.5D) / 5.0D;
		      
				this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL.getParticleName(), this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ, 0.0D, 0.0D, 0.0D);
				this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL.getParticleName(), this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ, 0.0D, 0.0D, 0.0D);
			}		
			super.onUpdate();
		}
	}
	
	public static class GomuGomuNoGatling extends AbilityProjectile
	{
		public GomuGomuNoGatling(World world)
		{super(world);}
		
		public GomuGomuNoGatling(World world, double x, double y, double z)
		{super(world, x, y, z);}
		
		public GomuGomuNoGatling(World world, EntityLivingBase player, AbilityAttribute attr) 
		{		
			super(world, player, attr);		
		}
	}
	
	
	public static class GomuGomuNoLeoBazooka extends AbilityProjectile
	{
		public GomuGomuNoLeoBazooka(World world)
		{super(world);}
		
		public GomuGomuNoLeoBazooka(World world, double x, double y, double z)
		{super(world, x, y, z);}
		
		public GomuGomuNoLeoBazooka(World world, EntityLivingBase player, AbilityAttribute attr) 
		{		
			super(world, player, attr);		
		}	
	}
	
	public static class GomuGomuNoGrizzlyMagnum extends AbilityProjectile
	{
		public GomuGomuNoGrizzlyMagnum(World world)
		{super(world);}
		
		public GomuGomuNoGrizzlyMagnum(World world, double x, double y, double z)
		{super(world, x, y, z);}
		
		public GomuGomuNoGrizzlyMagnum(World world, EntityLivingBase player, AbilityAttribute attr) 
		{		
			super(world, player, attr);		
		}	
	}

	public static class GomuGomuNoJetBazooka extends AbilityProjectile
	{
		public GomuGomuNoJetBazooka(World world)
		{super(world);}
		
		public GomuGomuNoJetBazooka(World world, double x, double y, double z)
		{super(world, x, y, z);}
		
		public GomuGomuNoJetBazooka(World world, EntityLivingBase player, AbilityAttribute attr) 
		{		
			super(world, player, attr);		
		}	
		
		public void onUpdate()
		{	
			for (int i = 0; i < 2; i++)
			{
				double offsetX = (new Random().nextInt(5) + 1.0D - 2.5D) / 5.0D;
				double offsetY = (new Random().nextInt(5) + 1.0D - 2.5D) / 5.0D;
				double offsetZ = (new Random().nextInt(5) + 1.0D - 2.5D) / 5.0D;
		      
				this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL.getParticleName(), this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ, 0.0D, 0.0D, 0.0D);
				this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL.getParticleName(), this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ, 0.0D, 0.0D, 0.0D);
			}		
			super.onUpdate();
		}
	}
	
	public static class GomuGomuNoBazooka extends AbilityProjectile
	{
		public GomuGomuNoBazooka(World world)
		{super(world);}
		
		public GomuGomuNoBazooka(World world, double x, double y, double z)
		{super(world, x, y, z);}
		
		public GomuGomuNoBazooka(World world, EntityLivingBase player, AbilityAttribute attr) 
		{		
			super(world, player, attr);		
		}	
	}
	
	public static class GomuGomuNoPistol extends AbilityProjectile
	{
		public GomuGomuNoPistol(World world)
		{super(world);}
		
		public GomuGomuNoPistol(World world, double x, double y, double z)
		{super(world, x, y, z);}
		
		public GomuGomuNoPistol(World world, EntityLivingBase player, AbilityAttribute attr) 
		{		
			super(world, player, attr);		
		}	
	}
	
	public static class GomuGomuNoJetPistol extends AbilityProjectile
	{
		public GomuGomuNoJetPistol(World world)
		{super(world);}
		
		public GomuGomuNoJetPistol(World world, double x, double y, double z)
		{super(world, x, y, z);}
		
		public GomuGomuNoJetPistol(World world, EntityLivingBase player, AbilityAttribute attr) 
		{		
			super(world, player, attr);		
		}	
		
		public void onUpdate()
		{	
			for (int i = 0; i < 2; i++)
			{
				double offsetX = (new Random().nextInt(5) + 1.0D - 2.5D) / 5.0D;
				double offsetY = (new Random().nextInt(5) + 1.0D - 2.5D) / 5.0D;
				double offsetZ = (new Random().nextInt(5) + 1.0D - 2.5D) / 5.0D;
		      
				this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL.getParticleName(), this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ, 0.0D, 0.0D, 0.0D);
				this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL.getParticleName(), this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ, 0.0D, 0.0D, 0.0D);
			}		
			super.onUpdate();
		}
	}
	
	public static class GomuGomuNoElephantGun extends AbilityProjectile
	{
		public GomuGomuNoElephantGun(World world)
		{super(world);}
		
		public GomuGomuNoElephantGun(World world, double x, double y, double z)
		{super(world, x, y, z);}
		
		public GomuGomuNoElephantGun(World world, EntityLivingBase player, AbilityAttribute attr) 
		{		
			super(world, player, attr);		
		}	
	}
	
	public static class GomuGomuNoKongGun extends AbilityProjectile
	{
		public GomuGomuNoKongGun(World world)
		{super(world);}
		
		public GomuGomuNoKongGun(World world, double x, double y, double z)
		{super(world, x, y, z);}
		
		public GomuGomuNoKongGun(World world, EntityLivingBase player, AbilityAttribute attr) 
		{		
			super(world, player, attr);		
		}	
	}
}
