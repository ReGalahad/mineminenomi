package xyz.pixelatedw.mineminenomi.entities.projectiles.mera;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

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

	private void onBlockImpactEvent(BlockPos hit)
	{
		if(CommonConfig.instance.isGriefingEnabled())
		{
			for(int j = -2; j <= 2; j++)
			{
				for(int i = -5; i <= 5; i++)
					if(this.world.isAirBlock(new BlockPos(hit.getX() + i, hit.getY() + j, hit.getZ())))
						this.world.setBlockState(new BlockPos(hit.getX() + i, hit.getY() + j, hit.getZ()), Blocks.FIRE.getDefaultState());
					
				for(int i = -5; i <= 5; i++)
					if(this.world.isAirBlock(new BlockPos(hit.getX(), hit.getY() + j, hit.getZ() + i)))
						this.world.setBlockState(new BlockPos(hit.getX(), hit.getY() + j, hit.getZ() + i), Blocks.FIRE.getDefaultState());
			}
		}	
	}
}
