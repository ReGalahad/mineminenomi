package xyz.pixelatedw.mineminenomi.particles.effects.yomi;

import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;

public class KasuriutaFubukiGiriParticleEffect extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		for (int i = 0; i < 2; i++)
		{
			double offsetX = WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble();
			double offsetY = WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble();
			double offsetZ = WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble();
	      
			GenericParticleData data = new GenericParticleData();
			data.setTexture(ModResources.HIE);
			data.setLife(20);
			data.setSize(1.3F);
			WyHelper.spawnParticles(data, (ServerWorld) world, posX + offsetX, posY + 1.5 + offsetY, posZ + offsetZ);
		}
	}
}
