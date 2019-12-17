package xyz.pixelatedw.mineminenomi.particles.effects.toriphoenix;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.particles.SimpleParticle;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.values.ModValuesParticles;

public class ParticleEffectBlueFlames extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		for (int i = 0; i < 50; i++)
		{
			double offsetX = WyMathHelper.randomDouble();
			double offsetY = WyMathHelper.randomDouble();
			double offsetZ = WyMathHelper.randomDouble();
			
			SimpleParticle cp = new SimpleParticle(world, ModValuesParticles.PARTICLE_ICON_BLUEFLAME,
					posX + offsetX, 
					posY + 1 + offsetY,
					posZ + offsetZ, 
					0, 0, 0)
					.setParticleScale(1.2F).setParticleGravity(0).setParticleAge(1);
			Minecraft.getInstance().particles.addEffect(cp);
		}
	}

}
