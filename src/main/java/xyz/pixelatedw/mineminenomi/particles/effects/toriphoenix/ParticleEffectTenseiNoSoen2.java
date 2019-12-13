package xyz.pixelatedw.mineminenomi.particles.effects.toriphoenix;

import java.util.Timer;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.particles.CustomParticle;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.tasks.ParticleTaskWave;
import xyz.pixelatedw.mineminenomi.values.ModValuesParticles;

public class ParticleEffectTenseiNoSoen2 extends ParticleEffect
{

	@Override
	public void spawn(PlayerEntity player, double posX, double posY, double posZ)
	{
		Timer timer = new Timer(true); 
		CustomParticle particle = new CustomParticle(player.world, ModValuesParticles.PARTICLE_ICON_BLUEFLAME,
				posX, 
				posY,
				posZ, 
				0, 0, 0);
		timer.schedule(ParticleTaskWave.Create(player, particle.getPos().getX(), particle.getPos().getY() + 0.5, particle.getPos().getZ(), particle, 20), 0);
	}

}
