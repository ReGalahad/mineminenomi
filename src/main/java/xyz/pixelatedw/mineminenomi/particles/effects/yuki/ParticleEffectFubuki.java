package xyz.pixelatedw.mineminenomi.particles.effects.yuki;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.particles.CustomParticle;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.values.ModValuesParticles;

public class ParticleEffectFubuki extends ParticleEffect
{

	@Override
	public void spawn(PlayerEntity player, double posX, double posY, double posZ)
	{
		for (int i = 0; i < 1024; i++)
		{
			double offsetX = (new Random().nextInt(50) + 1.0D - 25.0D) / 1.0D;
			double offsetY = (new Random().nextInt(50) + 1.0D - 25.0D) / 1.0D;
			double offsetZ = (new Random().nextInt(50) + 1.0D - 25.0D) / 1.0D;

	        double motionX = WyMathHelper.randomWithRange(-1, 1) + player.world.rand.nextDouble();
	        double motionY = WyMathHelper.randomWithRange(-1, 1) + player.world.rand.nextDouble();
	        double motionZ = WyMathHelper.randomWithRange(-1, 1) + player.world.rand.nextDouble();
	        
            double middlePoint = 0.2D;
            middlePoint *= player.world.rand.nextFloat() * player.world.rand.nextFloat() + 0.3F;
	        
	        motionX *= middlePoint / 2;
	        motionY *= middlePoint / 2;
	        motionZ *= middlePoint / 2;
			
			CustomParticle cp = new CustomParticle(player.world, ModValuesParticles.PARTICLE_ICON_YUKI,
					posX + offsetX, 
					posY + offsetY,
					posZ + offsetZ, 
					motionX, 
					0, 
					motionZ)
					.setParticleAge(300).setParticleGravity(3).setParticleScale((float) (1 + WyMathHelper.randomWithRange(0, 2)));
			Minecraft.getInstance().particles.addEffect(cp);
		}		
	}

}
