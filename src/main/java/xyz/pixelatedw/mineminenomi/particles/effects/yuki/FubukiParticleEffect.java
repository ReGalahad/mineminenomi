package xyz.pixelatedw.mineminenomi.particles.effects.yuki;

import java.util.Random;

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
			double offsetX = (new Random().nextInt(50) + 1.0D - 25.0D) / 1.0D;
			double offsetY = (new Random().nextInt(50) + 1.0D - 25.0D) / 1.0D;
			double offsetZ = (new Random().nextInt(50) + 1.0D - 25.0D) / 1.0D;

			motionX = WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble();
			motionZ = WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble();

			double middlePoint = 0.2D;
			middlePoint *= (WyHelper.randomDouble() * 2) + 0.3F;

			motionX *= middlePoint / 2;
			motionZ *= middlePoint / 2;
		
			float scale = (float) (1 + WyHelper.randomWithRange(0, 2));
			
			GenericParticleData data = new GenericParticleData();
			data.setTexture(ModResources.YUKI);
			data.setLife(300);
			data.setSize(scale);
			data.setMotion(motionX, -0.3, motionY);
			WyHelper.spawnParticles(data, (ServerWorld) world, posX + offsetX, posY + offsetY, posZ + offsetZ);
		}		
	}

}
