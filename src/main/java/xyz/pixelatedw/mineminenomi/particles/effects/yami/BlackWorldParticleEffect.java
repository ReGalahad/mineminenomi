package xyz.pixelatedw.mineminenomi.particles.effects.yami;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.SimpleParticle;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;

public class BlackWorldParticleEffect extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		for (int i = 0; i < 2048 * 2; i++)
		{
			double offsetX = (new Random().nextInt(40) + 1.0D - 20.0D) / 2.0D;
			double offsetY = (new Random().nextInt(40) + 1.0D - 20.0D) / 35.0D;
			double offsetZ = (new Random().nextInt(40) + 1.0D - 20.0D) / 2.0D;
			
			SimpleParticle cp = new SimpleParticle(world, ModResources.DARKNESS,
					posX + offsetX + WyHelper.randomWithRange(0, 5), 
					posY + offsetY,
					posZ + offsetZ + WyHelper.randomWithRange(0, 5), 
					0, 0, 0)
					.setParticleGravity(-1 + (world.rand.nextInt(2) * -1))
					.setParticleAge(30 + world.rand.nextInt(10))
					.setParticleScale(1.2F);
			Minecraft.getInstance().particles.addEffect(cp);

			cp = new SimpleParticle(world, ModResources.DARKNESS,
					posX + offsetX + WyHelper.randomWithRange(0, 5), 
					posY + 1.5 + offsetY,
					posZ + offsetZ + WyHelper.randomWithRange(0, 5), 
					0, 0, 0)
					.setParticleGravity(-1 + (world.rand.nextInt(2) * -1))
					.setParticleAge(30 + world.rand.nextInt(10))
					.setParticleScale(1.2F);
			Minecraft.getInstance().particles.addEffect(cp);

			cp = new SimpleParticle(world, ModResources.DARKNESS,
					posX + offsetX + WyHelper.randomWithRange(0, 5), 
					posY + 2.5 + offsetY,
					posZ + offsetZ + WyHelper.randomWithRange(0, 5), 
					0, 0, 0)
					.setParticleGravity(-1 + (world.rand.nextInt(2) * -1))
					.setParticleAge(30 + world.rand.nextInt(10))
					.setParticleScale(1.2F);
			Minecraft.getInstance().particles.addEffect(cp);
		}		

	}

}
