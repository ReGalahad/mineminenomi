package xyz.pixelatedw.mineminenomi.particles.effects.goro;

import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;

public class RaigoParticleEffect extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		for (int i = 0; i < 55; i++)
		{
			double offsetX = WyHelper.randomWithRange(-55, 55) + WyHelper.randomDouble();
			double offsetY = 40 + (WyHelper.randomWithRange(-5, 5) + WyHelper.randomDouble());
			double offsetZ = WyHelper.randomWithRange(-55, 55) + WyHelper.randomDouble();

			GenericParticleData data = new GenericParticleData();
			data.setTexture(ModResources.GORO3);
			data.setLife(100);
			data.setSize(100);
			WyHelper.spawnParticles(data, (ServerWorld) world, posX + offsetX, posY + offsetY, posZ + offsetZ);

			if (i % 2 == 0)
				data.setColor(0.4F, 0.4F, 0.4F);
			else
				data.setColor(0.3F, 0.3F, 0.3F);
		}

		for (int i = 0; i < 25; i++)
		{
			double offsetX = WyHelper.randomWithRange(-55, 55) + WyHelper.randomDouble();
			double offsetY = 30 + (WyHelper.randomWithRange(-5, 0) + WyHelper.randomDouble());
			double offsetZ = WyHelper.randomWithRange(-55, 55) + WyHelper.randomDouble();

			GenericParticleData data = new GenericParticleData();
			data.setTexture(ModResources.GORO2);
			data.setLife(10);
			data.setSize(40);
			WyHelper.spawnParticles(data, (ServerWorld) world, posX + offsetX, posY + offsetY, posZ + offsetZ);
		}
	}

}
