package xyz.pixelatedw.mineminenomi.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DemonPoisonBlock extends PoisonBlock
{

	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity)
	{
	   	if(entity instanceof LivingEntity)
    	{
			if(!((LivingEntity)entity).isPotionActive(Effects.POISON))
			{
				((LivingEntity)entity).addPotionEffect(new EffectInstance(Effects.POISON, 300, 2));
			}
    	}
	}
	
}
