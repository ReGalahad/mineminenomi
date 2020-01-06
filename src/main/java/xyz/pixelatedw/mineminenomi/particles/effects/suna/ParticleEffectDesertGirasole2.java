package xyz.pixelatedw.mineminenomi.particles.effects.suna;

import java.util.Timer;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.init.ModParticleTextures;
import xyz.pixelatedw.mineminenomi.particles.CustomParticle;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.tasks.ParticleTaskTornado;

public class ParticleEffectDesertGirasole2 extends ParticleEffect
{

	@Override
	public void spawn(PlayerEntity player, double posX, double posY, double posZ)
	{		
		Timer timer = new Timer(true); 

		CustomParticle particle = new CustomParticle(player.world, ModParticleTextures.SUNA,
				posX, 
				posY - 1,
				posZ, 
				0, 0, 0)
				.setParticleScale(4).setParticleGravity(-1.5F);

		timer.schedule(ParticleTaskTornado.Create(player, particle.getPos().getX(), particle.getPos().getY(), particle.getPos().getZ(), particle, 0.3, 1, 4, .8), 0);
	}

}
