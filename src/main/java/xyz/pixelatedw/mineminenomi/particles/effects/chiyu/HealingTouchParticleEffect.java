package xyz.pixelatedw.mineminenomi.particles.effects.chiyu;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.init.ModParticleTextures;
import xyz.pixelatedw.mineminenomi.particles.SimpleParticle;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;

public class HealingTouchParticleEffect extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{	
		for (int i = 0; i < 64; i++)
		{
	        motionX = WyMathHelper.randomWithRange(-3, 3) + WyMathHelper.randomDouble();
	        motionY = WyMathHelper.randomWithRange(-3, 3) + WyMathHelper.randomDouble();
	        motionZ = WyMathHelper.randomWithRange(-3, 3) + WyMathHelper.randomDouble();
	        
            double middlePoint = 0.1;
            middlePoint *= (WyMathHelper.randomDouble() * 2) + 0.3F;
	        
	        motionX *= middlePoint / 2;
	        motionY *= middlePoint / 2;
	        motionZ *= middlePoint / 2;
			
			SimpleParticle cp = new SimpleParticle(world, ModParticleTextures.CHIYU,
					posX , 
					posY + 1,
					posZ, 
					motionX,
					motionY,
					motionZ)
					.setParticleScale(1.5F)
					.setParticleAge(10);
			Minecraft.getInstance().particles.addEffect(cp);
		}
	}
}