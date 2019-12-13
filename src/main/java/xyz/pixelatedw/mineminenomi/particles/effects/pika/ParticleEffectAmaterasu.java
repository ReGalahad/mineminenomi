package xyz.pixelatedw.mineminenomi.particles.effects.pika;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import xyz.pixelatedw.mineminenomi.particles.CustomParticle;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.values.ModValuesParticles;

public class ParticleEffectAmaterasu extends ParticleEffect
{

	@Override
	public void spawn(PlayerEntity player, double posX, double posY, double posZ)
	{
        double motionX = -MathHelper.sin(player.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(player.rotationPitch / 180.0F * (float)Math.PI) * 1;
        double motionZ = MathHelper.cos(player.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(player.rotationPitch / 180.0F * (float)Math.PI) * 1;
        double motionY = -MathHelper.sin((player.rotationPitch) / 180.0F * (float)Math.PI) * 1;

		CustomParticle cp = new CustomParticle(player.world, ModValuesParticles.PARTICLE_ICON_PIKA,
				posX, 
				posY,
				posZ, 
				0,
				0,
				0)
				.setParticleScale(50F)
				.setParticleGravity(0)
				.setParticleAge(10);
		Minecraft.getInstance().particles.addEffect(cp);
	}

}
