package xyz.pixelatedw.mineminenomi.particles.effects.fishkarate;

import java.util.Timer;

import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.tasks.ParticleTaskSphere;

public class ParticleEffectSamehada extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		Timer timer = new Timer(true); 
		timer.schedule(ParticleTaskSphere.Create(player, posX, posY, posZ, ParticleTypes.SPLASH, 1.5, 6, 1), 0);
	}

}
