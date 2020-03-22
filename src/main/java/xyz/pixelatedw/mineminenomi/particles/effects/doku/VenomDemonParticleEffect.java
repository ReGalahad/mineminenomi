package xyz.pixelatedw.mineminenomi.particles.effects.doku;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.SimpleParticle;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;

public class VenomDemonParticleEffect extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		for (int i = 0; i < 3; i++)
		{
			double offsetX = WyHelper.randomWithRange(-2, 2) + WyHelper.randomDouble();
			double offsetY = WyHelper.randomWithRange(-2, 0) + WyHelper.randomDouble();
			double offsetZ = WyHelper.randomWithRange(-2, 2) + WyHelper.randomDouble();
			
			SimpleParticle cp = new SimpleParticle(world, ModResources.DOKU,
					posX + offsetX, 
					posY + 1 + offsetY,
					posZ + offsetZ, 
					0, 0, 0)
					.setParticleAge((int) (1 + WyHelper.randomWithRange(0, 4))).setParticleScale(1.5F);
			cp.setColor(1, 0, 0);
			
			Minecraft.getInstance().particles.addEffect(cp);
		}
	}

}
