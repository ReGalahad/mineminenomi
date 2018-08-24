package xyz.pixelatedw.MineMineNoMi3.lists;

import java.util.TimerTask;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.Explosion;
import xyz.pixelatedw.MineMineNoMi3.entities.particles.ParticleTaskCharge1;
import xyz.pixelatedw.MineMineNoMi3.entities.particles.ParticleTaskCharge2;
import xyz.pixelatedw.MineMineNoMi3.entities.particles.ParticleTaskSphere;
import xyz.pixelatedw.MineMineNoMi3.entities.particles.ParticleTaskTornado;
import xyz.pixelatedw.MineMineNoMi3.entities.particles.ParticleTaskWave1;

public class ListParticleEffects 
{
	
	//Sphere
	public static TimerTask createSphereFX(EntityLivingBase living, String fxName, double radius, int density, int repeats)
	{
		return createSphereFX(living, living.posX, living.posY, living.posZ, fxName, radius, density, repeats);
	}
	public static TimerTask createSphereFX(EntityLivingBase living, double posX, double posY, double posZ, String fxName, double radius, int density, int repeats)
	{
		return new ParticleTaskSphere(living, posX, posY, posZ, fxName, radius, density, repeats);
	}

	//Tornado
	public static TimerTask createTornadoFX(EntityLivingBase living, String fxName, double radius, int density, double opening, double height)
	{
		return createTornadoFX(living, living.posX, living.posY, living.posZ, fxName, radius, density, opening, height);
	}
	public static TimerTask createTornadoFX(EntityLivingBase living, double posX, double posY, double posZ, String fxName, double radius, int density, double opening, double height)
	{
		return new ParticleTaskTornado(living, posX, posY, posZ, fxName, radius, density, opening, height);
	}
	
	//Charge 2
	public static TimerTask createCharge2FX(EntityLivingBase living, String fxName, double radius, int density)
	{
		return createCharge2FX(living, living.posX, living.posY, living.posZ, fxName, radius, density);
	}
	public static TimerTask createCharge2FX(EntityLivingBase living, double posX, double posY, double posZ, String fxName, double radius, int density)
	{
		return new ParticleTaskCharge2(living, posX, posY, posZ, fxName, radius, density);
	}
	
	//Charge 1
	public static TimerTask createCharge1FX(EntityLivingBase living, String fxName, double radius, int density, double opening, double height)
	{
		return createCharge1FX(living, living.posX, living.posY, living.posZ, fxName, radius, density, opening, height);
	}
	public static TimerTask createCharge1FX(EntityLivingBase living, double posX, double posY, double posZ, String fxName, double radius, int density, double opening, double height)
	{
		return new ParticleTaskCharge1(living, posX, posY, posZ, fxName, radius, density, opening, height);
	}
	
	//Wave
	public static TimerTask createWave1FX(EntityLivingBase living, String fxName)
	{
		return createWave1FX(living, living.posX, living.posY, living.posZ, fxName);
	}
	public static TimerTask createWave1FX(EntityLivingBase living, double posX, double posY, double posZ, String fxName)
	{
		return new ParticleTaskWave1(living, posX, posY, posZ, fxName);
	}
}



/* LEGACY
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
*/