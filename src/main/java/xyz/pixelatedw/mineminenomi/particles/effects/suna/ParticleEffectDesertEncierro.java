package xyz.pixelatedw.mineminenomi.particles.effects.suna;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.particles.SimpleParticle;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.values.ModValuesParticles;

public class ParticleEffectDesertEncierro extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		double t = 0;
		double x, y, z;
		Random rand = world.rand;

		while (t < 1)
		{
			t += 0.5 * Math.PI;

			for (double theta = 0; theta <= 4 * Math.PI; theta += Math.PI / 32)
			{
				x = t * Math.cos(theta);
				y = rand.nextInt(1);
				z = t * Math.sin(theta);

				motionX = -x / 10;
				motionY = 0.1 + (rand.nextDouble() / 10);
				motionZ = -z / 10;

				SimpleParticle cp = new SimpleParticle(world, ModValuesParticles.PARTICLE_ICON_SUNA,
						posX + (x * 1.25), 
						posY + 0.5 + y,
						posZ + (z * 1.25), 
						motionX, 
						motionY, 
						motionZ)
						.setParticleAge(-3).setParticleScale(3.3F);
				Minecraft.getInstance().particles.addEffect(cp);
				
				cp = new SimpleParticle(world, ModValuesParticles.PARTICLE_ICON_SUNA,
						posX + (x * 1.25), 
						posY + 2.5 + y,
						posZ + (z * 1.25), 
						motionX, 
						-motionY, 
						motionZ)
						.setParticleAge(-3).setParticleScale(3.3F);
				Minecraft.getInstance().particles.addEffect(cp);
			}
		}
	}

}
