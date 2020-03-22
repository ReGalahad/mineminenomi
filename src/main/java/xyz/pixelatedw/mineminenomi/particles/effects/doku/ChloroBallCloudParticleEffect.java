package xyz.pixelatedw.mineminenomi.particles.effects.doku;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.SimpleParticle;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;

public class ChloroBallCloudParticleEffect extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		for (int i = 0; i < 64; i++)
		{
			double offsetX = WyHelper.randomWithRange(-2, 2) + WyHelper.randomDouble();
			double offsetY = WyHelper.randomWithRange(-1, 2) + WyHelper.randomDouble();
			double offsetZ = WyHelper.randomWithRange(-2, 2) + WyHelper.randomDouble();
			
			motionX = WyHelper.randomDouble() / 8;
			motionZ = WyHelper.randomDouble() / 8;
			
			SimpleParticle cp = new SimpleParticle(world, ModResources.DOKU,
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
