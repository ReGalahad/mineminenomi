package xyz.pixelatedw.mineminenomi.particles.effects.hie;

import java.util.Timer;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.particles.CustomParticle;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.tasks.ParticleTaskWave;
import xyz.pixelatedw.mineminenomi.values.ModValuesParticles;

public class ParticleEffectIceAge extends ParticleEffect
{

	@Override
	public void spawn(PlayerEntity player, double posX, double posY, double posZ)
	{
		for(int i = 0; i < 5; i++)
		{
			Timer timer = new Timer(true); 
			
			CustomParticle cp = new CustomParticle(player.world, ModValuesParticles.PARTICLE_ICON_HIE,
					posX, 
					posY - 0.5,
					posZ, 
					0,
					0,
					0)
					.setParticleScale(1 + player.world.rand.nextFloat());
			Minecraft.getInstance().particles.addEffect(cp);

			timer.schedule(ParticleTaskWave.Create(player, cp.getPos().getX(), cp.getPos().getY(), cp.getPos().getZ(), cp, 20), 0);
		}
	}

}
