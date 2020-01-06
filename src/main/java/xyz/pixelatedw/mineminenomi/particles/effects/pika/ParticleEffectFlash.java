package xyz.pixelatedw.mineminenomi.particles.effects.pika;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.SimpleParticle;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;

public class ParticleEffectFlash extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		SimpleParticle cp = new SimpleParticle(world, ModResources.PIKA,
				posX, 
				posY + 3.5,
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
