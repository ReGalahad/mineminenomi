package xyz.pixelatedw.mineminenomi.particles.effects.toriphoenix;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.particles.CustomParticle;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.values.ModValuesParticles;

public class ParticleEffectBlueFlames extends ParticleEffect
{

	@Override
	public void spawn(PlayerEntity player, double posX, double posY, double posZ)
	{
		for (int i = 0; i < 50; i++)
		{
			double offsetX = (new Random().nextInt(50) + 1.0D - 25.0D) / 30.0D;
			double offsetY = (new Random().nextInt(50) + 1.0D - 25.0D) / 30.0D;
			double offsetZ = (new Random().nextInt(50) + 1.0D - 25.0D) / 30.0D;
			
			CustomParticle cp = new CustomParticle(player.world, ModValuesParticles.PARTICLE_ICON_BLUEFLAME,
					posX + offsetX, 
					posY + 1 + offsetY,
					posZ + offsetZ, 
					0, 0, 0)
					.setParticleScale(1.2F).setParticleGravity(0).setParticleAge(1);
			Minecraft.getInstance().particles.addEffect(cp);
		}
	}

}
