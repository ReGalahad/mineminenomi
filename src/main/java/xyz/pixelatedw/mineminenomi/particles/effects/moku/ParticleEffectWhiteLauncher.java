package xyz.pixelatedw.mineminenomi.particles.effects.moku;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.init.ModParticleTextures;
import xyz.pixelatedw.mineminenomi.particles.SimpleParticle;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;

public class ParticleEffectWhiteLauncher extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		for (int i = 0; i < 20; i++)
		{
			double offsetX = (new Random().nextInt(20) + 1.0D - 10.0D) / 15.0D;
			double offsetY = (new Random().nextInt(20) + 1.0D - 10.0D) / 10.0D;
			double offsetZ = (new Random().nextInt(20) + 1.0D - 10.0D) / 15.0D;
	      
			SimpleParticle cp = new SimpleParticle(world, ModParticleTextures.MOKU,
					posX + offsetX, 
					posY + 1 + offsetY,
					posZ + offsetZ, 
					0, 0, 0)
					.setParticleAge(20).setParticleGravity(0).setParticleScale(1.3F);
			Minecraft.getInstance().particles.addEffect(cp);
		}
	}

}
