package xyz.pixelatedw.mineminenomi.entities.projectiles.extra;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class KujaArrowProjectile extends AbilityProjectileEntity
{
	public KujaArrowProjectile(World world)
	{
		super(ExtraProjectiles.KUJA_ARROW, world);
	}

	public KujaArrowProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public KujaArrowProjectile(World world, double x, double y, double z)
	{
		super(ExtraProjectiles.KUJA_ARROW, world, x, y, z);
	}

	public KujaArrowProjectile(World world, LivingEntity player)
	{
		super(ExtraProjectiles.KUJA_ARROW, world, player);
		this.setPhysical();
		this.setDamage(10);
	}
}