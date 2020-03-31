package xyz.pixelatedw.mineminenomi.particles.effects.doru;

import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;

public class CandleLockParticleEffect extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		for (int i = 0; i < 40; i++)
		{
			double offsetX = WyHelper.randomDouble() * 2;
			double offsetZ = WyHelper.randomDouble() * 2;

			GenericParticleData data = new GenericParticleData();
			data.setTexture(ModResources.MOKU);
			data.setLife(8);
			data.setSize(1.4F);
			data.setMotion(0, 0.2 + (Math.abs(WyHelper.randomDouble()) / 3), 0);
			WyHelper.spawnParticles(data, (ServerWorld) world, posX + offsetX, posY, posZ + offsetZ);
			
			if(i % 5 == 0)
				((ServerWorld)world).spawnParticle(ParticleTypes.END_ROD, posX + offsetX, posY, posZ + offsetZ, 1, 0, 0, 0, -0.2);
		}
	}
}
