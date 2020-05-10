package xyz.pixelatedw.mineminenomi.particles.effects.sniper;

import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;

public class KemuriBoshiParticleEffect extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		for (int i = 0; i < 512; i++)
		{
			double offsetX = WyHelper.randomWithRange(-4, 4) + WyHelper.randomDouble();
			double offsetY = WyHelper.randomWithRange(-2, 3) + WyHelper.randomDouble();
			double offsetZ = WyHelper.randomWithRange(-4, 4) + WyHelper.randomDouble();
			
			if(i % 2 == 0)
				((ServerWorld) world).spawnParticle(ParticleTypes.CLOUD, posX + offsetX + WyHelper.randomWithRange(-7, 7), (posY + 0.5) + offsetY + WyHelper.randomWithRange(-1, 3), posZ + offsetZ + WyHelper.randomWithRange(-7, 7), 1, 0.0D, 0, 0.0D, 0.05);
			else
				((ServerWorld) world).spawnParticle(ParticleTypes.POOF, posX + offsetX + WyHelper.randomWithRange(-7, 7), (posY + 0.5) + offsetY + WyHelper.randomWithRange(-1, 3), posZ + offsetZ + WyHelper.randomWithRange(-7, 7), 1, 0.0D, 0, 0.0D, 0.05);
		}
	}

}
