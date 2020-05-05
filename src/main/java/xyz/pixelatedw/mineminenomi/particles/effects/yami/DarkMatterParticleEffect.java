package xyz.pixelatedw.mineminenomi.particles.effects.yami;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;

public class DarkMatterParticleEffect extends ParticleEffect
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

				motionX = -x / 3;
				motionY = -0.1 + (rand.nextDouble() / 10);
				motionZ = -z / 3;

				GenericParticleData data = new GenericParticleData();
				data.setTexture(ModResources.DARKNESS);
				data.setLife(10);
				data.setSize(3.3F);
				data.setMotion(motionX, motionY, motionZ);
				WyHelper.spawnParticles(data, (ServerWorld) world, posX + (x * 5.25), posY + 1.2, posZ + (z * 5.25));
				
				data = new GenericParticleData();
				data.setTexture(ModResources.DARKNESS);
				data.setLife(10);
				data.setSize(3.3F);
				data.setMotion(motionX, motionY, motionZ);
				WyHelper.spawnParticles(data, (ServerWorld) world, posX + (x * 5.25), posY + 3.2, posZ + (z * 5.25));
			}
		}
	}

}
