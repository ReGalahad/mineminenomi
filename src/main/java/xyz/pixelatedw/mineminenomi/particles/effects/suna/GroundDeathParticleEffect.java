package xyz.pixelatedw.mineminenomi.particles.effects.suna;

import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;

public class GroundDeathParticleEffect extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{		
		double t = 0;
		double x, y, z;

		while(t < 1)
		{
			t += 0.1 * Math.PI;
			
			for(double theta = 0; theta <= 4 * Math.PI; theta += Math.PI / 16)
			{
				x = t * Math.cos(theta);
				y = WyHelper.randomDouble();
				z = t * Math.sin(theta);
										
				motionX = (WyHelper.randomDouble() / 3);
				motionY = 0;
				motionZ = (WyHelper.randomDouble() / 3);

				GenericParticleData data = new GenericParticleData();
				data.setTexture(ModResources.SUNA2);
				data.setLife(10);
				data.setSize(2.5F);
				data.setMotion(motionX, motionY, motionZ);
				WyHelper.spawnParticles(data, (ServerWorld) world, posX + (x * 2.25), posY + 0.5 + y, posZ + (z * 2.25));
			}
		}
	}

}
