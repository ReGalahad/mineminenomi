package xyz.pixelatedw.mineminenomi.particles.effects.suna;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.particles.CustomParticle;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.values.ModValuesParticles;

public class ParticleEffectDesertEncierro extends ParticleEffect
{

	@Override
	public void spawn(PlayerEntity player, double posX, double posY, double posZ)
	{
		double t = 0;
		double x, y, z;
		Random rand = player.getRNG();

		while(t < 1)
		{
			t += 0.5 * Math.PI;
			
			for(double theta = 0; theta <= 4 * Math.PI; theta += Math.PI / 32)
			{
				x = t * Math.cos(theta);
				y = rand.nextInt(1);
				z = t * Math.sin(theta);
										
				double motionX = -x / 10;
				double motionY = 0.1 + (rand.nextDouble() / 10);
				double motionZ = -z / 10;

				CustomParticle cp = new CustomParticle(player.world, ModValuesParticles.PARTICLE_ICON_SUNA,
						posX + (x * 1.25), 
						posY + 0.5 + y,
						posZ + (z * 1.25), 
						motionX, 
						motionY, 
						motionZ)
						.setParticleAge(-3).setParticleScale(3.3F);
				Minecraft.getInstance().particles.addEffect(cp);
				
				cp = new CustomParticle(player.world, ModValuesParticles.PARTICLE_ICON_SUNA,
						posX + (x * 1.25), 
						posY + 2.5 + y,
						posZ + (z * 1.25), 
						motionX, 
						-motionY, 
						motionZ)
						.setParticleAge(-3).setParticleScale(3.3F);
				Minecraft.getInstance().particles.addEffect(cp);
			}
		}
	}

}
