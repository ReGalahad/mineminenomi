package xyz.pixelatedw.mineminenomi.particles.effects.gura;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.particles.CustomParticle;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.values.ModValuesParticles;

public class ParticleEffectGekishin extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		double offsetX = 0;
		double offsetZ = 0;
		
		switch(WyHelper.get4Directions(player))
		{
			case NORTH:
				offsetZ = -2.5; break;
			case SOUTH:
				offsetZ = 2.5; break;
			case EAST:
				offsetX = 2.5; break;
			case WEST:
				offsetX = -2.5; break;
			default:
				break;
		}
		
		CustomParticle cp = new CustomParticle(player.world, ModValuesParticles.PARTICLE_ICON_GURA,
				posX + offsetX , 
				posY + 1.0,
				posZ + offsetZ, 
				0, 0, 0)
				.setParticleScale(50).setParticleGravity(0).setParticleAge(10);
		Minecraft.getInstance().particles.addEffect(cp);
		
		for (int i = 0; i < 50; i++)
		{
			offsetX = (new Random().nextInt(40) + 1.0D - 20.0D) / 2.0D;
			double offsetY = (new Random().nextInt(40) + 1.0D - 20.0D) / 10.0D;
			offsetZ = (new Random().nextInt(40) + 1.0D - 20.0D) / 2.0D;
			
		}	
	}

}
