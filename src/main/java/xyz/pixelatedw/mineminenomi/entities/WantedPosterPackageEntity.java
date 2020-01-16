package xyz.pixelatedw.mineminenomi.entities;

import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModEntities;

public class WantedPosterPackageEntity extends MobEntity
{

	public WantedPosterPackageEntity(World world)
	{
		super(ModEntities.WANTED_POSTER_PACKAGE, world);
	}

	@Override
	public void remove()
	{
		if(!this.onGround)
    		ItemsHelper.dropWantedPosters(this.world, (int)posX, (int)posY, (int)posZ);
		
		super.remove();
	}
	
	@Override
	public void tick()
	{
		this.setMotion(this.getMotion().getX(), this.getMotion().getY() / (1.5 + this.world.rand.nextDouble()), this.getMotion().getZ());
		this.fallDistance = 0;
		BlockPos pos = new BlockPos(this.posX, this.posY, this.posZ);
		
		if(this.onGround && !this.world.isRemote)
		{
			if(this.world.isAirBlock(pos))
			{
				this.world.setBlockState(pos, ModBlocks.WANTED_POSTER_PACKAGE.getDefaultState());
				this.remove();
			}
			else if(this.world.isAirBlock(pos.up()))
			{
				this.world.setBlockState(pos.up(), ModBlocks.WANTED_POSTER_PACKAGE.getDefaultState());
				this.remove();
			}
		}
		
		if(this.isInWater() || this.isInLava())
			this.remove();
		
		super.tick();
	}
}
