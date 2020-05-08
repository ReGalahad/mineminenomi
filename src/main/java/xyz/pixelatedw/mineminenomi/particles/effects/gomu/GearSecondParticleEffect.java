package xyz.pixelatedw.mineminenomi.particles.effects.gomu;

import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;

public class GearSecondParticleEffect extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		for (int i = 0; i < 2; i++)
		{
			double offsetX = WyHelper.randomDouble() / 1.25;
			double offsetY = WyHelper.randomDouble() / 1.25;
			double offsetZ = WyHelper.randomDouble() / 1.25;
	      
			((ServerWorld) world).spawnParticle(ParticleTypes.POOF, posX + offsetX, (posY + 1) + offsetY, posZ + offsetZ, 1, 0, 0, 0, 0);
		}	
	}

}
