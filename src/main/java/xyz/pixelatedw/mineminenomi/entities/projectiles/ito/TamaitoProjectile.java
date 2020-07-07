package xyz.pixelatedw.mineminenomi.entities.projectiles.ito;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class TamaitoProjectile extends AbilityProjectileEntity
{
	public TamaitoProjectile(World world)
	{
		super(ItoProjectiles.TAMAITO, world);
	}

	public TamaitoProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public TamaitoProjectile(World world, double x, double y, double z)
	{
		super(ItoProjectiles.TAMAITO, world, x, y, z);
	}

	public TamaitoProjectile(World world, LivingEntity player)
	{
		super(ItoProjectiles.TAMAITO, world, player);

		this.setDamage(5);
	}
}
