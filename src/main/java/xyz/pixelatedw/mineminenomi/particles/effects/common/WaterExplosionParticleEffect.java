package xyz.pixelatedw.mineminenomi.particles.effects.common;

import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;

public class WaterExplosionParticleEffect extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{	
		for (int i = 0; i < 512; i++)
		{
	        motionX = WyHelper.randomWithRange(-5, 5) + WyHelper.randomDouble();
	        motionY = WyHelper.randomWithRange(-5, 5) + WyHelper.randomDouble();
			motionZ = WyHelper.randomWithRange(-5, 5) + WyHelper.randomDouble();
	        
            double middlePoint = 0.25;
            middlePoint *= (WyHelper.randomDouble() * 2) + 0.3F;
	        
	        motionX *= middlePoint / 2;
	        motionY *= middlePoint / 2;
	        motionZ *= middlePoint / 2;
			
            world.addParticle(ParticleTypes.FISHING, posX, posY, posZ, motionX, motionY, motionZ);
            world.addParticle(ParticleTypes.FISHING, posX, posY, posZ, motionX, motionY, motionZ);
		}
	}
}
