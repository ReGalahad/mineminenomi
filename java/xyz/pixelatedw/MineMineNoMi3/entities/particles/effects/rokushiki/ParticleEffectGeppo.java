package xyz.pixelatedw.MineMineNoMi3.entities.particles.effects.rokushiki;

import java.util.Random;
import java.util.Timer;

import net.minecraft.entity.player.EntityPlayer;
import xyz.pixelatedw.MineMineNoMi3.api.EnumParticleTypes;
import xyz.pixelatedw.MineMineNoMi3.api.math.WyMathHelper;
import xyz.pixelatedw.MineMineNoMi3.entities.particles.effects.ParticleEffect;
import xyz.pixelatedw.MineMineNoMi3.lists.ListParticleEffects;

public class ParticleEffectGeppo extends ParticleEffect
{

	public void spawn(EntityPlayer player, double posX, double posY, double posZ)
	{
		player.worldObj.spawnParticle(EnumParticleTypes.CLOUD.getParticleName(), (int) posX, (int) posY, (int) posZ, 0, 0, 0);
		
		player.worldObj.spawnParticle(EnumParticleTypes.CLOUD.getParticleName(), (int) posX + 0.2, (int) posY, (int) posZ + 0.2, 0, 0, 0);
		player.worldObj.spawnParticle(EnumParticleTypes.CLOUD.getParticleName(), (int) posX + 0.2, (int) posY, (int) posZ - 0.2, 0, 0, 0);
		player.worldObj.spawnParticle(EnumParticleTypes.CLOUD.getParticleName(), (int) posX - 0.2, (int) posY, (int) posZ - 0.2, 0, 0, 0);
		player.worldObj.spawnParticle(EnumParticleTypes.CLOUD.getParticleName(), (int) posX - 0.2, (int) posY, (int) posZ + 0.2, 0, 0, 0);
			
		player.worldObj.spawnParticle(EnumParticleTypes.CLOUD.getParticleName(), (int) posX + 0.5, (int) posY, (int) posZ, 0, 0, 0);
		player.worldObj.spawnParticle(EnumParticleTypes.CLOUD.getParticleName(), (int) posX + 0.2, (int) posY, (int) posZ, 0, 0, 0);
		player.worldObj.spawnParticle(EnumParticleTypes.CLOUD.getParticleName(), (int) posX - 0.5, (int) posY, (int) posZ, 0, 0, 0);	
		player.worldObj.spawnParticle(EnumParticleTypes.CLOUD.getParticleName(), (int) posX - 0.2, (int) posY, (int) posZ, 0, 0, 0);	
			
		player.worldObj.spawnParticle(EnumParticleTypes.CLOUD.getParticleName(), (int) posX, (int) posY, (int) posZ + 0.5, 0, 0, 0);
		player.worldObj.spawnParticle(EnumParticleTypes.CLOUD.getParticleName(), (int) posX, (int) posY, (int) posZ + 0.2, 0, 0, 0);
		player.worldObj.spawnParticle(EnumParticleTypes.CLOUD.getParticleName(), (int) posX, (int) posY, (int) posZ - 0.5, 0, 0, 0);
		player.worldObj.spawnParticle(EnumParticleTypes.CLOUD.getParticleName(), (int) posX, (int) posY, (int) posZ - 0.2, 0, 0, 0);
	}

}
