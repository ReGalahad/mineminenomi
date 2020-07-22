package xyz.pixelatedw.mineminenomi.particles.effects.common;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;

public class SpecialFlyingParticleEffect extends ParticleEffect
{
	private ResourceLocation texture;

	public SpecialFlyingParticleEffect(ResourceLocation texture)
	{
		this.texture = texture;
	}

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		for (int i = 0; i < WyHelper.randomWithRange(5, 10); i++)
		{
			double offsetX = WyHelper.randomDouble() / 1.7;
			double offsetY = -0.55 + (WyHelper.randomDouble() / 3);
			double offsetZ = WyHelper.randomDouble() / 1.7;
			
			int age = (int) (1 + WyHelper.randomWithRange(0, 20));
			motionY = (WyHelper.randomDouble() / 10) * -1;
			if(motionY > 0)
				motionY = -0.005;

			GenericParticleData data = new GenericParticleData();
			data.setTexture(this.texture);
			data.setLife(age);
			data.setSize(2.5F);
			data.setMotion(0, motionY, 0);
			WyHelper.spawnParticles(data, (ServerWorld) world, posX + offsetX, posY + 1 + offsetY, posZ + offsetZ);
		}		
	}

}
