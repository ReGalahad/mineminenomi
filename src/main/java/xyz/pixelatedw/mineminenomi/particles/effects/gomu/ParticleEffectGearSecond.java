package xyz.pixelatedw.mineminenomi.particles.effects.gomu;

import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;

public class ParticleEffectGearSecond extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		for (int i = 0; i < 2; i++)
		{
			double offsetX = WyMathHelper.randomDouble();
			double offsetY = WyMathHelper.randomDouble();
			double offsetZ = WyMathHelper.randomDouble();
	      
			world.addParticle(ParticleTypes.EXPLOSION, posX + offsetX, (posY + 0.5) + offsetY, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
		}	
	}

}
