package xyz.pixelatedw.mineminenomi.particles.effects.rokushiki;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;

public class ParticleEffectGeppo extends ParticleEffect
{

	@Override
	public void spawn(PlayerEntity player, double posX, double posY, double posZ)
	{
		player.world.addParticle(ParticleTypes.CLOUD, (int) posX, (int) posY, (int) posZ, 0, 0, 0);
		
		player.world.addParticle(ParticleTypes.CLOUD, (int) posX + 0.2, (int) posY, (int) posZ + 0.2, 0, 0, 0);
		player.world.addParticle(ParticleTypes.CLOUD, (int) posX + 0.2, (int) posY, (int) posZ - 0.2, 0, 0, 0);
		player.world.addParticle(ParticleTypes.CLOUD, (int) posX - 0.2, (int) posY, (int) posZ - 0.2, 0, 0, 0);
		player.world.addParticle(ParticleTypes.CLOUD, (int) posX - 0.2, (int) posY, (int) posZ + 0.2, 0, 0, 0);
			
		player.world.addParticle(ParticleTypes.CLOUD, (int) posX + 0.5, (int) posY, (int) posZ, 0, 0, 0);
		player.world.addParticle(ParticleTypes.CLOUD, (int) posX + 0.2, (int) posY, (int) posZ, 0, 0, 0);
		player.world.addParticle(ParticleTypes.CLOUD, (int) posX - 0.5, (int) posY, (int) posZ, 0, 0, 0);	
		player.world.addParticle(ParticleTypes.CLOUD, (int) posX - 0.2, (int) posY, (int) posZ, 0, 0, 0);	
			
		player.world.addParticle(ParticleTypes.CLOUD, (int) posX, (int) posY, (int) posZ + 0.5, 0, 0, 0);
		player.world.addParticle(ParticleTypes.CLOUD, (int) posX, (int) posY, (int) posZ + 0.2, 0, 0, 0);
		player.world.addParticle(ParticleTypes.CLOUD, (int) posX, (int) posY, (int) posZ - 0.5, 0, 0, 0);
		player.world.addParticle(ParticleTypes.CLOUD, (int) posX, (int) posY, (int) posZ - 0.2, 0, 0, 0);
	}

}
