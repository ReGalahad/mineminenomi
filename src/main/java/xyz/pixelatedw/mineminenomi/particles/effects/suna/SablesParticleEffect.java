package xyz.pixelatedw.mineminenomi.particles.effects.suna;

import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;

public class SablesParticleEffect extends ParticleEffect
{
	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		double phi = 0;
		double x, y, z;

		while (phi < Math.PI * 2)
		{
			phi += Math.PI / 16;
			for (double t = 0; t <= 2 * Math.PI; t += Math.PI / 16)
			{
				for (double i = 0; i <= 1; i += 1)
				{
					x = 0.35 * (2 * Math.PI + t) * Math.cos(t * phi + i * Math.PI);
					y = 0.7 * t;
					z = 0.35 * (2 * Math.PI + t) * Math.sin(t * phi - i * Math.PI);

					GenericParticleData data = new GenericParticleData();
					data.setTexture(ModResources.SUNA2);
					data.setLife(20);
					data.setSize(1.3F);
					data.setMotion(motionX, motionY + 0.01 + (Math.abs(WyHelper.randomDouble()) / 5), motionZ);
					WyHelper.spawnParticles(data, (ServerWorld) world, posX + x, posY + y, posZ + z);
				}
			}
		}
	}

}
