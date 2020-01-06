package xyz.pixelatedw.mineminenomi.particles.effects.hie;

import java.util.Timer;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.init.ModParticleTextures;
import xyz.pixelatedw.mineminenomi.particles.SimpleParticle;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.tasks.ParticleTaskWave;

public class ParticleEffectIceAge extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		for(int i = 0; i < 5; i++)
		{
			Timer timer = new Timer(true); 
			
			SimpleParticle cp = new SimpleParticle(world, ModParticleTextures.HIE,
					posX, 
					posY - 0.5,
					posZ, 
					0,
					0,
					0)
					.setParticleScale(1 + world.rand.nextFloat());
			Minecraft.getInstance().particles.addEffect(cp);

			timer.schedule(ParticleTaskWave.Create(player, cp.getPos().getX(), cp.getPos().getY(), cp.getPos().getZ(), cp, 20), 0);
		}
	}

}
