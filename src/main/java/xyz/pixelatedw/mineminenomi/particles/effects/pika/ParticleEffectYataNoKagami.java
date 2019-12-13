package xyz.pixelatedw.mineminenomi.particles.effects.pika;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.particles.CustomParticle;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.values.ModValuesParticles;

public class ParticleEffectYataNoKagami extends ParticleEffect
{

	@Override
	public void spawn(PlayerEntity player, double posX, double posY, double posZ)
	{
		for (int i = 0; i < 20; i++)
		{
			double offsetX = (new Random().nextInt(40) + 1.0D - 20.0D) / 20.0D;
			double offsetY = (new Random().nextInt(40) + 1.0D) / 20.0D;
			double offsetZ = (new Random().nextInt(40) + 1.0D - 20.0D) / 20.0D;
			
			CustomParticle cp = new CustomParticle(player.world, ModValuesParticles.PARTICLE_ICON_PIKA,
					posX + offsetX, 
					posY + 0.5 + offsetY,
					posZ + offsetZ, 
					0,
					0,
					0)
					.setParticleScale(4F)
					.setParticleGravity(0)
					.setParticleAge(20);
			Minecraft.getInstance().particles.addEffect(cp);
		}	
	}

}
