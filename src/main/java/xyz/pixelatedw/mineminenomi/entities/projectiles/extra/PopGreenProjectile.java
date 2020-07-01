package xyz.pixelatedw.mineminenomi.entities.projectiles.extra;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class PopGreenProjectile extends AbilityProjectileEntity
{
	public PopGreenProjectile(World world)
	{
		super(ExtraProjectiles.POP_GREEN, world);
	}

	public PopGreenProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public PopGreenProjectile(World world, double x, double y, double z)
	{
		super(ExtraProjectiles.POP_GREEN, world, x, y, z);
	}

	public PopGreenProjectile(World world, LivingEntity player, double weightScale)
	{
		super(ExtraProjectiles.POP_GREEN, world, player);
		
		this.setDamage((float) (6 * weightScale));
	}
}