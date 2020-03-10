package xyz.pixelatedw.mineminenomi.particles.effects.pika;

import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;

public class AmaterasuParticleEffect extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		for (int i = 0; i < 2; i++)
		{
			double offsetX = WyHelper.randomDouble() * 1.55;
			double offsetY = WyHelper.randomDouble();
			double offsetZ = WyHelper.randomDouble() * 1.55;
			
			GenericParticleData data = new GenericParticleData();
			data.setTexture(ModResources.PIKA);
			data.setLife(5);
			data.setSize(3F);
			data.setMotion(0, 0.15, 0);
			data.setHasRotation();
			WyHelper.spawnParticles(data, (ServerWorld) world, posX + offsetX, posY + 0.5 + offsetY, posZ + offsetZ);
		}
	}

}
