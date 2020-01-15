package xyz.pixelatedw.mineminenomi.particles.effects.mera;

import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;

public class ParticleEffectDaiEnkai extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		for (int i = 0; i < 10; i++)
		{
			double offsetX = WyMathHelper.randomWithRange(-2, 2) + WyMathHelper.randomDouble();
			double offsetY = WyMathHelper.randomWithRange(-2, 2) + WyMathHelper.randomDouble();
			double offsetZ = WyMathHelper.randomWithRange(-2, 2) + WyMathHelper.randomDouble();
			
	        motionX = WyMathHelper.randomWithRange(-1, 1) + WyMathHelper.randomDouble();
	        motionY = WyMathHelper.randomWithRange(-1, 1) + WyMathHelper.randomDouble();
	        motionZ = WyMathHelper.randomWithRange(-1, 1) + WyMathHelper.randomDouble();
	        
            double middlePoint = 0.5D / (5 / 0.5);
            middlePoint *= (WyMathHelper.randomDouble() * 2) + 0.3F;
	        
	        motionX *= middlePoint / 2;
	        motionY *= middlePoint / 2;
	        motionZ *= middlePoint / 2;
			
			GenericParticleData data = new GenericParticleData();
			data.setTexture(ModResources.MERA);
			data.setLife(20);
			data.setSize(1.3F);
			data.setMotion(motionX, motionY + 0.05, motionZ);
			((ServerWorld) world).spawnParticle(data, posX + offsetX, posY + 1.5 + offsetY, posZ + offsetZ, 1, 0, 0, 0, 0.0D);	
		}
	}

}
