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
				data.setLife((int) (50 + WyHelper.randomDouble()));
				data.setSize(25);
				
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
	
				WyHelper.spawnParticles(data, (ServerWorld) world, posX + x, posY + y, posZ + z);
				i++;
			}
		}
	}
}
