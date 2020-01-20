package xyz.pixelatedw.mineminenomi.entities.projectiles.mera;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityProjectileEntity;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;

public class HidarumaProjectile extends AbilityProjectileEntity
{
	public HidarumaProjectile(World world)
	{
		super(MeraProjectiles.HIDARUMA, world);
	}

	public HidarumaProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public HidarumaProjectile(World world, double x, double y, double z)
	{
		super(MeraProjectiles.HIDARUMA, world, x, y, z);
	}

	public HidarumaProjectile(World world, LivingEntity player)
	{
		super(MeraProjectiles.HIDARUMA, world, player);

		this.setDamage(2);
		
		this.onEntityImpactEvent = this::onEntityImpactEvent;
		this.onBlockImpactEvent = this::onBlockImpactEvent;
		this.onTickEvent = this::onTickEvent;
	}

	private void onEntityImpactEvent(LivingEntity hitEntity)
	{
		hitEntity.setFire(200);
	}
	
	private void onBlockImpactEvent(BlockRayTraceResult hit)
	{		
		this.world.setBlockState(new BlockPos(this.posX, this.posY, this.posZ), Blocks.FIRE.getDefaultState());
	}

	private void onTickEvent()
	{
		if (!this.world.isRemote)
		{
			for (int i = 0; i < 5; i++)
			{
				double offsetX = WyMathHelper.randomDouble() / 2;
				double offsetY = WyMathHelper.randomDouble() / 2;
				double offsetZ = WyMathHelper.randomDouble() / 2;

				((ServerWorld) this.world).spawnParticle(ParticleTypes.HAPPY_VILLAGER, this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ, 1, 0, 0, 0, 0.0D);
			}
		}
	}
}
