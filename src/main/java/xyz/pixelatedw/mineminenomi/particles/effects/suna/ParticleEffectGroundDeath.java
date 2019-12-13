package xyz.pixelatedw.mineminenomi.particles.effects.suna;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.particles.CustomParticle;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.values.ModValuesParticles;

public class ParticleEffectGroundDeath extends ParticleEffect
{

	@Override
	public void spawn(PlayerEntity player, double posX, double posY, double posZ)
	{		
		double t = 0;
		double x, y, z;
		Random rand = player.getRNG();

		while(t < 2)
		{
			t += 0.1 * Math.PI;
			
			for(double theta = 0; theta <= 4 * Math.PI; theta += Math.PI / 32)
			{
				x = t * Math.cos(theta);
				y = rand.nextInt(1);
				z = t * Math.sin(theta);
										
				double motionX = x / 2 + WyMathHelper.randomDouble();
				double motionY = 0;
				double motionZ = z / 2 + WyMathHelper.randomDouble();

				CustomParticle cp = new CustomParticle(player.world, ModValuesParticles.PARTICLE_ICON_SUNA2,
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
