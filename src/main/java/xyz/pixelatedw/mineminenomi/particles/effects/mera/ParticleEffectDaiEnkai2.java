package xyz.pixelatedw.mineminenomi.particles.effects.mera;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;

public class ParticleEffectDaiEnkai2 extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		double t = 0;
		double x, y, z;
		Random rand = world.rand;

		while(t < 1)
		{
			t += 0.5 * Math.PI;
			
			for(double theta = 0; theta <= 4 * Math.PI; theta += Math.PI / 32)
			{
				x = t * Math.cos(theta);
				y = rand.nextInt(1);
				z = t * Math.sin(theta);
										
				motionX = x / 10;
				motionY = 0.05 + (rand.nextDouble() / 10);
				motionZ = z / 10;

				GenericParticleData data = new GenericParticleData();
				data.setTexture(ModResources.MERA);
				data.setLife(-3);
				data.setSize(1.3F);
				data.setMotion(motionX, motionY, motionZ);
				((ServerWorld) world).spawnParticle(data, posX + (x * 1.25) + WyMathHelper.randomDouble(), posY + y, posZ + (z * 1.25) + WyMathHelper.randomDouble(), 1, 0, 0, 0, 0.0D);	
				
				data = new GenericParticleData();
				data.setTexture(ModResources.MERA);
				data.setLife(1);
				data.setSize(1.3F);
				data.setMotion(motionX, motionY + 0.05, motionZ);
				((ServerWorld) world).spawnParticle(data, posX + (x * 2.0) + WyMathHelper.randomDouble(), posY + y, posZ + (z * 2.0) + WyMathHelper.randomDouble(), 1, 0, 0, 0, 0.0D);	

				data = new GenericParticleData();
				data.setTexture(ModResources.MERA);
				data.setLife(3);
				data.setSize(1.3F);
				data.setMotion(motionX, motionY + 0.05, motionZ);
				((ServerWorld) world).spawnParticle(data, posX + (x * 3.25) + WyMathHelper.randomDouble(), posY + y, posZ + (z * 3.25) + WyMathHelper.randomDouble(), 1, 0, 0, 0, 0.0D);	
			}
		}
	}

}
