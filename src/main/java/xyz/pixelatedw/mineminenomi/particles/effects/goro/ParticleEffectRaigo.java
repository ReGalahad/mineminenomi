package xyz.pixelatedw.mineminenomi.particles.effects.goro;

import java.util.Timer;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.particles.CustomParticle;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.tasks.ParticleTaskSparks;
import xyz.pixelatedw.mineminenomi.values.ModValuesParticles;

public class ParticleEffectRaigo extends ParticleEffect
{

	@Override
	public void spawn(PlayerEntity player, double posX, double posY, double posZ)
	{
		for (int i = 0; i < 512; i++)
		{
			double offsetX = WyMathHelper.randomWithRange(-55, 55);
			double offsetY = WyMathHelper.randomWithRange(-5, 5);
			double offsetZ = WyMathHelper.randomWithRange(-55, 55);

			CustomParticle cp = new CustomParticle(player.world, ModValuesParticles.PARTICLE_ICON_GORO3,
					posX + offsetX + (WyMathHelper.randomDouble() * 5), 
					posY + 40 + offsetY + (WyMathHelper.randomDouble() * 5),
					posZ + offsetZ + (WyMathHelper.randomDouble() * 5), 
					0, 0, 0)
					.setParticleScale(100)
					.setParticleAge(100);
			
			if(i % 2 == 0)
				cp.setColor(0.4F, 0.4F, 0.4F);
			else
				cp.setColor(0.3F, 0.3F, 0.3F);
			
			Minecraft.getInstance().particles.addEffect(cp);
		}
		
		Timer timer = new Timer(true); 
		timer.schedule(ParticleTaskSparks.Create(player, posX, posY, posZ, 55, 5, 55), 0);		
	}
	
}
