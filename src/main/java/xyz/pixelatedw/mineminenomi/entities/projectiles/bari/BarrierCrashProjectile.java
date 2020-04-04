package xyz.pixelatedw.mineminenomi.entities.projectiles.bari;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class BarrierCrashProjectile extends AbilityProjectileEntity
{
	public BarrierCrashProjectile(World world)
	{
		super(BariProjectiles.BARRIER_CRASH, world);
	}

	public BarrierCrashProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public BarrierCrashProjectile(World world, double x, double y, double z)
	{
		super(BariProjectiles.BARRIER_CRASH, world, x, y, z);
	}

	public BarrierCrashProjectile(World world, LivingEntity player)
	{
		super(BariProjectiles.BARRIER_CRASH, world, player);

		this.setDamage(20);
		this.setMaxLife(15);
	}
}
