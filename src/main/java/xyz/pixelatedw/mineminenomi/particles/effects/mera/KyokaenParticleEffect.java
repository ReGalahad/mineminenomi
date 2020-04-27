package xyz.pixelatedw.mineminenomi.particles.effects.mera;

import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;

public class KyokaenParticleEffect extends ParticleEffect
{
	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		for(int i = 0; i < 64; i++)
		{
			double offsetX = WyHelper.randomDouble() / 1.2;
			double offsetY = WyHelper.randomDouble();
			double offsetZ = WyHelper.randomDouble() / 1.2;
			
			GenericParticleData data = new GenericParticleData();
			data.setTexture(ModResources.MERA);
			data.setLife(2);
			data.setSize(1);
			data.setColor(1, 1, 1, 0.5F);
			data.setMotion(0, 0.1, 0);
			WyHelper.spawnParticles(data, (ServerWorld) world, posX + offsetX, posY + 1 + offsetY, posZ + offsetZ);
		}
	}
}
