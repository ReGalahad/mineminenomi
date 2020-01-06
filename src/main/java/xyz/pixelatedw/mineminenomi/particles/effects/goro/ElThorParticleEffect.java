package xyz.pixelatedw.mineminenomi.particles.effects.goro;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.init.ModParticleTextures;
import xyz.pixelatedw.mineminenomi.particles.SimpleParticle;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;

public class ElThorParticleEffect extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		for (int i = 0; i < 30; i++)
		{
			double offsetX = WyMathHelper.randomDouble();
			double offsetY = WyMathHelper.randomDouble();
			double offsetZ = WyMathHelper.randomDouble();
			
			SimpleParticle cp = new SimpleParticle(world, ModParticleTextures.GORO2,
					posX + offsetX, 
					posY + offsetY,
					posZ +offsetZ, 
					0, 0, 0)
					.setParticleScale(3)
					.setParticleGravity(0)
					.setParticleAge(1);
			Minecraft.getInstance().particles.addEffect(cp);
		}
	}

}
