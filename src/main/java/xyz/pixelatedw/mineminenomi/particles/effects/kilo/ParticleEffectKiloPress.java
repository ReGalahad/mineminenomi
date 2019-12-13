package xyz.pixelatedw.mineminenomi.particles.effects.kilo;

import java.util.Timer;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.tasks.ParticleTaskWave;

public class ParticleEffectKiloPress extends ParticleEffect
{

	@Override
	public void spawn(PlayerEntity player, double posX, double posY, double posZ)
	{
		for(int i = 0; i < 8; i++)
		{
			Timer timer = new Timer(true); 
			BlockState BlockState = player.world.getBlockState(new BlockPos(posX, posY, posZ).down());
			timer.schedule(ParticleTaskWave.Create(player, player.posX, player.posY - 1.5, player.posZ, new BlockParticleData(ParticleTypes.BLOCK, BlockState), 4), 0);
		}
	}

}
