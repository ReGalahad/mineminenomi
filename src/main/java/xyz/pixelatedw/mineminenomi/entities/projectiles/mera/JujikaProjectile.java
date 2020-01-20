package xyz.pixelatedw.mineminenomi.entities.projectiles.mera;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityProjectileEntity;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;

public class JujikaProjectile extends AbilityProjectileEntity
{
	public JujikaProjectile(World world)
	{
		super(MeraProjectiles.JUJIKA, world);
	}

	public JujikaProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public JujikaProjectile(World world, double x, double y, double z)
	{
		super(MeraProjectiles.JUJIKA, world, x, y, z);
	}

	public JujikaProjectile(World world, LivingEntity player)
	{
		super(MeraProjectiles.JUJIKA, world, player);

		this.onBlockImpactEvent = this::onBlockImpactEvent;
	}

	private void onBlockImpactEvent(BlockRayTraceResult hit)
	{
		if(CommonConfig.instance.isGriefingEnabled())
		{
			for(int j = -2; j <= 2; j++)
			{
				for(int i = -5; i <= 5; i++)
					if(this.world.isAirBlock(new BlockPos(this.posX + i, this.posY + j, this.posZ)))
						this.world.setBlockState(new BlockPos(this.posX + i, this.posY + j, this.posZ), Blocks.FIRE.getDefaultState());
					
				for(int i = -5; i <= 5; i++)
					if(this.world.isAirBlock(new BlockPos(this.posX, this.posY + j, this.posZ + i)))
						this.world.setBlockState(new BlockPos(this.posX, this.posY + j, this.posZ + i), Blocks.FIRE.getDefaultState());
			}
		}	
	}
}
