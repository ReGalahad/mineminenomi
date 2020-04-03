package xyz.pixelatedw.mineminenomi.particles.effects.doku;

import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;

public class DokuGumoParticleEffect extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		for (int i = 0; i < 30; i++)
		{
			double offsetX = WyHelper.randomWithRange(-3, 3) + WyHelper.randomDouble();
			double offsetY = WyHelper.randomWithRange(-3, 3) + WyHelper.randomDouble();
			double offsetZ = WyHelper.randomWithRange(-3, 3) + WyHelper.randomDouble();
			
			int age = (int) (1 + WyHelper.randomWithRange(0, 2));
			
			GenericParticleData data = new GenericParticleData();
			data.setTexture(ModResources.DOKU);
			data.setLife(age);
			data.setSize(1F);
			WyHelper.spawnParticles(data, (ServerWorld) world, posX + offsetX, posY + offsetY, posZ + offsetZ);
		}
	}

}
