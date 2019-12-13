package xyz.pixelatedw.mineminenomi.particles.effects.mero;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import xyz.pixelatedw.mineminenomi.particles.CustomParticle;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.values.ModValuesParticles;

public class ParticleEffectSlaveArrow extends ParticleEffect
{

	@Override
	public void spawn(PlayerEntity player, double posX, double posY, double posZ)
	{
        double motionX = -MathHelper.sin(player.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(player.rotationPitch / 180.0F * (float)Math.PI) * 0.05;
        double motionZ = MathHelper.cos(player.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(player.rotationPitch / 180.0F * (float)Math.PI) * 0.05;
        double motionY = -MathHelper.sin((player.rotationPitch) / 180.0F * (float)Math.PI) * 0;

		CustomParticle cp = new CustomParticle(player.world, ModValuesParticles.PARTICLE_ICON_MERO,
				posX , 
				posY + 1.5,
				posZ, 
				motionX,
				motionY,
				motionZ)
				.setParticleScale(10F)
				.setParticleGravity(0)
				.setParticleAge(50);
		cp.setParticleAlpha(0.5F);
		Minecraft.getInstance().particles.addEffect(cp);
	}

}
