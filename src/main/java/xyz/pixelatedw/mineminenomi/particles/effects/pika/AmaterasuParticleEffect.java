package xyz.pixelatedw.mineminenomi.particles.effects.pika;

import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;

public class AmaterasuParticleEffect extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		for (int i = 0; i < 2; i++)
		{
			double offsetX = WyMathHelper.randomDouble() * 1.55;
			double offsetY = WyMathHelper.randomDouble();
			double offsetZ = WyMathHelper.randomDouble() * 1.55;
			
			GenericParticleData data = new GenericParticleData();
			data.setTexture(ModResources.PIKA);
			data.setLife(5);
			data.setSize(3F);
			data.setMotion(0, 0.15, 0);
			data.setHasRotation();
			((ServerWorld) world).spawnParticle(data, posX + offsetX, posY + 0.5 + offsetY, posZ + offsetZ, 1, 0, 0.0, 0, 0.0D);	
		}
	}

}
