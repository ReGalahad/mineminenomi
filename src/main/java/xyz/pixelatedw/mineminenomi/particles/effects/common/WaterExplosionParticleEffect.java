package xyz.pixelatedw.mineminenomi.particles.effects.common;

import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;

public class WaterExplosionParticleEffect extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{	
		for (int i = 0; i < 512; i++)
		{
	        motionX = WyMathHelper.randomWithRange(-5, 5) + WyMathHelper.randomDouble();
	        motionY = WyMathHelper.randomWithRange(-5, 5) + WyMathHelper.randomDouble();
			motionZ = WyMathHelper.randomWithRange(-5, 5) + WyMathHelper.randomDouble();
	        
            double middlePoint = 0.25;
            middlePoint *= (WyMathHelper.randomDouble() * 2) + 0.3F;
	        
	        motionX *= middlePoint / 2;
	        motionY *= middlePoint / 2;
	        motionZ *= middlePoint / 2;
			
            world.addParticle(ParticleTypes.FISHING, posX, posY, posZ, motionX, motionY, motionZ);
            world.addParticle(ParticleTypes.FISHING, posX, posY, posZ, motionX, motionY, motionZ);
		}
	}
}
