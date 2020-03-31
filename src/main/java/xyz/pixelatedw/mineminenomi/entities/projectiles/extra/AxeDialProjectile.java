package xyz.pixelatedw.mineminenomi.entities.projectiles.extra;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitsHelper;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class AxeDialProjectile extends AbilityProjectileEntity
{
	public AxeDialProjectile(World world)
	{
		super(ExtraProjectiles.AXE_DIAL_PROJECTILE, world);
	}

	public AxeDialProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public AxeDialProjectile(World world, double x, double y, double z)
	{
		super(ExtraProjectiles.AXE_DIAL_PROJECTILE, world, x, y, z);
	}

	public AxeDialProjectile(World world, LivingEntity player)
	{
		super(ExtraProjectiles.AXE_DIAL_PROJECTILE, world, player);
		
		this.setDamage(7);
		
		this.onBlockImpactEvent = this::onBlockImpactEvent;
	}
	
	private void onBlockImpactEvent(BlockPos pos)
	{
		ExplosionAbility explosion = DevilFruitsHelper.newExplosion(this, pos.getX(), pos.getY(), pos.getZ(), 4);
		explosion.doExplosion();
	}
}