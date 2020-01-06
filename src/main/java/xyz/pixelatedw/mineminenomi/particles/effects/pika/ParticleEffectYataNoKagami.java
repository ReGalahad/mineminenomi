package xyz.pixelatedw.mineminenomi.particles.effects.pika;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.SimpleParticle;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;

public class ParticleEffectYataNoKagami extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		for (int i = 0; i < 20; i++)
		{
			double offsetX = (new Random().nextInt(40) + 1.0D - 20.0D) / 20.0D;
			double offsetY = (new Random().nextInt(40) + 1.0D) / 20.0D;
			double offsetZ = (new Random().nextInt(40) + 1.0D - 20.0D) / 20.0D;
			
			SimpleParticle cp = new SimpleParticle(world, ModResources.PIKA,
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
