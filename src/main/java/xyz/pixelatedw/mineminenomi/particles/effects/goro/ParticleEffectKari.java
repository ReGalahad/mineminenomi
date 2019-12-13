package xyz.pixelatedw.mineminenomi.particles.effects.goro;

import java.util.Timer;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.tasks.ParticleTaskSparks;

public class ParticleEffectKari extends ParticleEffect
{

	@Override
	public void spawn(PlayerEntity player, double posX, double posY, double posZ)
	{
		Timer timer = new Timer(true); 
		timer.schedule(ParticleTaskSparks.Create(player, player.posX, player.posY, player.posZ, 25, 5, 25), 0);		
	}
	
}
