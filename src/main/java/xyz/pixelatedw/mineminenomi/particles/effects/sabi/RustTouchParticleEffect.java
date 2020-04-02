package xyz.pixelatedw.mineminenomi.particles.effects.sabi;

import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;

public class RustTouchParticleEffect extends ParticleEffect
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
			middlePoint *= (WyHelper.randomDouble() * 2) + 0.3F;

			motionX *= middlePoint / 7;
			motionY *= middlePoint / 7;
			motionZ *= middlePoint / 7;

			GenericParticleData data = new GenericParticleData();
			data.setTexture(ModResources.RUST);
			data.setLife(10);
			data.setSize(1F);
			data.setMotion(motionX, motionY + 0.08, motionZ);
			WyHelper.spawnParticles(data, (ServerWorld) world, posX, posY + 1, posZ);
		}
	}
}