package xyz.pixelatedw.mineminenomi.particles.effects.yami;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.init.ModParticleTextures;
import xyz.pixelatedw.mineminenomi.particles.SimpleParticle;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;

public class ParticleEffectBlackHole extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		for (int i = 0; i < 1024; i++)
		{
			double offsetX = WyMathHelper.randomWithRange(-10, 10) + WyMathHelper.randomDouble();
			double offsetZ = WyMathHelper.randomWithRange(-10, 10) + WyMathHelper.randomDouble();
			
			SimpleParticle cp = new SimpleParticle(world, ModParticleTextures.DARKNESS,
					posX - 1 + offsetX, 
					posY - 0.5 + WyMathHelper.randomDouble(),
					posZ - 1 + offsetZ, 
					0, 0, 0)
					.setParticleGravity(-1 + world.rand.nextFloat()).setParticleScale((float) (1 + WyMathHelper.randomWithRange(0, 3)));
			Minecraft.getInstance().particles.addEffect(cp);
		}
	}

}
