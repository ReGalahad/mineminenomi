package xyz.pixelatedw.mineminenomi.particles.effects.doku;

import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;

public class ChloroBallParticleEffect extends ParticleEffect
{
	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{	
		for (int i = 0; i < 12; i++)
		{
	        motionX = WyHelper.randomWithRange(-3, 3) + WyHelper.randomDouble();
	        motionY = WyHelper.randomWithRange(-3, 3) + WyHelper.randomDouble();
			motionZ = WyHelper.randomWithRange(-3, 3) + WyHelper.randomDouble();
	        
            double middlePoint = 0.25;
            middlePoint *= (WyHelper.randomDouble() * 2) + 0.3F;
	        
	        motionX *= middlePoint / 2;
	        motionY *= middlePoint / 2;
	        motionZ *= middlePoint / 2;
			
			GenericParticleData data = new GenericParticleData();
			data.setTexture(ModResources.DOKU);
			data.setLife(8);
			data.setSize(0.8F);
			data.setMotion(motionX, motionY, motionZ);
			WyHelper.spawnParticles(data, (ServerWorld) world, posX, posY, posZ);
		}
	}
}
