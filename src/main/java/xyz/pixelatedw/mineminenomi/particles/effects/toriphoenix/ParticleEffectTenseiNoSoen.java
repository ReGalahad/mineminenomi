package xyz.pixelatedw.mineminenomi.particles.effects.toriphoenix;

import java.util.Timer;

import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.particles.CustomParticle;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.tasks.ParticleTaskTornado;
import xyz.pixelatedw.mineminenomi.values.ModValuesParticles;

public class ParticleEffectTenseiNoSoen extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		Timer timer = new Timer(true); 
		
		CustomParticle particle = new CustomParticle(player.world, ModValuesParticles.PARTICLE_ICON_BLUEFLAME,
				posX, 
				posY + 4,
				posZ, 
				0, 0, 0);
		timer.schedule(ParticleTaskTornado.Create(player, particle.getPos().getX(), particle.getPos().getY(), particle.getPos().getZ(), particle, 4.0, 1, 0.15, -1.7), 0);
	}

}
