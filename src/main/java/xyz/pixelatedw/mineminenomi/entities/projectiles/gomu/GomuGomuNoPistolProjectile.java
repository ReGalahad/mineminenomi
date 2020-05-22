package xyz.pixelatedw.mineminenomi.entities.projectiles.gomu;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class GomuGomuNoPistolProjectile extends AbilityProjectileEntity
{
	public GomuGomuNoPistolProjectile(World world)
	{
		super(GomuProjectiles.GOMU_GOMU_NO_PISTOL, world);
	}

	public GomuGomuNoPistolProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public GomuGomuNoPistolProjectile(World world, double x, double y, double z)
	{
		super(GomuProjectiles.GOMU_GOMU_NO_PISTOL, world, x, y, z);
	}

	public GomuGomuNoPistolProjectile(World world, LivingEntity player)
	{
		super(GomuProjectiles.GOMU_GOMU_NO_PISTOL, world, player);

		this.setDamage(6);
		this.setPhysical();	
	}
}
