package xyz.pixelatedw.mineminenomi.particles.effects.yami;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.init.ModParticleTextures;
import xyz.pixelatedw.mineminenomi.particles.SimpleParticle;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;

public class ParticleEffectKorouzu extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		for (int i = 0; i < 30; i++)
		{
			double offsetX = WyMathHelper.randomWithRange(-2, 2) + WyMathHelper.randomDouble();
			double offsetY = WyMathHelper.randomWithRange(-2, 2) + WyMathHelper.randomDouble();
			double offsetZ = WyMathHelper.randomWithRange(-2, 2) + WyMathHelper.randomDouble();
			
			SimpleParticle cp = new SimpleParticle(world, ModParticleTextures.DARKNESS,
					posX + offsetX , 
					posY + offsetY,
					posZ + offsetZ, 
					0, 0, 0)
					.setParticleScale(3F).setParticleGravity(0).setParticleAge(1);		
			Minecraft.getInstance().particles.addEffect(cp);
		}
	}

}
