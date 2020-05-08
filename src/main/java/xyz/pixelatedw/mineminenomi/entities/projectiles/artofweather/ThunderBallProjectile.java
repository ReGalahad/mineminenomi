package xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class ThunderBallProjectile extends WeatherBallProjectile
{
	public ThunderBallProjectile(World world)
	{
		super(ArtOfWeatherProjectiles.THUNDER_BALL, world);
	}

	public ThunderBallProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public ThunderBallProjectile(World world, double x, double y, double z)
	{
		super(ArtOfWeatherProjectiles.THUNDER_BALL, world, x, y, z);
	}

	public ThunderBallProjectile(World world, LivingEntity player)
	{
		super(ArtOfWeatherProjectiles.THUNDER_BALL, world, player);
	}
}
