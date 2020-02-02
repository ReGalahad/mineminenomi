package xyz.pixelatedw.mineminenomi.particles.effects.goro;

import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;

public class ElThorParticleEffect extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		for(int i = 0; i < 15; i++)
		{
			double offsetX = WyMathHelper.randomDouble();
			double offsetY = WyMathHelper.randomDouble();
			double offsetZ = WyMathHelper.randomDouble();

			GenericParticleData data = new GenericParticleData();
			data.setTexture(ModResources.GORO2);
			data.setLife(1);
			data.setSize(15);
			WyHelper.spawnParticles(data, (ServerWorld) world, posX + 0.5 + offsetX, posY + offsetY, posZ + offsetZ);
		}
	}

}
