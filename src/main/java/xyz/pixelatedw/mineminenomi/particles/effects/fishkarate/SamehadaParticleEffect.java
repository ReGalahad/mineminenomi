package xyz.pixelatedw.mineminenomi.particles.effects.fishkarate;

import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;

public class SamehadaParticleEffect extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		posY += 1.5;
		
		double radius = 1;
		double density = 6;
		double phi = 0;

		while(phi < Math.PI)
		{
			phi += Math.PI / 4;
			
			for(double theta = 0; theta <= 2 * Math.PI; theta += Math.PI / 6)
			{
				double x = radius * Math.cos(theta) * Math.sin(phi);
				double y = radius * Math.cos(phi);
				double z = radius* Math.sin(theta) * Math.sin(phi);

				world.addParticle(ParticleTypes.SPLASH, posX + x, posY + y, posZ + z, 0.0D, 0.0D, 0.0D);
			}
		}
	}

}
