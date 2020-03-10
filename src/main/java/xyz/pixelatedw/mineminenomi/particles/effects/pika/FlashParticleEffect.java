package xyz.pixelatedw.mineminenomi.particles.effects.pika;

import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;

public class FlashParticleEffect extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		for (int i = 0; i < 10; i++)
		{
			double offsetX = WyHelper.randomDouble() / 1.25;
			double offsetY = WyHelper.randomDouble() * 1.25;
			double offsetZ = WyHelper.randomDouble() / 1.25;
			
			GenericParticleData data = new GenericParticleData();
			data.setTexture(ModResources.PIKA);
			data.setLife(5);
			data.setSize(4F);
			data.setHasRotation();
			WyHelper.spawnParticles(data, (ServerWorld) world, posX + offsetX, posY + offsetY, posZ + offsetZ);	
		}
	}

}
