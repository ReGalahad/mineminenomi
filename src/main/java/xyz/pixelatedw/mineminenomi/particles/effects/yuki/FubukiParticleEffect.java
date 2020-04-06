package xyz.pixelatedw.mineminenomi.particles.effects.yuki;

import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;

public class FubukiParticleEffect extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		for (int i = 0; i < 1024; i++)
		{
			double offsetX = WyHelper.randomWithRange(-15, 15) + WyHelper.randomDouble();
			double offsetY = WyHelper.randomWithRange(0, 20) + WyHelper.randomDouble();
			double offsetZ = WyHelper.randomWithRange(-15, 15) + WyHelper.randomDouble();

			motionX = WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble();
			motionZ = WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble();

			double middlePoint = 1.2D;

			motionX *= middlePoint / 25;
			motionZ *= middlePoint / 25;
		
			float scale = (float) (1 + WyHelper.randomWithRange(0, 3));
			
			GenericParticleData data = new GenericParticleData();
			data.setTexture(ModResources.YUKI);
			data.setLife(300);
			data.setSize(scale);
			data.setMotion(motionX, -0.05, motionY);
			data.setHasMotionDecay(false);
			WyHelper.spawnParticles(data, (ServerWorld) world, posX + offsetX, posY + offsetY, posZ + offsetZ);
		}		
	}

}
