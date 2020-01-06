package xyz.pixelatedw.mineminenomi.particles.effects.zou;

import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;

public class ParticleEffectGreatStomp extends ParticleEffect
{

	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		/*
		for(int i = 0; i < 5; i++)
		{
			Timer timer = new Timer(true); 
			BlockState blockState = player.world.getBlockState(new BlockPos(posX, posY, posZ).down());
			timer.schedule(ParticleTaskWave.Create(player, player.posX, player.posY + 0.2, player.posZ, new BlockParticleData(ParticleTypes.BLOCK, blockState), 12), 0);
		}
		*/
	}

}
