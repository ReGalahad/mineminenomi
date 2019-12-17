package xyz.pixelatedw.mineminenomi.particles.effects.doku;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.particles.SimpleParticle;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.values.ModValuesParticles;

public class ParticleEffectChloroBall extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{	
		for (int i = 0; i < 256; i++)
		{
	        motionX = WyMathHelper.randomWithRange(-7, 7) + WyMathHelper.randomDouble();
	        motionY = WyMathHelper.randomWithRange(-7, 7) + WyMathHelper.randomDouble();
	        motionZ = WyMathHelper.randomWithRange(-7, 7) + WyMathHelper.randomDouble();
	        
            double middlePoint = 0.05;
            middlePoint *= (WyMathHelper.randomDouble() * 2) + 2.2F;
	        
	        motionX *= middlePoint / 2;
	        motionY *= middlePoint / 2;
	        motionZ *= middlePoint / 2;
			
			SimpleParticle cp = new SimpleParticle(world, ModValuesParticles.PARTICLE_ICON_DOKU,
					posX, 
					posY + 1,
					posZ, 
					motionX,
					motionY,
					motionZ)
					.setParticleAge(1).setParticleScale(2F);
			Minecraft.getInstance().particles.addEffect(cp);
		}
	}
}
