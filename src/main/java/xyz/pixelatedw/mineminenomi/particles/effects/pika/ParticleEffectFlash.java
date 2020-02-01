package xyz.pixelatedw.mineminenomi.particles.effects.pika;

import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;

public class ParticleEffectFlash extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		for (int i = 0; i < 10; i++)
		{
			double offsetX = WyMathHelper.randomDouble() / 1.25;
			double offsetY = WyMathHelper.randomDouble() * 1.25;
			double offsetZ = WyMathHelper.randomDouble() / 1.25;
			
			GenericParticleData data = new GenericParticleData();
			data.setTexture(ModResources.PIKA);
			data.setLife(5);
			data.setSize(4F);
			data.setHasRotation();
			((ServerWorld) world).spawnParticle(data, posX + offsetX, posY + offsetY, posZ + offsetZ, 1, 0, 0, 0, 0.0D);	
		}
	}

}
