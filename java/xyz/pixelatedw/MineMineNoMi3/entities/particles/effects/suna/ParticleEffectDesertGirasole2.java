package xyz.pixelatedw.MineMineNoMi3.entities.particles.effects.suna;

import java.util.Timer;

import net.minecraft.entity.player.EntityPlayer;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.MainMod;
import xyz.pixelatedw.MineMineNoMi3.api.math.WyMathHelper;
import xyz.pixelatedw.MineMineNoMi3.entities.particles.EntityParticleFX;
import xyz.pixelatedw.MineMineNoMi3.entities.particles.effects.ParticleEffect;
import xyz.pixelatedw.MineMineNoMi3.lists.ListParticleEffects;

public class ParticleEffectDesertGirasole2 extends ParticleEffect
{

	public void spawn(EntityPlayer player, double posX, double posY, double posZ)
	{		
		Timer timer = new Timer(true); 
		EntityParticleFX particle = new EntityParticleFX(player.worldObj, ID.PARTICLE_ICON_SUNA, 
				posX, 
				posY - 1, 
				posZ, 
				0, 0, 0)
			.setParticleScale(4).setParticleGravity(-1.5F);
		timer.schedule(ListParticleEffects.createTornadoFX(player, particle.posX, particle.posY, particle.posZ, particle, 0.3, 1, 4, .8), 0);
	}

}
