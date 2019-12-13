package xyz.pixelatedw.mineminenomi.particles.effects.ushibison;

import java.util.Timer;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.tasks.ParticleTaskTornado2;

public class ParticleEffectKokuteiCross extends ParticleEffect
{

	@Override
	public void spawn(PlayerEntity player, double posX, double posY, double posZ)
	{
		Timer timer = new Timer(true); 
		timer.schedule(ParticleTaskTornado2.Create(player, posX, posY, posZ, ParticleTypes.LARGE_SMOKE, 2.0, 1), 0);		
	}

}
