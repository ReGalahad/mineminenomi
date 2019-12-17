package xyz.pixelatedw.mineminenomi.particles.effects.goro;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.particles.SimpleParticle;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.values.ModValuesParticles;

public class KariParticleEffect extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		for (int i = 0; i < 20; i++)
		{
			double offsetX = WyMathHelper.randomWithRange(-5, 5) + WyMathHelper.randomDouble();
			double offsetY = WyMathHelper.randomWithRange(0, 3) + WyMathHelper.randomDouble();
			double offsetZ = WyMathHelper.randomWithRange(-5, 5) + WyMathHelper.randomDouble();
			
			SimpleParticle cp = new SimpleParticle(world, ModValuesParticles.PARTICLE_ICON_GORO2,
				posX + offsetX, 
				posY + offsetY,
				posZ + offsetZ, 
				0, 0, 0)
				.setParticleScale(3)
				.setParticleAge(10);
		
			Minecraft.getInstance().particles.addEffect(cp);
		}
	}
	
}
