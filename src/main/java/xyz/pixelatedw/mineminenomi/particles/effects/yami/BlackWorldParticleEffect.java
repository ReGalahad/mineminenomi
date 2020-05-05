package xyz.pixelatedw.mineminenomi.particles.effects.yami;

import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;

public class BlackWorldParticleEffect extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		for (int i = 0; i < 500; i++)
		{
			double offsetX = WyHelper.randomWithRange(-10, 10) + WyHelper.randomDouble();
			double offsetY = WyHelper.randomWithRange(0, 2) + WyHelper.randomDouble();
			double offsetZ = WyHelper.randomWithRange(-10, 10) + WyHelper.randomDouble();
			
			GenericParticleData data = new GenericParticleData();
			data.setTexture(ModResources.DARKNESS);
			data.setLife(30 + world.rand.nextInt(10));
			data.setSize(1.2F);
			data.setMotion(0, 0.02, 0);
			WyHelper.spawnParticles(data, (ServerWorld) world, posX + offsetX, posY + offsetY, posZ + offsetZ);

			data = new GenericParticleData();
			data.setTexture(ModResources.DARKNESS);
			data.setLife(30 + world.rand.nextInt(10));
			data.setSize(1.2F);
			data.setMotion(0, 0.02, 0);
			WyHelper.spawnParticles(data, (ServerWorld) world, posX + offsetX, posY + 0.5 + offsetY, posZ + offsetZ);

			data = new GenericParticleData();
			data.setTexture(ModResources.DARKNESS);
			data.setLife(30 + world.rand.nextInt(10));
			data.setSize(1.2F);
			data.setMotion(0, 0.02, 0);
			WyHelper.spawnParticles(data, (ServerWorld) world, posX + offsetX, posY + 1.5 + offsetY, posZ + offsetZ);
		}
	}

}
