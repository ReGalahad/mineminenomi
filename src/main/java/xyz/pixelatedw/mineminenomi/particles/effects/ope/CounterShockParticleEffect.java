package xyz.pixelatedw.mineminenomi.particles.effects.ope;

import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;

public class CounterShockParticleEffect extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{		
		for (int i = 0; i < 20; i++)
		{
			double x = WyHelper.randomDouble();
			double y = WyHelper.randomDouble();
			double z = WyHelper.randomDouble();

			motionY = 0.005 + Math.abs(WyHelper.randomDouble() / 8);
			
			GenericParticleData data = new GenericParticleData();
			data.setTexture(ModResources.GORO2);
			data.setLife(5);
			data.setSize(3.5F);
			data.setMotion(0, motionY, 0);
			WyHelper.spawnParticles(data, (ServerWorld) world, posX + x, posY + 1.5 + y, posZ + z);
		}
		
	}

}
