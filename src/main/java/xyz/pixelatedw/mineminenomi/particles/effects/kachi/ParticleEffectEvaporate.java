package xyz.pixelatedw.mineminenomi.particles.effects.kachi;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.particles.CustomParticle;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.values.ModValuesParticles;

public class ParticleEffectEvaporate extends ParticleEffect
{

	@Override
	public void spawn(PlayerEntity player, double posX, double posY, double posZ)
	{
		for (int i = 0; i < 3; i++)
		{
			double offsetX = player.world.rand.nextDouble();
			double offsetY = player.world.rand.nextDouble();
			double offsetZ = player.world.rand.nextDouble();
			
	        double motionX = WyMathHelper.randomWithRange(0, 1) + player.world.rand.nextDouble();
	        double motionY = WyMathHelper.randomWithRange(0, 1) + player.world.rand.nextDouble();
	        double motionZ = WyMathHelper.randomWithRange(0, 1) + player.world.rand.nextDouble();
	        
            double middlePoint = 0.5D / (5 / 0.5);
            middlePoint *= player.world.rand.nextFloat() * player.world.rand.nextFloat() + 0.3F;
	        
	        motionX *= middlePoint / 2;
	        motionY *= middlePoint / 2;
	        motionZ *= middlePoint / 2;
			
			CustomParticle cp = new CustomParticle(player.world, ModValuesParticles.PARTICLE_ICON_MOKU,
					posX + offsetX, 
					posY + 1.5 + offsetY,
					posZ + offsetZ, 
					motionX,
					motionY + 0.05,
					motionZ)
					.setParticleScale(2.3F)
					.setParticleAge(10);
			Minecraft.getInstance().particles.addEffect(cp);

			cp = new CustomParticle(player.world, ModValuesParticles.PARTICLE_ICON_MERA,
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