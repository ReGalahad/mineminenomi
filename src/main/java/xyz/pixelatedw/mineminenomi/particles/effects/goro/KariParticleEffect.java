package xyz.pixelatedw.mineminenomi.particles.effects.goro;

import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;

public class KariParticleEffect extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		for (int i = 0; i < 20; i++)
		{
			double offsetX = WyMathHelper.randomWithRange(-5, 5) + WyMathHelper.randomDouble();
			double offsetY = WyMathHelper.randomWithRange(0, 3) + WyMathHelper.randomDouble();
			double offsetZ = WyMathHelper.randomWithRange(-5, 5) + WyMathHelper.randomDouble();
			
			GenericParticleData data = new GenericParticleData();
			data.setTexture(ModResources.GORO2);
			data.setLife(10);
			data.setSize(7);
			WyHelper.spawnParticles(data, (ServerWorld) world, posX + offsetX, posY + offsetY, posZ + offsetZ);
		}
	}
	
}
