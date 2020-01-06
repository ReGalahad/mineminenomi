package xyz.pixelatedw.mineminenomi.particles.effects.doku;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.SimpleParticle;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;

public class DokuGumoParticleEffect extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		for (int i = 0; i < 30; i++)
		{
			double offsetX = WyMathHelper.randomWithRange(-3, 3) + WyMathHelper.randomDouble();
			double offsetY = WyMathHelper.randomWithRange(-3, 3) + WyMathHelper.randomDouble();
			double offsetZ = WyMathHelper.randomWithRange(-3, 3) + WyMathHelper.randomDouble();
			
			SimpleParticle cp = new SimpleParticle(world, ModResources.DOKU,
					posX - 1 + offsetX, 
					posY + offsetY,
					posZ - 1 + offsetZ, 
					0, 0, 0)
					.setParticleAge((int) (1 + WyMathHelper.randomWithRange(0, 2))).setParticleScale(1F);
			Minecraft.getInstance().particles.addEffect(cp);
		}
	}

}
