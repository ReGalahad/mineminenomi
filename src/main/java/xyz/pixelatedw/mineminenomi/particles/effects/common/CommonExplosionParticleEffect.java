package xyz.pixelatedw.mineminenomi.particles.effects.common;

import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;

public class CommonExplosionParticleEffect extends ParticleEffect
{

	private int explosionSize;

	public CommonExplosionParticleEffect()
	{
		this(2);
	}

	public CommonExplosionParticleEffect(int explosionSize)
	{
		this.explosionSize = explosionSize;
	}

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		for (int i = 0; i < this.explosionSize * 2; i++)
		{
			double x = posX + WyHelper.randomWithRange(-this.explosionSize / 2, this.explosionSize / 2) + WyHelper.randomDouble();
			double y = posY + WyHelper.randomDouble();
			double z = posZ + WyHelper.randomWithRange(-this.explosionSize / 2, this.explosionSize / 2) + WyHelper.randomDouble();

			motionX = WyHelper.randomWithRange(-2, 2) + WyHelper.randomDouble();
			motionY = WyHelper.randomWithRange(-2, 2) + WyHelper.randomDouble();
			motionZ = WyHelper.randomWithRange(-2, 2) + WyHelper.randomDouble();

			double middlePoint = 0.5D / (5 / this.explosionSize + 0.1D);
			middlePoint *= (WyHelper.randomDouble() * 2) + 0.3F;

			motionX *= middlePoint / 2;
			motionY *= middlePoint / 2;
			motionZ *= middlePoint / 2;

			WyHelper.spawnParticles(ParticleTypes.EXPLOSION, (ServerWorld) world, x, y, z);
			WyHelper.spawnParticles(ParticleTypes.POOF, (ServerWorld) world, posX, posY + 1, posZ);
		}
	}

}