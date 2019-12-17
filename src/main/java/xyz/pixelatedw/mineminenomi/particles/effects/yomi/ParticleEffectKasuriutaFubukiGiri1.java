package xyz.pixelatedw.mineminenomi.particles.effects.yomi;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.particles.SimpleParticle;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.values.ModValuesParticles;

public class ParticleEffectKasuriutaFubukiGiri1 extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		for (int i = 0; i < 2; i++)
		{
			double offsetX = WyMathHelper.randomWithRange(-1, 1) + WyMathHelper.randomDouble();
			double offsetY = WyMathHelper.randomWithRange(-1, 1) + WyMathHelper.randomDouble();
			double offsetZ = WyMathHelper.randomWithRange(-1, 1) + WyMathHelper.randomDouble();
	      
			SimpleParticle cp = new SimpleParticle(world, ModValuesParticles.PARTICLE_ICON_HIE, 
							posX + offsetX, 
							posY + 1 + offsetY, 
							posZ + offsetZ, 
							0, 0, 0)
					.setParticleScale(1.3F).setParticleGravity(0).setParticleAge(20);
			Minecraft.getInstance().particles.addEffect(cp);

		}
	}
}
