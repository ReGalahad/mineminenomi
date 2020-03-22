package xyz.pixelatedw.mineminenomi.particles.effects.moku;

import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;

public class WhiteLauncherParticleEffect extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		for (int i = 0; i < 20; i++)
		{
			double offsetX = WyHelper.randomDouble() / 5;
			double offsetY = WyHelper.randomDouble() / 5;
			double offsetZ = WyHelper.randomDouble() / 5;
	      
			GenericParticleData data = new GenericParticleData();
			data.setTexture(ModResources.MOKU);
			data.setLife(20);
			data.setSize(1.3F);
			data.setMotion(0, 0.05, 0);
			WyHelper.spawnParticles(data, (ServerWorld) world, posX + offsetX, posY + 1.5 + offsetY, posZ + offsetZ);
		}
	}

}
