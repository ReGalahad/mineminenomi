package xyz.pixelatedw.mineminenomi.particles.effects.yami;

import java.util.Random;

import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;

public class BlackHoleParticleEffect extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		double t = 0;
		double x, y, z;
		Random rand = world.rand;

		while(t < 3)
		{
			t += 0.1 * Math.PI;
			
			for(double theta = 0; theta <= 4 * Math.PI; theta += Math.PI / 16)
			{
				x = t * Math.cos(theta);
				y = rand.nextInt(1);
				z = t * Math.sin(theta);
										
				motionX = x / 4;
				motionY = 0.05 + (MathHelper.abs((float) WyHelper.randomDouble() / 12));
				motionZ = z / 4;

				GenericParticleData data = new GenericParticleData();
				data.setTexture(ModResources.DARKNESS);
				data.setLife(20);
				data.setSize(2F);
				data.setMotion(motionX, motionY, motionZ);
				WyHelper.spawnParticles(data, (ServerWorld) world, posX + (x * 1.25) + WyHelper.randomDouble(), posY + y, posZ + (z * 1.25) + WyHelper.randomDouble());
			}
		}
	}

}
