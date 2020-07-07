package xyz.pixelatedw.mineminenomi.entities.projectiles.baku;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class BeroCannonProjectile extends AbilityProjectileEntity
{
	public BeroCannonProjectile(World world)
	{
		super(BakuProjectiles.BERO_CANNON, world);
	}

	public BeroCannonProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public BeroCannonProjectile(World world, double x, double y, double z)
	{
		super(BakuProjectiles.BERO_CANNON, world, x, y, z);
	}

	public BeroCannonProjectile(World world, LivingEntity player)
	{
		super(BakuProjectiles.BERO_CANNON, world, player);

		this.setDamage(10);
		this.setPhysical();
	}
}
