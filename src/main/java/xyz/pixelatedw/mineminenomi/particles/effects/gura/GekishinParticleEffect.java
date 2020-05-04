package xyz.pixelatedw.mineminenomi.particles.effects.gura;

import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;

public class GekishinParticleEffect extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{	
		GenericParticleData data = new GenericParticleData();
		data.setTexture(ModResources.GURA);
		data.setLife(20);
		data.setSize(50F);
		WyHelper.spawnParticles(data, (ServerWorld) world, posX, posY, posZ);
	}
	
}
