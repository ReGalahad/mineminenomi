package xyz.pixelatedw.mineminenomi.particles.effects.doku;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.SimpleParticle;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;

public class DokuGumoParticleEffect extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		for (int i = 0; i < 30; i++)
		{
			double offsetX = WyHelper.randomWithRange(-3, 3) + WyHelper.randomDouble();
			double offsetY = WyHelper.randomWithRange(-3, 3) + WyHelper.randomDouble();
			double offsetZ = WyHelper.randomWithRange(-3, 3) + WyHelper.randomDouble();
			
			SimpleParticle cp = new SimpleParticle(world, ModResources.DOKU,
					posX - 1 + offsetX, 
					posY + offsetY,
					posZ - 1 + offsetZ, 
					0, 0, 0)
					.setParticleAge((int) (1 + WyHelper.randomWithRange(0, 2))).setParticleScale(1F);
			Minecraft.getInstance().particles.addEffect(cp);
		}
	}

}
