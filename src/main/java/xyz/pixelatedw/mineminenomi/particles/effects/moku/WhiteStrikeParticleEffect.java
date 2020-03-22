package xyz.pixelatedw.mineminenomi.particles.effects.moku;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;

public class WhiteStrikeParticleEffect extends ParticleEffect
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
			
			for(double theta = 0; theta <= 4 * Math.PI; theta += Math.PI / 16)
			{
				x = t * Math.cos(theta);
				y = rand.nextInt(1);
				z = t * Math.sin(theta);
										
				motionX = x / 7;
				motionY = 0.02 + (rand.nextDouble() / 10);
				motionZ = z / 7;

				GenericParticleData data = new GenericParticleData();
				data.setTexture(ModResources.MOKU);
				data.setLife(3);
				data.setSize(1.3F);
				data.setMotion(motionX, motionY, motionZ);
				WyHelper.spawnParticles(data, (ServerWorld) world, posX + (x * 1.65) + WyHelper.randomDouble(), posY + y, posZ + (z * 1.65) + WyHelper.randomDouble());
			}
		}
	}
	
}