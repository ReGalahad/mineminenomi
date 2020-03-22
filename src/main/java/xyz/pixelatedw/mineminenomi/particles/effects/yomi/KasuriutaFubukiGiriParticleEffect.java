package xyz.pixelatedw.mineminenomi.particles.effects.yomi;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.SimpleParticle;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;

public class KasuriutaFubukiGiriParticleEffect extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		for (int i = 0; i < 2; i++)
		{
			double offsetX = WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble();
			double offsetY = WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble();
			double offsetZ = WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble();
	      
			SimpleParticle cp = new SimpleParticle(world, ModResources.HIE, 
							posX + offsetX, 
							posY + 1 + offsetY, 
							posZ + offsetZ, 
							0, 0, 0)
					.setParticleScale(1.3F).setParticleGravity(0).setParticleAge(20);
			Minecraft.getInstance().particles.addEffect(cp);

		}
	}
}
