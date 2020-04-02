package xyz.pixelatedw.mineminenomi.particles.effects.doctor;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;

public class MedicBagExplosionParticleEffect extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		double t = 0;
		double x, y, z;
		Random rand = world.rand;

		while(t < 1)
		{
			t += 0.5 * Math.PI;
			
			for(double theta = 0; theta <= 4 * Math.PI; theta += Math.PI / 32)
			{
				x = t * Math.cos(theta);
				y = rand.nextInt(1);
				z = t * Math.sin(theta);
										
				motionX = x / 4;
				motionY = 0.05 + (rand.nextDouble() / 7);
				motionZ = z / 4;

				GenericParticleData data = new GenericParticleData();
				data.setTexture(ModResources.YUKI);
				data.setLife(4);
				data.setSize(2F);
				data.setMotion(motionX, motionY, motionZ);
				data.setColor(0, 0, 0.8F);
				WyHelper.spawnParticles(data, (ServerWorld) world, posX + (x * 0.75) + WyHelper.randomDouble(), posY + y, posZ + (z * 0.75) + WyHelper.randomDouble());
				
				data = new GenericParticleData();
				data.setTexture(ModResources.YUKI);
				data.setLife(7);
				data.setSize(2.5F);
				data.setMotion(motionX, motionY, motionZ);
				data.setColor(0, 0, 0.8F);
				WyHelper.spawnParticles(data, (ServerWorld) world, posX + (x * 2.0) + WyHelper.randomDouble(), posY + y, posZ + (z * 2.0) + WyHelper.randomDouble());

				data = new GenericParticleData();
				data.setTexture(ModResources.YUKI);
				data.setLife(10);
				data.setSize(4.5F);
				data.setMotion(motionX, motionY * 2.25, motionZ);
				data.setColor(0, 0, 0.8F);
				WyHelper.spawnParticles(data, (ServerWorld) world, posX + (x * 3.25) + WyHelper.randomDouble(), posY + y, posZ + (z * 3.25) + WyHelper.randomDouble());
			}
		}
	}

}
