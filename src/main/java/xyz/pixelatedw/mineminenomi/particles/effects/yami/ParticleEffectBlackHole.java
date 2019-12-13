package xyz.pixelatedw.mineminenomi.particles.effects.yami;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.particles.CustomParticle;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.values.ModValuesParticles;

public class ParticleEffectBlackHole extends ParticleEffect
{

	@Override
	public void spawn(PlayerEntity player, double posX, double posY, double posZ)
	{
		for (int i = 0; i < 1024; i++)
		{
			double offsetX = WyMathHelper.randomWithRange(-10, 10) + player.world.rand.nextDouble();
			double offsetZ = WyMathHelper.randomWithRange(-10, 10) + player.world.rand.nextDouble();
			
			CustomParticle cp = new CustomParticle(player.world, ModValuesParticles.PARTICLE_ICON_DARKNESS,
					posX - 1 + offsetX, 
					posY - 0.5 + WyMathHelper.randomDouble(),
					posZ - 1 + offsetZ, 
					0, 0, 0)
					.setParticleGravity(-1 + player.world.rand.nextFloat()).setParticleScale((float) (1 + WyMathHelper.randomWithRange(0, 3)));
			Minecraft.getInstance().particles.addEffect(cp);
		}
	}

}
