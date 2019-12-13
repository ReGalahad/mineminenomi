package xyz.pixelatedw.mineminenomi.particles.effects.ito;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.particles.CustomParticle;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.values.ModValuesParticles;

public class ParticleEffectKumoNoSugaki extends ParticleEffect
{

	@Override
	public void spawn(PlayerEntity player, double posX, double posY, double posZ)
	{
		double offsetX = 0;
		double offsetZ = 0;
		
		switch(WyHelper.get4Directions(player))
		{
			case NORTH:
				offsetZ = -1.5; break;
			case SOUTH:
				offsetZ = 1.5; break;
			case EAST:
				offsetX = 1.5; break;
			case WEST:
				offsetX = -1.5; break;
			default:
				break;
		}
		
		CustomParticle cp = new CustomParticle(player.world, ModValuesParticles.PARTICLE_ICON_ITO,
				posX + offsetX, 
				posY + 1,
				posZ + offsetZ, 
				0, 0, 0)
				.setParticleAge(10).setParticleGravity(0).setParticleScale(30F);
		Minecraft.getInstance().particles.addEffect(cp);

	}

}
