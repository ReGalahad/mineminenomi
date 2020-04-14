package xyz.pixelatedw.mineminenomi.particles.effects.ito;

import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;

public class KumoNoSugakiParticleEffect extends ParticleEffect
{
	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		GenericParticleData data = new GenericParticleData();
		data.setTexture(ModResources.ITO);
		data.setLife(-10);
		data.setSize(20);
		data.setColor(1, 1, 1, 0.7F);
		data.setMotion(motionX, motionY, motionZ);
		WyHelper.spawnParticles(data, (ServerWorld) world, posX, posY + 1.5, posZ);
	}
}
