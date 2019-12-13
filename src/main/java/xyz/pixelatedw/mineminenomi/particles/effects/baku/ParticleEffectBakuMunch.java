package xyz.pixelatedw.mineminenomi.particles.effects.baku;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;

public class ParticleEffectBakuMunch extends ParticleEffect
{

	@Override
	public void spawn(PlayerEntity player, double posX, double posY, double posZ)
	{
		for (int i = 0; i < 15; i++)
		{
			double offsetX = player.world.rand.nextDouble();
			double offsetY = 1;
			double offsetZ = player.world.rand.nextDouble();

			BlockState blockState = player.world.getBlockState(new BlockPos(posX, posY, posZ).down());
			
			player.world.addParticle(
					new BlockParticleData(ParticleTypes.BLOCK, blockState), 
					posX + offsetX, 
					posY + offsetY, 
					posZ + offsetZ, 
					0, 0, 0);
		}
	}

}
