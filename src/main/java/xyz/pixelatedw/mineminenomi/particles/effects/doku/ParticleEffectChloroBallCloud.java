package xyz.pixelatedw.mineminenomi.particles.effects.doku;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.particles.SimpleParticle;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.values.ModValuesParticles;

public class ParticleEffectChloroBallCloud extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		for (int i = 0; i < 128; i++)
		{
			double offsetX = WyMathHelper.randomWithRange(-2, 2) + WyMathHelper.randomDouble();
			double offsetY = WyMathHelper.randomWithRange(-1, 2) + WyMathHelper.randomDouble();
			double offsetZ = WyMathHelper.randomWithRange(-2, 2) + WyMathHelper.randomDouble();
			
			motionX = WyMathHelper.randomDouble() / 8;
			motionZ = WyMathHelper.randomDouble() / 8;
			
			SimpleParticle cp = new SimpleParticle(world, ModValuesParticles.PARTICLE_ICON_DOKU,
					posX + offsetX, 
					posY + offsetY,
					posZ + offsetZ, 
					motionX,
					0.05D,
					motionZ)
					.setParticleAge(5).setParticleGravity(0).setParticleScale(1.5F);
			Minecraft.getInstance().particles.addEffect(cp);
		}	
	}

}
