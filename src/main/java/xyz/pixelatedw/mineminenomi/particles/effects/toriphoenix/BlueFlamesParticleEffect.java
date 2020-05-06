package xyz.pixelatedw.mineminenomi.particles.effects.toriphoenix;

import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;

public class BlueFlamesParticleEffect extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		for (int i = 0; i < 20; i++)
		{
			double offsetX = WyHelper.randomDouble() / 1.25;
			double offsetY = WyHelper.randomDouble() / 1.25;
			double offsetZ = WyHelper.randomDouble() / 1.25;
			
			GenericParticleData data = new GenericParticleData();
			data.setTexture(ModResources.BLUE_FLAME);
			data.setLife(1);
			data.setSize(2.5F);
			WyHelper.spawnParticles(data, (ServerWorld) world, posX + offsetX, posY + 1 + offsetY, posZ + offsetZ);
		}
	}

}
