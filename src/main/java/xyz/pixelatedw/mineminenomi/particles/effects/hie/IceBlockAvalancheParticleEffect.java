package xyz.pixelatedw.mineminenomi.particles.effects.hie;

import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;

public class IceBlockAvalancheParticleEffect extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		double density = 6;
		double phi = 0;

		while (phi < Math.PI)
		{
			phi += Math.PI / 4;

			for (double theta = 0; theta <= 2 * Math.PI; theta += Math.PI / 6)
			{
				double x = (3 * Math.cos(theta) * Math.sin(phi)) + WyHelper.randomDouble();
				double y = posY - 3 - 1;
				double z = (3 * Math.sin(theta) * Math.sin(phi)) + WyHelper.randomDouble();
				motionX = x / 40;
				motionY = 0.1;
				motionZ = z / 40;

				GenericParticleData data = new GenericParticleData();
				data.setTexture(ModResources.HIE);
				data.setLife(8);
				data.setSize(10f);

				data.setMotion(-motionX, motionY, -motionZ);
				if (Math.random() > 0.7)
				{
					WyHelper.spawnParticles(data, (ServerWorld) world, posX + x, y, posZ + z);
				}

			}
		}
	}

}
