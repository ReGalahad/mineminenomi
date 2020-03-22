package xyz.pixelatedw.mineminenomi.particles.effects.goro;

import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;

public class KariParticleEffect extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		for (int i = 0; i < 20; i++)
		{
			double offsetX = WyHelper.randomWithRange(-5, 5) + WyHelper.randomDouble();
			double offsetY = WyHelper.randomWithRange(0, 3) + WyHelper.randomDouble();
			double offsetZ = WyHelper.randomWithRange(-5, 5) + WyHelper.randomDouble();
			
			GenericParticleData data = new GenericParticleData();
			data.setTexture(ModResources.GORO2);
			data.setLife(10);
			data.setSize(7);
			WyHelper.spawnParticles(data, (ServerWorld) world, posX + offsetX, posY + offsetY, posZ + offsetZ);
		}
	}
	
}
