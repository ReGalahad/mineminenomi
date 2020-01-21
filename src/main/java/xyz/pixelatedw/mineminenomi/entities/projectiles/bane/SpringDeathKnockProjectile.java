package xyz.pixelatedw.mineminenomi.entities.projectiles.bane;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.abilities.projectiles.AbilityProjectileEntity;

public class SpringDeathKnockProjectile extends AbilityProjectileEntity
{
	public SpringDeathKnockProjectile(World world)
	{
		super(BaneProjectiles.SPRING_DEATH_KNOCK, world);
	}

	public SpringDeathKnockProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public SpringDeathKnockProjectile(World world, double x, double y, double z)
	{
		super(BaneProjectiles.SPRING_DEATH_KNOCK, world, x, y, z);
	}

	public SpringDeathKnockProjectile(World world, LivingEntity player)
	{
		super(BaneProjectiles.SPRING_DEATH_KNOCK, world, player);

		this.setDamage(20);
		this.setPhysical();
	}
}
