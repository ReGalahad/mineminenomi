package xyz.pixelatedw.mineminenomi.particles.effects.artofweather;

import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;

public class WeatherCloudParticleEffect extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		for (int i = 0; i < 55; i++)
		{
			double offsetX = WyHelper.randomWithRange(-15, 15) + WyHelper.randomDouble();
			double offsetY = WyHelper.randomWithRange(-2, 0) + WyHelper.randomDouble();
			double offsetZ = WyHelper.randomWithRange(-15, 15) + WyHelper.randomDouble();

			GenericParticleData data = new GenericParticleData();
			data.setLife((int) (40 + WyHelper.randomDouble()));
			data.setSize(15);
			
			if (i % 2 == 0)
			{
				data.setColor(0.5F, 0.5F, 0.5F);
				data.setTexture(ModResources.MOKU2);
			}
			else
			{
				data.setColor(0.3F, 0.3F, 0.3F);
				data.setTexture(ModResources.MOKU);
			}

			WyHelper.spawnParticles(data, (ServerWorld) world, posX + offsetX, posY + offsetY, posZ + offsetZ);
		}
	}

}
