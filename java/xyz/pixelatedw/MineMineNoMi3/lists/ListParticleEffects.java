package xyz.pixelatedw.MineMineNoMi3.lists;

import java.util.TimerTask;

import net.minecraft.entity.EntityLivingBase;
import xyz.pixelatedw.MineMineNoMi3.entities.particles.EntityParticleFX;
import xyz.pixelatedw.MineMineNoMi3.entities.particles.tasks.ParticleTaskCharge1;
import xyz.pixelatedw.MineMineNoMi3.entities.particles.tasks.ParticleTaskCharge2;
import xyz.pixelatedw.MineMineNoMi3.entities.particles.tasks.ParticleTaskSphere;
import xyz.pixelatedw.MineMineNoMi3.entities.particles.tasks.ParticleTaskTornado;
import xyz.pixelatedw.MineMineNoMi3.entities.particles.tasks.ParticleTaskWave1;

public class ListParticleEffects 
{
	
	//Sphere
	public static TimerTask createSphereFX(EntityLivingBase living, Object fxName, double radius, int density, int repeats)
	{
		return createSphereFX(living, living.posX, living.posY, living.posZ, fxName, radius, density, repeats);
	}
	public static TimerTask createSphereFX(EntityLivingBase living, double posX, double posY, double posZ, Object fxName, double radius, int density, int repeats)
	{
		if(fxName instanceof String)
			return new ParticleTaskSphere(living, posX, posY, posZ, (String) fxName, radius, density, repeats);
		else if(fxName instanceof EntityParticleFX)
			return new ParticleTaskSphere(living, posX, posY, posZ, (EntityParticleFX) fxName, radius, density, repeats);
		
		return null;
	}

	//Tornado
	public static TimerTask createTornadoFX(EntityLivingBase living, Object fxName, double radius, int density, double opening, double height)
	{
		return createTornadoFX(living, living.posX, living.posY, living.posZ, fxName, radius, density, opening, height);
	}
	public static TimerTask createTornadoFX(EntityLivingBase living, double posX, double posY, double posZ, Object fxName, double radius, int density, double opening, double height)
	{
		if(fxName instanceof String)
			return new ParticleTaskTornado(living, posX, posY, posZ, (String) fxName, radius, density, opening, height);
		else if(fxName instanceof EntityParticleFX)
			return new ParticleTaskTornado(living, posX, posY, posZ, (EntityParticleFX) fxName, radius, density, opening, height);
		
		return null;
	}
	
	//Charge 2
	public static TimerTask createCharge2FX(EntityLivingBase living, Object fxName, double radius, int density)
	{
		return createCharge2FX(living, living.posX, living.posY, living.posZ, fxName, radius, density);
	}
	public static TimerTask createCharge2FX(EntityLivingBase living, double posX, double posY, double posZ, Object fxName, double radius, int density)
	{		
		if(fxName instanceof String)
			return new ParticleTaskCharge2(living, posX, posY, posZ, (String) fxName, radius, density);
		else if(fxName instanceof EntityParticleFX)
			return new ParticleTaskCharge2(living, posX, posY, posZ, (EntityParticleFX) fxName, radius, density);
		
		return null;
	}
	
	//Charge 1
	public static TimerTask createCharge1FX(EntityLivingBase living, Object fxName, double radius, int density, double opening, double height)
	{
		return createCharge1FX(living, living.posX, living.posY, living.posZ, fxName, radius, density, opening, height);
	}
	public static TimerTask createCharge1FX(EntityLivingBase living, double posX, double posY, double posZ, Object fxName, double radius, int density, double opening, double height)
	{
		if(fxName instanceof String)
			return new ParticleTaskCharge1(living, posX, posY, posZ, (String) fxName, radius, density, opening, height);
		else if(fxName instanceof EntityParticleFX)
			return new ParticleTaskCharge1(living, posX, posY, posZ, (EntityParticleFX) fxName, radius, density, opening, height);
		
		return null;
	}
	
	//Wave
	public static TimerTask createWave1FX(EntityLivingBase living, Object fxName, int radius)
	{
		return createWave1FX(living, living.posX, living.posY, living.posZ, fxName, radius);
	}
	public static TimerTask createWave1FX(EntityLivingBase living, double posX, double posY, double posZ, Object fxName, int radius)
	{
		if(fxName instanceof String)
			return new ParticleTaskWave1(living, posX, posY, posZ, (String) fxName, radius);
		else if(fxName instanceof EntityParticleFX)
			return new ParticleTaskWave1(living, posX, posY, posZ, (EntityParticleFX) fxName, radius);
		
		return null;
	}
}
