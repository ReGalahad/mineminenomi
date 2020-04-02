package xyz.pixelatedw.mineminenomi.particles.effects.doctor;

import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;

public class FirstAidParticleEffect extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		for (int i = 0; i < 64; i++)
		{
			motionX = WyHelper.randomWithRange(-3, 3) + WyHelper.randomDouble();
			motionY = WyHelper.randomWithRange(-3, 3) + WyHelper.randomDouble();
			motionZ = WyHelper.randomWithRange(-3, 3) + WyHelper.randomDouble();

			double middlePoint = 0.1;
			middlePoint *= (world.rand.nextFloat() * 2) + 0.3F;

			motionX *= middlePoint / 2;
			motionY *= middlePoint / 2;
			motionZ *= middlePoint / 2;

			GenericParticleData data = new GenericParticleData();
			data.setTexture(ModResources.YUKI);
			data.setLife(10);
			data.setSize(1.5F);
			data.setColor(0, 0, 0.8F);
			WyHelper.spawnParticles(data, (ServerWorld) world, posX, posY + 1, posZ);
		}
	}

}
