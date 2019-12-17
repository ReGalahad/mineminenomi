package xyz.pixelatedw.mineminenomi.particles.effects.chiyu;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.particles.SimpleParticle;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.values.ModValuesParticles;

public class ParticleEffectChiyupopo extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		double t = 0;
		double x, y, z;
		Random rand = world.rand;

		while(t < 1)
		{
			t += 0.5 * Math.PI;
			
			for(double theta = 0; theta <= 4 * Math.PI; theta += Math.PI / 32)
			{
				x = t * Math.cos(theta);
				y = 0.2 + rand.nextInt(1);
				z = t * Math.sin(theta);
										
				motionX = x / 4;
				motionY = 0.05 + (rand.nextDouble() / 7);
				motionZ = z / 4;
				
				SimpleParticle cp = new SimpleParticle(world, ModValuesParticles.PARTICLE_ICON_CHIYU,
						posX + (x * 1.25) + WyMathHelper.randomDouble(), 
						posY + y,
						posZ + (z * 1.25) + WyMathHelper.randomDouble(), 
						motionX,
						motionY,
						motionZ)
						.setParticleScale(2F)
						.setParticleAge(-3);
				Minecraft.getInstance().particles.addEffect(cp);

				cp = new SimpleParticle(world, ModValuesParticles.PARTICLE_ICON_CHIYU,
						posX + (x * 2.0) + WyMathHelper.randomDouble(), 
						posY + y,
						posZ + (z * 2.0) + WyMathHelper.randomDouble(), 
						motionX,
						motionY,
						motionZ)
						.setParticleScale(2.5F)
						.setParticleAge(1);
				Minecraft.getInstance().particles.addEffect(cp);
				
				cp = new SimpleParticle(world, ModValuesParticles.PARTICLE_ICON_CHIYU,
						posX + (x * 3.25) + WyMathHelper.randomDouble(), 
						posY + y,
						posZ + (z * 3.25) + WyMathHelper.randomDouble(), 
						motionX,
						motionY + 0.1,
						motionZ)
						.setParticleScale(5F)
						.setParticleAge(3);
				Minecraft.getInstance().particles.addEffect(cp);
			}
		}
	}

}
