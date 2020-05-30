package xyz.pixelatedw.mineminenomi.entities.projectiles.cyborg;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class StrongRightProjectile extends AbilityProjectileEntity
{
	public StrongRightProjectile(World world)
	{
		super(CyborgProjectiles.STRONG_RIGHT, world);
	}

	public StrongRightProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public StrongRightProjectile(World world, double x, double y, double z)
	{
		super(CyborgProjectiles.STRONG_RIGHT, world, x, y, z);
	}

	public StrongRightProjectile(World world, LivingEntity player)
	{
		super(CyborgProjectiles.STRONG_RIGHT, world, player);

		this.setPhysical();
		this.setDamage(20);
		this.setMaxLife(7);
	}
}