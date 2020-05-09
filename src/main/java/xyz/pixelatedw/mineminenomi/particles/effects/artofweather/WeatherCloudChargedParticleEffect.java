package xyz.pixelatedw.mineminenomi.particles.effects.artofweather;

import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;

public class WeatherCloudChargedParticleEffect extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		for (int i = 0; i < 50; i++)
		{
			double offsetX = WyHelper.randomWithRange(-14, 14) + WyHelper.randomDouble();
			double offsetY = (WyHelper.randomWithRange(-2, 1) + WyHelper.randomDouble()) - 4;
			double offsetZ = WyHelper.randomWithRange(-14, 14) + WyHelper.randomDouble();

			GenericParticleData data = new GenericParticleData();
			data.setTexture(ModResources.GORO2);
			data.setLife(10);
			data.setSize(6);
			WyHelper.spawnParticles(data, (ServerWorld) world, posX + offsetX, posY + offsetY, posZ + offsetZ);
		}
	}

}
