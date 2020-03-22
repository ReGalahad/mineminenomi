package xyz.pixelatedw.mineminenomi.entities.projectiles.extra;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitsHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.ExtraProjectiles;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class MilkyDialProjectile extends AbilityProjectileEntity
{
	public MilkyDialProjectile(World world)
	{
		super(ExtraProjectiles.AXE_DIAL_PROJECTILE, world);
	}

	public MilkyDialProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public MilkyDialProjectile(World world, double x, double y, double z)
	{
		super(ExtraProjectiles.AXE_DIAL_PROJECTILE, world, x, y, z);
	}

	public MilkyDialProjectile(World world, LivingEntity player)
	{
		super(ExtraProjectiles.AXE_DIAL_PROJECTILE, world, player);

		this.setMaxLife(40);
		
		this.onTickEvent = this::onTickEvent;
	}
	
	private void onTickEvent()
	{
		for (BlockPos blockpos : BlockPos.getAllInBoxMutable(this.getPosition().add(-1, -1, -1), this.getPosition().add(1, 0, 1)))
		{
			int k = this.getPosition().getX() - blockpos.getX();
			int l = this.getPosition().getZ() - blockpos.getZ();
			DevilFruitsHelper.placeBlockIfAllowed(this.world, blockpos, ModBlocks.SKY_BLOCK, 4, "air");
		}
	}
}