package xyz.pixelatedw.MineMineNoMi3.entities.particles.effects.suna;

import java.util.Timer;

import net.minecraft.entity.player.EntityPlayer;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.MainMod;
import xyz.pixelatedw.MineMineNoMi3.api.math.WyMathHelper;
import xyz.pixelatedw.MineMineNoMi3.entities.particles.EntityParticleFX;
import xyz.pixelatedw.MineMineNoMi3.entities.particles.effects.ParticleEffect;
import xyz.pixelatedw.MineMineNoMi3.lists.ListParticleEffects;

public class ParticleEffectDesertGirasole extends ParticleEffect
{

	public void spawn(EntityPlayer player, double posX, double posY, double posZ)
	{		
		for (int i = 0; i < 64; i++)
		{
			double offsetX = WyMathHelper.randomWithRange(-15, 15);
			double offsetZ = WyMathHelper.randomWithRange(-15, 15);
			
			for (int j = 0; j < 90; j++)
			{
				MainMod.proxy.spawnCustomParticles(player,
						new EntityParticleFX(player.worldObj, ID.PARTICLE_ICON_SUNA2,
								posX + offsetX,
								posY - 0.5 - (0.15 * j),
								posZ + offsetZ,
								0, 0, 0)
						.setParticleScale(4).setParticleGravity(-3.8F).setParticleAge((int) (80 + (0.2 * j))));
			}
		}	
	}

}
