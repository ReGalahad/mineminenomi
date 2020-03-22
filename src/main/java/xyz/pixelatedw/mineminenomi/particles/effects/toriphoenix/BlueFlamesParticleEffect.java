package xyz.pixelatedw.mineminenomi.particles.effects.toriphoenix;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.SimpleParticle;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;

public class BlueFlamesParticleEffect extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		for (int i = 0; i < 50; i++)
		{
			double offsetX = WyHelper.randomDouble();
			double offsetY = WyHelper.randomDouble();
			double offsetZ = WyHelper.randomDouble();
			
			SimpleParticle cp = new SimpleParticle(world, ModResources.BLUE_FLAME,
					posX + offsetX, 
					posY + 1 + offsetY,
					posZ + offsetZ, 
					0, 0, 0)
					.setParticleScale(1.2F).setParticleGravity(0).setParticleAge(1);
			Minecraft.getInstance().particles.addEffect(cp);
		}
	}

}
