package xyz.pixelatedw.mineminenomi.particles.effects.common;

import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;

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
			double x = posX + WyMathHelper.randomWithRange(-this.explosionSize / 2, this.explosionSize / 2) + WyMathHelper.randomDouble();
			double y = posY + WyMathHelper.randomDouble();
			double z = posZ + WyMathHelper.randomWithRange(-this.explosionSize / 2, this.explosionSize / 2) + WyMathHelper.randomDouble();

	        motionX = WyMathHelper.randomWithRange(-2, 2) + WyMathHelper.randomDouble();
	        motionY = WyMathHelper.randomWithRange(-2, 2) + WyMathHelper.randomDouble();
	        motionZ = WyMathHelper.randomWithRange(-2, 2) + WyMathHelper.randomDouble();
	        
            double middlePoint = 0.5D / (5 / this.explosionSize + 0.1D);
            middlePoint *= (WyMathHelper.randomDouble() * 2) + 0.3F;
	        
	        motionX *= middlePoint / 2;
	        motionY *= middlePoint / 2;
	        motionZ *= middlePoint / 2;

	        ((ServerWorld) world).spawnParticle(ParticleTypes.EXPLOSION, x, y, z, 1, 0, 0, 0, 1);
	        ((ServerWorld) world).spawnParticle(ParticleTypes.POOF, posX, posY + 1, posZ, 1, motionX, motionY, motionZ, .5);
		}
	}

}