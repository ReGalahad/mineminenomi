package xyz.pixelatedw.mineminenomi.particles.effects.sabi;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.SimpleParticle;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;

public class RustTouchParticleEffect extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		for (int i = 0; i < 64; i++)
		{
			motionX = WyHelper.randomWithRange(-3, 3) + WyHelper.randomDouble();
			motionY = WyHelper.randomWithRange(-3, 3) + WyHelper.randomDouble();
			motionZ = WyHelper.randomWithRange(-3, 3) + WyHelper.randomDouble();

			double middlePoint = 0.1;
			middlePoint *= (WyHelper.randomDouble() * 2) + 0.3F;

			motionX *= middlePoint / 2;
			motionY *= middlePoint / 2;
			motionZ *= middlePoint / 2;

			SimpleParticle cp = new SimpleParticle(world, ModResources.RUST,
					posX, 
					posY + 1,
					posZ, 
					motionX,
					motionY,
					motionZ)
					.setParticleScale(1F)
					.setParticleAge(10);
			Minecraft.getInstance().particles.addEffect(cp);
		}
	}
}