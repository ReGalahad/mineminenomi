package xyz.pixelatedw.mineminenomi.particles.effects.gasu;

import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;

public class GastanetParticleEffect extends ParticleEffect
{
	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{	
		for (int i = 0; i < 228; i++)
		{
			double offsetX = WyHelper.randomDouble();
			double offsetY = WyHelper.randomDouble();
			double offsetZ = WyHelper.randomDouble();
			
	        motionX = WyHelper.randomWithRange(-5, 5) + WyHelper.randomDouble();
	        motionY = WyHelper.randomWithRange(0, 5) + WyHelper.randomDouble();
	        motionZ = WyHelper.randomWithRange(-5, 5) + WyHelper.randomDouble();
	        
            double middlePoint = 2.8;
	        
	        motionX *= middlePoint / 15;
	        motionY *= middlePoint / 25;
	        motionZ *= middlePoint / 15;
			
	        motionY = Math.abs(motionY);
	        
	        GenericParticleData data = new GenericParticleData();
	        if(i % 4 == 0)
				data.setTexture(ModResources.GASU);
	        else
				data.setTexture(ModResources.GASU2);
	        
			data.setLife(7);
			data.setSize(1.5F);
			data.setMotion(motionX, motionY, motionZ);
			WyHelper.spawnParticles(data, (ServerWorld) world, posX + offsetX, posY + 0.25 + offsetY, posZ + offsetZ);
		}
	}
}