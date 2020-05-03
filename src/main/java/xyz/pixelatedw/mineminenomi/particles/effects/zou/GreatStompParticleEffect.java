package xyz.pixelatedw.mineminenomi.particles.effects.zou;

import net.minecraft.block.BlockState;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;

public class GreatStompParticleEffect extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		double phi = 0;
		double x, y, z;

		while(phi < 10)
		{
			phi += 0.1 * Math.PI;
			
			for(double theta = 0; theta <= 4 * Math.PI; theta += Math.PI / 16)
			{
				x = phi * Math.cos(theta);
				y = WyHelper.randomDouble();
				z = phi * Math.sin(theta);
										
				motionX = (WyHelper.randomDouble() / 2);
				motionY = 0;
				motionZ = (WyHelper.randomDouble() / 2);

				BlockState blockState = world.getBlockState(new BlockPos(posX, posY, posZ).down());
				
				((ServerWorld) world).spawnParticle(new BlockParticleData(ParticleTypes.BLOCK, blockState), 
						posX + WyHelper.randomWithRange(-3, 3) + x,
						posY + y,
						posZ + WyHelper.randomWithRange(-3, 3) + z,
						1, 0, 0, 0, 0);
			}
		}
	}

}
