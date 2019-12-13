package xyz.pixelatedw.mineminenomi.particles.effects.common;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;

public class ParticleEffectWaterExplosion extends ParticleEffect
{

	@Override
	public void spawn(PlayerEntity player, double posX, double posY, double posZ)
	{	
		for (int i = 0; i < 512; i++)
		{
	        double motionX = WyMathHelper.randomWithRange(-5, 5) + player.world.rand.nextDouble();
	        double motionY = WyMathHelper.randomWithRange(-5, 5) + player.world.rand.nextDouble();
	        double motionZ = WyMathHelper.randomWithRange(-5, 5) + player.world.rand.nextDouble();
	        
            double middlePoint = 0.25;
            middlePoint *= player.world.rand.nextFloat() * player.world.rand.nextFloat() + 0.3F;
	        
	        motionX *= middlePoint / 2;
	        motionY *= middlePoint / 2;
	        motionZ *= middlePoint / 2;
			
            player.world.addParticle(ParticleTypes.FISHING, posX, posY, posZ, motionX, motionY, motionZ);
            player.world.addParticle(ParticleTypes.FISHING, posX, posY, posZ, motionX, motionY, motionZ);
		}
	}
}
