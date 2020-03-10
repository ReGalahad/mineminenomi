package xyz.pixelatedw.mineminenomi.particles.effects.yuki;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.SimpleParticle;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;

public class FubukiParticleEffect extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		for (int i = 0; i < 1024; i++)
		{
			double offsetX = (new Random().nextInt(50) + 1.0D - 25.0D) / 1.0D;
			double offsetY = (new Random().nextInt(50) + 1.0D - 25.0D) / 1.0D;
			double offsetZ = (new Random().nextInt(50) + 1.0D - 25.0D) / 1.0D;

			motionX = WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble();
			motionY = WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble();
			motionZ = WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble();

			double middlePoint = 0.2D;
			middlePoint *= (WyHelper.randomDouble() * 2) + 0.3F;

			motionX *= middlePoint / 2;
			motionY *= middlePoint / 2;
			motionZ *= middlePoint / 2;

			SimpleParticle cp = new SimpleParticle(world, ModResources.YUKI,
					posX + offsetX, 
					posY + offsetY,
					posZ + offsetZ, 
					motionX, 
					0, 
					motionZ)
					.setParticleAge(300).setParticleGravity(3).setParticleScale((float) (1 + WyHelper.randomWithRange(0, 2)));
			Minecraft.getInstance().particles.addEffect(cp);
		}		
	}

}
