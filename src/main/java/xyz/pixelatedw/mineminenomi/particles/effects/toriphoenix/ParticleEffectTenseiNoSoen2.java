package xyz.pixelatedw.mineminenomi.particles.effects.toriphoenix;

import java.util.Timer;

import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.init.ModParticleTextures;
import xyz.pixelatedw.mineminenomi.particles.CustomParticle;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.tasks.ParticleTaskWave;

public class ParticleEffectTenseiNoSoen2 extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		Timer timer = new Timer(true); 
		CustomParticle particle = new CustomParticle(player.world, ModParticleTextures.BLUE_FLAME,
				posX, 
				posY,
				posZ, 
				0, 0, 0);
		timer.schedule(ParticleTaskWave.Create(player, particle.getPos().getX(), particle.getPos().getY() + 0.5, particle.getPos().getZ(), particle, 20), 0);
	}

}
