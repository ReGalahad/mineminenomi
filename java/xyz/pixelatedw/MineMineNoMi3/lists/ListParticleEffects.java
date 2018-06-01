package xyz.pixelatedw.MineMineNoMi3.lists;

import java.util.Random;
import java.util.TimerTask;

import net.minecraft.client.particle.EntityAuraFX;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import xyz.pixelatedw.MineMineNoMi3.MainMod;
import xyz.pixelatedw.MineMineNoMi3.api.EnumParticleTypes;

public class ListParticleEffects 
{
	
	//Sphere
	public static TimerTask createSphereFX(EntityLivingBase living, String fxName, double radius, int density, int repeats)
	{
		return createSphereFX(living, living.posX, living.posY, living.posZ, fxName, radius, density, repeats);
	}
	public static TimerTask createSphereFX(EntityLivingBase living, double posX, double posY, double posZ, String fxName, double radius, int density, int repeats)
	{
		return new Sphere(living, fxName, radius, density, repeats);
	}
	
	//Mist
	public static TimerTask createMistFX(EntityLivingBase living, String fxName, int radius, int density)
	{
		return createMistFX(living, living.posX, living.posY, living.posZ, fxName, radius, density);
	}
	public static TimerTask createMistFX(EntityLivingBase living, double posX, double posY, double posZ, String fxName, int radius, int density)
	{
		return new Mist(living, fxName, radius, density);
	}
	
	//Tornado
	public static TimerTask createTornadoFX(EntityLivingBase living, String fxName, double radius, int density, double opening, double height)
	{
		return createTornadoFX(living, living.posX, living.posY, living.posZ, fxName, radius, density, opening, height);
	}
	public static TimerTask createTornadoFX(EntityLivingBase living, double posX, double posY, double posZ, String fxName, double radius, int density, double opening, double height)
	{
		return new Tornado(living, posX, posY, posZ, fxName, radius, density, opening, height);
	}
	
	//Ground Effect
	public static TimerTask createGroundEffect1FX(EntityLivingBase living, String fxName, double radius, int density)
	{
		return createGroundEffect1FX(living, living.posX, living.posY, living.posZ, fxName, radius, density);
	}
	public static TimerTask createGroundEffect1FX(EntityLivingBase living, double posX, double posY, double posZ, String fxName, double radius, int density)
	{
		return new GroundEffect1(living, posX, posY, posZ, fxName, radius, density);
	}
	
	//Charge Effect
	public static TimerTask createChargeEffect1FX(EntityLivingBase living, String fxName, double radius, int density, double opening, double height)
	{
		return createChargeEffect1FX(living, living.posX, living.posY, living.posZ, fxName, radius, density, opening, height);
	}
	public static TimerTask createChargeEffect1FX(EntityLivingBase living, double posX, double posY, double posZ, String fxName, double radius, int density, double opening, double height)
	{
		return new ChargeEffect1(living, posX, posY, posZ, fxName, radius, density, opening, height);
	}
	
}

/** XXX Tornado **/
class Tornado extends TimerTask
{
	
	private EntityLivingBase player;
	private String fxName;
	private double radius, opening, posX, posY, posZ, height;
	private int density, repeats;

	public Tornado(EntityLivingBase player, double posX, double posY, double posZ, String fxName, double radius, int density, double opening, double height)
	{
		this.player = player;
		this.fxName = fxName;
		this.radius = radius;
		this.density = density;
		this.opening = opening;
		this.height = height;
		this.posX = posX;
		this.posY = posY;
		this.posZ = posZ;
	}
	
	public void run()
	{
		double phi = 0;
		double x, y, z;
		
		while(phi < Math.PI * density)
		{
			phi += Math.PI / 16;
			for(double t = 0; t <= 2 * Math.PI; t += Math.PI / 16)
			{
				for(double i = 0; i <= 1; i += 1)
				{				
					x = opening * (radius * Math.PI + t) * Math.cos(t * phi + i * Math.PI);
					y = height * t;
					z = opening * (radius * Math.PI + t) * Math.sin(t * phi - i * Math.PI);
					
					try
					{
						if(this.fxName.contains("custom_"))
							MainMod.proxy.spawnCustomParticles(this.player, this.fxName.replace("custom_", ""), this.posX + x, this.posY + y, this.posZ + z, 0.0D, 0.0D, 0.0D);
						else							
							player.worldObj.spawnParticle(fxName, this.posX + x, this.posY + y, this.posZ + z, 0.0D, 0.0D, 0.0D);
						Thread.sleep(1);
					} 
					catch (InterruptedException e) 
					{
						e.printStackTrace();
					}	
				}
			}
		}		
	}
}

/** XXX GroundEffect1 **/
class GroundEffect1 extends TimerTask
{
	
	private EntityLivingBase player;
	private String fxName;
	private double radius, posX, posY, posZ;
	private int density;

	public GroundEffect1(EntityLivingBase player, double posX, double posY, double posZ, String fxName, double radius, int density)
	{
		this.player = player;
		this.fxName = fxName;
		this.radius = radius;
		this.density = density;
		this.posX = posX;
		this.posY = posY;
		this.posZ = posZ;
	}
	
	public void run()
	{
		double phi = 0;
		double x, y, z;
		
		while(phi < density * Math.PI)
		{
			phi += Math.PI / 16;
			for(double t = 0; t <= 2 * Math.PI; t += Math.PI / 16)
			{
				for(double i = 0; i <= 1; i += 1)
				{
					
					x = 0.5 * (radius * phi + t) * Math.cos(t * phi + i);
					y = 0.1 * (t * 3 + radius * Math.PI);
					z = 0.5 * (radius * phi + t) * Math.sin(t * phi + i);
					
					try
					{
						if(this.fxName.contains("custom_"))
							MainMod.proxy.spawnCustomParticles(this.player, this.fxName.replace("custom_", ""), this.posX + x, this.posY - 0.7 + y, this.posZ + z, 0.0D, 0.0D, 0.0D);
						else							
							player.worldObj.spawnParticle(fxName, this.posX + x, this.posY - 0.7 + y, this.posZ + z, 0.0D, 0.0D, 0.0D);
						Thread.sleep(1);
					} 
					catch (InterruptedException e) 
					{
						e.printStackTrace();
					}	
				}
			}
		}		
	}
}

/** XXX ChargeEffect1 **/
class ChargeEffect1 extends TimerTask
{
	
	private EntityLivingBase player;
	private String fxName;
	private double radius, posX, posY, posZ, height, opening;
	private int density;

	public ChargeEffect1(EntityLivingBase player, double posX, double posY, double posZ, String fxName, double radius, int density, double opening, double height)
	{
		this.player = player;
		this.fxName = fxName;
		this.radius = radius;
		this.density = density;
		this.opening = opening;
		this.height = height;
		this.posX = posX;
		this.posY = posY;
		this.posZ = posZ;
	}
	
	public void run()
	{
		double phi = 0;
		double x, y, z;
		
		while(phi < density * Math.PI)
		{
			phi += Math.PI / 16;
			for(double t = 0; t <= 2 * Math.PI; t += Math.PI / 16)
			{
				for(double i = 0; i <= 1; i += 1)
				{
					
					x = opening * (radius / Math.PI + t) / Math.cos(t * phi + i * Math.PI);
					y = height * t;
					z = opening * (radius / Math.PI + t) / Math.sin(t * phi + i * Math.PI);
					
					try
					{
						if(this.fxName.contains("custom_"))
							MainMod.proxy.spawnCustomParticles(this.player, this.fxName.replace("custom_", ""), this.posX + x, this.posY + y, this.posZ + z, 0.0D, 0.0D, 0.0D);
						else							
							player.worldObj.spawnParticle(fxName, this.posX + x, this.posY + y, this.posZ + z, 0.0D, 0.0D, 0.0D);
						Thread.sleep(1);
					} 
					catch (InterruptedException e) 
					{
						e.printStackTrace();
					}	
				}
			}
		}		
	}
}

/** XXX Mist **/
class Mist extends TimerTask
{
	
	private EntityLivingBase player;
	private String fxName;
	private int radius, density, repeats;

	public Mist(EntityLivingBase player, String fxName, int radius, int density)
	{
		this.player = player;
		this.fxName = fxName;
		this.radius = radius;
		this.density = density;
	}
	
	public void run()
	{
		try 
		{
			for (int i = 0; i < density; i++)
			{
				double offsetX = (new Random().nextInt(radius) + 1.0D - (radius / 2)) / (radius / 2);
				double offsetY = (new Random().nextInt(radius) + 1.0D - (radius / 2)) / (radius / 2);
				double offsetZ = (new Random().nextInt(radius) + 1.0D - (radius / 2)) / (radius / 2);
		      
				player.worldObj.spawnParticle(fxName, player.posX + offsetX, player.posY - 1.5 + offsetY, player.posZ + offsetZ, 0, 0.1, 0);
			}	
			Thread.sleep(1);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}	
	}
	
}

/** XXX Sphere **/
class Sphere extends TimerTask
{
	
	private EntityLivingBase player;
	private String fxName;
	private int density, repeats;
	private double radius;

	public Sphere(EntityLivingBase player, String fxName, double radius, int density, int repeats)
	{
		this.player = player;
		this.fxName = fxName;
		this.radius = radius;
		this.density = density;
		this.repeats = repeats;
	}
	
	public void run()
	{
		double phi = 0;
		while(phi < repeats * Math.PI)
		{
			phi += Math.PI / density;
			
			for(double theta = 0; theta <= 2 * Math.PI; theta += Math.PI / density)
			{
				double r = radius;
				double x = r * Math.cos(theta) * Math.sin(phi);
				double y = r * Math.cos(phi) + 1.5;
				double z = r * Math.sin(theta) * Math.sin(phi);
				
				try 
				{
					player.worldObj.spawnParticle(fxName, player.posX + x, player.posY - 1.5 + y, player.posZ + z, 0, 0, 0);
					Thread.sleep(2);
				} 
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}	
			}
		}
	}
}