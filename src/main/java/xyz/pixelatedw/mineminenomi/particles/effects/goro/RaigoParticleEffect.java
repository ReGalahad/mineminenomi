package xyz.pixelatedw.mineminenomi.particles.effects.goro;

import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;

public class RaigoParticleEffect extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		for (int i = 0; i < 75; i++)
		{
			double offsetX = WyMathHelper.randomWithRange(-55, 55) + WyMathHelper.randomDouble();
			double offsetY = 40 + (WyMathHelper.randomWithRange(-5, 5) + WyMathHelper.randomDouble());
			double offsetZ = WyMathHelper.randomWithRange(-55, 55) + WyMathHelper.randomDouble();

			GenericParticleData data = new GenericParticleData();
			data.setTexture(ModResources.GORO3);
			data.setLife(100);
			data.setSize(100);
			((ServerWorld) world).spawnParticle(data, posX + offsetX, posY + offsetY, posZ + offsetZ, 1, 0, 0, 0, 0.0D);

			if (i % 2 == 0)
				data.setColor(0.4F, 0.4F, 0.4F);
			else
				data.setColor(0.3F, 0.3F, 0.3F);
		}

		for (int i = 0; i < 25; i++)
		{
			double offsetX = WyMathHelper.randomWithRange(-55, 55) + WyMathHelper.randomDouble();
			double offsetY = 30 + (WyMathHelper.randomWithRange(-5, 0) + WyMathHelper.randomDouble());
			double offsetZ = WyMathHelper.randomWithRange(-55, 55) + WyMathHelper.randomDouble();

			GenericParticleData data = new GenericParticleData();
			data.setTexture(ModResources.GORO2);
			data.setLife(10);
			data.setSize(40);
			((ServerWorld) world).spawnParticle(data, posX + offsetX, posY + offsetY, posZ + offsetZ, 1, 0, 0, 0, 0.0D);
		}
	}

}
