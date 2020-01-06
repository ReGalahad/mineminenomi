package xyz.pixelatedw.mineminenomi.particles.effects.suna;

import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;

public class ParticleEffectDesertSpada extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		/*
		Random rand = world.rand;
		
		for(int i = 0; i < 200; i++)
		{
			double x = 0;
			double z = 0;

			motionX = 0;
			motionY = 0.05 + (WyMathHelper.randomDouble() / 50);
			motionZ = 0;
			
			if(WyHelper.get4Directions(player) == WyHelper.Direction.NORTH)
			{
				x = WyMathHelper.randomWithRange(-4, 4) + WyMathHelper.randomDouble();
				z = -i * 0.2;
			}
			else if(WyHelper.get4Directions(player) == WyHelper.Direction.EAST)
			{
				x = i * 0.2;
				z = WyMathHelper.randomWithRange(-4, 4) + WyMathHelper.randomDouble();
			}
			else if(WyHelper.get4Directions(player) == WyHelper.Direction.SOUTH)
			{
				x = WyMathHelper.randomWithRange(-4, 4) + WyMathHelper.randomDouble();
				z = i * 0.2;
			}
			else if(WyHelper.get4Directions(player) == WyHelper.Direction.WEST)
			{
				x = -i * 0.2;
				z = WyMathHelper.randomWithRange(-4, 4) + WyMathHelper.randomDouble();
			}
			
			CustomParticle cp = new CustomParticle(player.world, ModParticleTextures.SUNA,
					posX + x, 
					posY + 0.1,
					posZ + z, 
					motionX, 
					motionY, 
					motionZ)
					.setParticleAge(10).setParticleScale(5);
			Minecraft.getInstance().particles.addEffect(cp);
			
		}
		*/
	}
}
