package xyz.pixelatedw.mineminenomi.entities.projectiles.yuki;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class YukiRabiProjectile extends AbilityProjectileEntity
{
	public YukiRabiProjectile(World world)
	{
		super(YukiProjectiles.YUKI_RABI, world);
	}

	public YukiRabiProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public YukiRabiProjectile(World world, double x, double y, double z)
	{
		super(YukiProjectiles.YUKI_RABI, world, x, y, z);
	}

	public YukiRabiProjectile(World world, LivingEntity player)
	{
		super(YukiProjectiles.YUKI_RABI, world, player);

		this.setDamage(5);
	}
}
