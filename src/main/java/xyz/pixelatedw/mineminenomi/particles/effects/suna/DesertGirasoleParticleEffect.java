package xyz.pixelatedw.mineminenomi.particles.effects.suna;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.SimpleParticle;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;

public class DesertGirasoleParticleEffect extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{		
		for (int i = 0; i < 64; i++)
		{
			double offsetX = WyHelper.randomWithRange(-15, 15);
			double offsetZ = WyHelper.randomWithRange(-15, 15);
			
			SimpleParticle cp = new SimpleParticle(world, ModResources.SUNA2,
					posX + offsetX + WyHelper.randomDouble(), 
					posY + 0.5 - (0.15 * i),
					posZ + offsetZ + WyHelper.randomDouble(), 
					0, 0, 0)
					.setParticleAge( (int) (80 + (0.2 * i)) ).setParticleScale(4).setParticleGravity(-3.8F);
			Minecraft.getInstance().particles.addEffect(cp);
		}	
	}

}
