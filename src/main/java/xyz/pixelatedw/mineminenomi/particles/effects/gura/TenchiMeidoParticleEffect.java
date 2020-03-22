package xyz.pixelatedw.mineminenomi.particles.effects.gura;

import net.minecraft.block.BlockState;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;

public class TenchiMeidoParticleEffect extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{		
		for (int i = 0; i < 50; i++)
		{
			double offsetX = WyHelper.randomWithRange(-10, 10) + WyHelper.randomDouble();
			double offsetZ = WyHelper.randomWithRange(-10, 10) + WyHelper.randomDouble();

			BlockState blockState = world.getBlockState(new BlockPos(posX + offsetX, posY, posZ + offsetZ).down());

			((ServerWorld)world).spawnParticle(new BlockParticleData(ParticleTypes.BLOCK, blockState), posX + offsetX, posY, posZ + offsetZ, 1, 0, 0.2, 0, 0);
		}
	}
	
}
