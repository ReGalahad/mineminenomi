package xyz.pixelatedw.mineminenomi.particles.effects.doku;

import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;

public class ChloroBallCloudParticleEffect extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		for (int i = 0; i < 64; i++)
		{
			double offsetX = WyHelper.randomWithRange(-2, 2) + WyHelper.randomDouble();
			double offsetY = WyHelper.randomWithRange(-1, 2) + WyHelper.randomDouble();
			double offsetZ = WyHelper.randomWithRange(-2, 2) + WyHelper.randomDouble();
			
			motionX = WyHelper.randomDouble() / 8;
			motionZ = WyHelper.randomDouble() / 8;
			
			GenericParticleData data = new GenericParticleData();
			data.setTexture(ModResources.DOKU);
			data.setLife(5);
			data.setSize(1.5F);
			data.setMotion(motionX, 0.05, motionZ);
			WyHelper.spawnParticles(data, (ServerWorld) world, posX + offsetX, posY + offsetY, posZ + offsetZ);
		}	
	}

}
