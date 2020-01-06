package xyz.pixelatedw.mineminenomi.particles.effects.kachi;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.SimpleParticle;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;

public class ParticleEffectEvaporate extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		for (int i = 0; i < 3; i++)
		{
			double offsetX = WyMathHelper.randomDouble();
			double offsetY = WyMathHelper.randomDouble();
			double offsetZ = WyMathHelper.randomDouble();
			
	        motionX = WyMathHelper.randomWithRange(0, 1) + WyMathHelper.randomDouble();
	        motionY = WyMathHelper.randomWithRange(0, 1) + WyMathHelper.randomDouble();
	        motionZ = WyMathHelper.randomWithRange(0, 1) + WyMathHelper.randomDouble();
	        
            double middlePoint = 0.5D / (5 / 0.5);
            middlePoint *= (WyMathHelper.randomDouble() * 2) + 0.3F;
	        
	        motionX *= middlePoint / 2;
	        motionY *= middlePoint / 2;
	        motionZ *= middlePoint / 2;
			
			SimpleParticle cp = new SimpleParticle(world, ModResources.MOKU,
					posX + offsetX, 
					posY + 1.5 + offsetY,
					posZ + offsetZ, 
					motionX,
					motionY + 0.05,
					motionZ)
					.setParticleScale(2.3F)
					.setParticleAge(10);
			Minecraft.getInstance().particles.addEffect(cp);
			
			cp = new SimpleParticle(world, ModResources.MERA,
					posX + offsetX, 
					posY + 1.5 + offsetY,
					posZ + offsetZ, 
					motionX,
					motionY + 0.05,
					motionZ)
					.setParticleScale(2.3F)
					.setParticleAge(10);
			Minecraft.getInstance().particles.addEffect(cp);	
		}
	}
}