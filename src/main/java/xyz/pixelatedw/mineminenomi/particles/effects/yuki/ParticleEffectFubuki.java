package xyz.pixelatedw.mineminenomi.particles.effects.yuki;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.particles.SimpleParticle;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.values.ModValuesParticles;

public class ParticleEffectFubuki extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		for (int i = 0; i < 1024; i++)
		{
			double offsetX = (new Random().nextInt(50) + 1.0D - 25.0D) / 1.0D;
			double offsetY = (new Random().nextInt(50) + 1.0D - 25.0D) / 1.0D;
			double offsetZ = (new Random().nextInt(50) + 1.0D - 25.0D) / 1.0D;

			motionX = WyMathHelper.randomWithRange(-1, 1) + WyMathHelper.randomDouble();
			motionY = WyMathHelper.randomWithRange(-1, 1) + WyMathHelper.randomDouble();
			motionZ = WyMathHelper.randomWithRange(-1, 1) + WyMathHelper.randomDouble();

			double middlePoint = 0.2D;
			middlePoint *= (WyMathHelper.randomDouble() * 2) + 0.3F;

			motionX *= middlePoint / 2;
			motionY *= middlePoint / 2;
			motionZ *= middlePoint / 2;

			SimpleParticle cp = new SimpleParticle(world, ModValuesParticles.PARTICLE_ICON_YUKI,
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
