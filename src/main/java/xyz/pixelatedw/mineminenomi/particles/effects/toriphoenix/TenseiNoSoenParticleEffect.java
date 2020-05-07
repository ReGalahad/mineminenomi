package xyz.pixelatedw.mineminenomi.particles.effects.toriphoenix;

import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;

public class TenseiNoSoenParticleEffect extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		posY += 1.5;
		
		double radius = 1;
		double phi = 0;

		while(phi < Math.PI)
		{
			phi += Math.PI / 4;
			
			for(double theta = 0; theta <= 2 * Math.PI; theta += Math.PI / 2)
			{
				double x = (radius * Math.cos(theta) * Math.sin(phi)) + WyHelper.randomDouble();
				double y = radius * Math.cos(phi) + WyHelper.randomDouble();
				double z = (radius* Math.sin(theta) * Math.sin(phi)) + WyHelper.randomDouble();

				GenericParticleData data = new GenericParticleData();
				data.setTexture(ModResources.BLUE_FLAME);
				data.setLife(20);
				data.setSize(2F);
				data.setMotion(0, 0.02, 0);
				WyHelper.spawnParticles(data, (ServerWorld) world, posX + x, posY - 1 + y, posZ + z);			
			}
		}
	}

}
