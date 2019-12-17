package xyz.pixelatedw.mineminenomi.particles.effects.rokushiki;

import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;

public class ParticleEffectGeppo extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		world.addParticle(ParticleTypes.CLOUD, (int) posX, (int) posY, (int) posZ, 0, 0, 0);
		
		world.addParticle(ParticleTypes.CLOUD, (int) posX + 0.2, (int) posY, (int) posZ + 0.2, 0, 0, 0);
		world.addParticle(ParticleTypes.CLOUD, (int) posX + 0.2, (int) posY, (int) posZ - 0.2, 0, 0, 0);
		world.addParticle(ParticleTypes.CLOUD, (int) posX - 0.2, (int) posY, (int) posZ - 0.2, 0, 0, 0);
		world.addParticle(ParticleTypes.CLOUD, (int) posX - 0.2, (int) posY, (int) posZ + 0.2, 0, 0, 0);
			
		world.addParticle(ParticleTypes.CLOUD, (int) posX + 0.5, (int) posY, (int) posZ, 0, 0, 0);
		world.addParticle(ParticleTypes.CLOUD, (int) posX + 0.2, (int) posY, (int) posZ, 0, 0, 0);
		world.addParticle(ParticleTypes.CLOUD, (int) posX - 0.5, (int) posY, (int) posZ, 0, 0, 0);	
		world.addParticle(ParticleTypes.CLOUD, (int) posX - 0.2, (int) posY, (int) posZ, 0, 0, 0);	
			
		world.addParticle(ParticleTypes.CLOUD, (int) posX, (int) posY, (int) posZ + 0.5, 0, 0, 0);
		world.addParticle(ParticleTypes.CLOUD, (int) posX, (int) posY, (int) posZ + 0.2, 0, 0, 0);
		world.addParticle(ParticleTypes.CLOUD, (int) posX, (int) posY, (int) posZ - 0.5, 0, 0, 0);
		world.addParticle(ParticleTypes.CLOUD, (int) posX, (int) posY, (int) posZ - 0.2, 0, 0, 0);
	}

}
