package xyz.pixelatedw.mineminenomi.particles.effects.mera;

import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;

public class HeatDashParticleEffect extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		for (int i = 0; i < 40; i++)
		{
			double offsetX = WyHelper.randomDouble() / 1.3;
			double offsetY = WyHelper.randomDouble() / 1.3;
			double offsetZ = WyHelper.randomDouble() / 1.3;  
			
			GenericParticleData data = new GenericParticleData();
			data.setTexture(ModResources.MERA);
			data.setLife(10);
			data.setSize(1.3F);
			data.setMotion(offsetX / 5, (offsetY / 5) + 0.05, offsetZ / 5);
			WyHelper.spawnParticles(data, (ServerWorld) world, posX + offsetX, posY + 1 + offsetY, posZ + offsetZ);
		}
	}

}
