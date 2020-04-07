package xyz.pixelatedw.mineminenomi.entities.projectiles.supa;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class SpiralHollowProjectile extends AbilityProjectileEntity
{
	public SpiralHollowProjectile(World world)
	{
		super(SupaProjectiles.SPIRAL_HOLLOW, world);
	}

	public SpiralHollowProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public SpiralHollowProjectile(World world, double x, double y, double z)
	{
		super(SupaProjectiles.SPIRAL_HOLLOW, world, x, y, z);
	}

	public SpiralHollowProjectile(World world, LivingEntity player)
	{
		super(SupaProjectiles.SPIRAL_HOLLOW, world, player);

		this.setDamage(20);
		this.setMaxLife(5);
		this.setPhysical();
	}
}
