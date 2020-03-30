package xyz.pixelatedw.mineminenomi.particles.effects.swordsman;

import java.util.Random;

import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;

public class OTatsumakiParticleEffect extends ParticleEffect
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
				z = t * Math.sin(theta);

				motionX = x / 6;
				motionY = -0.1 + (rand.nextDouble() / 10);
				motionZ = z / 6;

				((ServerWorld)world).spawnParticle(ParticleTypes.SNEEZE, posX + (x * 1.85), posY + 1.2, posZ + (z * 1.85), 1, motionX, motionY, motionZ, 0.03);			
				((ServerWorld)world).spawnParticle(ParticleTypes.SNEEZE, posX + (x * 1.85), posY + 2.2, posZ + (z * 1.85), 1, motionX, motionY, motionZ, 0.03);
			}
		}
	}

}