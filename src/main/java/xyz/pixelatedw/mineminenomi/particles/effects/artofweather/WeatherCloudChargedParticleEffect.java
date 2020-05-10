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
		int i = 0;
		double phi = 0;
		double radius = 7;

		while(phi < Math.PI)
		{
			phi += Math.PI / 1.5;
			
			for(double theta = 0; theta <= 2 * Math.PI; theta += Math.PI / 4)
			{
				double x = (radius * Math.cos(theta) * Math.sin(phi)) + (WyHelper.randomDouble() * radius);
				double y = radius * Math.cos(phi);
				double z = (radius* Math.sin(theta) * Math.sin(phi)) + (WyHelper.randomDouble() * radius);
							
				GenericParticleData data = new GenericParticleData();
				if(i % 4 == 0)
					data.setTexture(ModResources.GORO);
				else
					data.setTexture(ModResources.GORO2);
				data.setLife(10);
				data.setSize(6);
				WyHelper.spawnParticles(data, (ServerWorld) world, posX + x, (posY - 2) + y, posZ + z);
				i++;
			}
		}
	}

}
