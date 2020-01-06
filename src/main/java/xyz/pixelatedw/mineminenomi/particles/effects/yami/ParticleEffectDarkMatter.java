package xyz.pixelatedw.mineminenomi.particles.effects.yami;

import java.util.Timer;

import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.init.ModParticleTextures;
import xyz.pixelatedw.mineminenomi.particles.CustomParticle;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.tasks.ParticleTaskTornado;

public class ParticleEffectDarkMatter extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		Timer timer = new Timer(true); 
		
		CustomParticle particle = new CustomParticle(player.world, ModParticleTextures.DARKNESS,
				posX, 
				posY + 1,
				posZ, 
				0, 0, 0)
				.setParticleGravity(-1.25f + player.world.rand.nextFloat()).setParticleScale(player.world.rand.nextInt(3) + 1);
		timer.schedule(ParticleTaskTornado.Create(player, posX, posY, posZ, particle, 8.0, 2, 0.15, 0.5), 0);
	}

}
