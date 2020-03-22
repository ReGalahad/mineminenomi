package xyz.pixelatedw.mineminenomi.particles.effects.suna;

import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;

public class DesertGirasoleParticleEffect extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{		
		for (int i = 0; i < 64; i++)
		{
			double offsetX = WyHelper.randomWithRange(-15, 15) + WyHelper.randomDouble();
			double offsetZ = WyHelper.randomWithRange(-15, 15) + WyHelper.randomDouble();
			
			GenericParticleData data = new GenericParticleData();
			data.setTexture(ModResources.SUNA2);
			data.setLife((int) (80 + (0.2 * i)));
			data.setSize(4F);
			data.setMotion(0, 0.3, 0);
			WyHelper.spawnParticles(data, (ServerWorld) world, posX + offsetX, posY + 0.5 - (0.15 * i), posZ + offsetZ);
		}	
	}

}
