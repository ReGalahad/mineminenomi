package xyz.pixelatedw.mineminenomi.particles.effects.common;

import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;

public class ParticleEffectCommonExplosion extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{	

		for (int i = 0; i < 1; i++)
		{
			int explosionSize = 2;
			
			double x = posX + WyMathHelper.randomDouble();
			double y = posY + WyMathHelper.randomDouble();
			double z = posZ + WyMathHelper.randomDouble();

	        motionX = WyMathHelper.randomWithRange(-2, 2) + WyMathHelper.randomDouble();
	        motionY = WyMathHelper.randomWithRange(-2, 2) + WyMathHelper.randomDouble();
	        motionZ = WyMathHelper.randomWithRange(-2, 2) + WyMathHelper.randomDouble();
	        
            double middlePoint = 0.5D / (5 / explosionSize + 0.1D);
            middlePoint *= (WyMathHelper.randomDouble() * 2) + 0.3F;
	        
	        motionX *= middlePoint / 2;
	        motionY *= middlePoint / 2;
	        motionZ *= middlePoint / 2;
			
	        world.addParticle(ParticleTypes.EXPLOSION, x, y, z, 0, 0, 0);
            world.addParticle(ParticleTypes.POOF, posX, posY + 1, posZ, motionX, motionY, motionZ);
		}
	}

}