package xyz.pixelatedw.mineminenomi.particles.effects.kachi;

import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;

public class EvaporateParticleEffect extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		for (int i = 0; i < 3; i++)
		{
			double offsetX = WyHelper.randomDouble();
			double offsetY = WyHelper.randomDouble();
			double offsetZ = WyHelper.randomDouble();
			
	        motionX = WyHelper.randomWithRange(0, 1) + WyHelper.randomDouble();
	        motionY = WyHelper.randomWithRange(0, 1) + WyHelper.randomDouble();
	        motionZ = WyHelper.randomWithRange(0, 1) + WyHelper.randomDouble();
	        
            double middlePoint = 0.5D / (5 / 0.5);
            middlePoint *= (WyHelper.randomDouble() * 2) + 0.3F;
	        
	        motionX *= middlePoint / 2;
	        motionY *= middlePoint / 2;
	        motionZ *= middlePoint / 2;
			
			GenericParticleData data = new GenericParticleData();
			data.setTexture(ModResources.MOKU);
			data.setLife(10);
			data.setSize(2.5f);
			data.setMotion(motionX, motionY + 0.05, motionZ);
			WyHelper.spawnParticles(data, (ServerWorld) world, posX + 1.5 + offsetX, posY + offsetY, posZ + offsetZ);

			offsetX = WyHelper.randomDouble();
			offsetY = WyHelper.randomDouble();
			offsetZ = WyHelper.randomDouble();
			
			data = new GenericParticleData();
			data.setTexture(ModResources.MERA);
			data.setLife(10);
			data.setSize(2.5f);
			data.setMotion(motionX, motionY + 0.05, motionZ);
			WyHelper.spawnParticles(data, (ServerWorld) world, posX + 1.5 + offsetX, posY + offsetY, posZ + offsetZ);
		}
	}
}