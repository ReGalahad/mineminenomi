package xyz.pixelatedw.mineminenomi.entities.projectiles.doru;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class DoruDoruArtsMoriProjectile extends AbilityProjectileEntity
{
	public DoruDoruArtsMoriProjectile(World world)
	{
		super(DoruProjectiles.DORU_DORU_ARTS_MORI, world);
	}

	public DoruDoruArtsMoriProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public DoruDoruArtsMoriProjectile(World world, double x, double y, double z)
	{
		super(DoruProjectiles.DORU_DORU_ARTS_MORI, world, x, y, z);
	}

	public DoruDoruArtsMoriProjectile(World world, LivingEntity player)
	{
		super(DoruProjectiles.DORU_DORU_ARTS_MORI, world, player);

		this.setDamage(20);
	}
}