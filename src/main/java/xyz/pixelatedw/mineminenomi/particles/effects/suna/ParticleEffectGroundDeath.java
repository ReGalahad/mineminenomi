package xyz.pixelatedw.mineminenomi.particles.effects.suna;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.init.ModParticleTextures;
import xyz.pixelatedw.mineminenomi.particles.SimpleParticle;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;

public class ParticleEffectGroundDeath extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{		
		double t = 0;
		double x, y, z;

		while(t < 2)
		{
			t += 0.1 * Math.PI;
			
			for(double theta = 0; theta <= 4 * Math.PI; theta += Math.PI / 32)
			{
				x = t * Math.cos(theta);
				y = WyMathHelper.randomDouble();
				z = t * Math.sin(theta);
										
				motionX = x / 2 + WyMathHelper.randomDouble();
				motionY = 0;
				motionZ = z / 2 + WyMathHelper.randomDouble();

				SimpleParticle cp = new SimpleParticle(world, ModParticleTextures.SUNA2,
						posX + (x * 1.25), 
						posY + 0.5 + y,
						posZ + (z * 1.25), 
						motionX,
						motionY, 
						motionZ)
						.setParticleAge(10).setParticleScale(3F);
				Minecraft.getInstance().particles.addEffect(cp);
			}
		}
	}

}
