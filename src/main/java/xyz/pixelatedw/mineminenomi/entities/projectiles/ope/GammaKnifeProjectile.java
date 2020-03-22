package xyz.pixelatedw.mineminenomi.entities.projectiles.ope;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class GammaKnifeProjectile extends AbilityProjectileEntity
{
	public GammaKnifeProjectile(World world)
	{
		super(OpeProjectiles.GAMMA_KNIFE, world);
	}

	public GammaKnifeProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public GammaKnifeProjectile(World world, double x, double y, double z)
	{
		super(OpeProjectiles.GAMMA_KNIFE, world, x, y, z);
	}

	public GammaKnifeProjectile(World world, LivingEntity player)
	{
		super(OpeProjectiles.GAMMA_KNIFE, world, player);

		this.setDamage(100);
		this.setMaxLife(5);
	}
}
