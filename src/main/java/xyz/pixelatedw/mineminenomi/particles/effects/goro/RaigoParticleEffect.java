package xyz.pixelatedw.mineminenomi.particles.effects.goro;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.particles.SimpleParticle;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.values.ModValuesParticles;

public class RaigoParticleEffect extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		for (int i = 0; i < 75; i++)
		{
			double offsetX = WyMathHelper.randomWithRange(-55, 55) + WyMathHelper.randomDouble();
			double offsetY = WyMathHelper.randomWithRange(-5, 5) + WyMathHelper.randomDouble();
			double offsetZ = WyMathHelper.randomWithRange(-55, 55) + WyMathHelper.randomDouble();

			SimpleParticle cp = new SimpleParticle(world, ModValuesParticles.PARTICLE_ICON_GORO3,
					posX + offsetX, 
					posY + 40 + offsetY,
					posZ + offsetZ, 
					0, 0, 0)
					.setParticleScale(100)
					.setParticleAge(100);
			
			if(i % 2 == 0)
				cp.setColor(0.4F, 0.4F, 0.4F);
			else
				cp.setColor(0.3F, 0.3F, 0.3F);
			
			Minecraft.getInstance().particles.addEffect(cp);
		}
		
		for (int i = 0; i < 25; i++)
		{
			double offsetX = WyMathHelper.randomWithRange(-55, 55) + WyMathHelper.randomDouble();
			double offsetY = WyMathHelper.randomWithRange(-5, 0) + WyMathHelper.randomDouble();
			double offsetZ = WyMathHelper.randomWithRange(-55, 55) + WyMathHelper.randomDouble();
			
			SimpleParticle cp = new SimpleParticle(world, ModValuesParticles.PARTICLE_ICON_GORO2,
				posX + offsetX, 
				posY + 30 + offsetY,
				posZ + offsetZ, 
				0, 0, 0)
				.setParticleScale(40)
				.setParticleAge(10);
		
			Minecraft.getInstance().particles.addEffect(cp);
		}
	}
	
}
