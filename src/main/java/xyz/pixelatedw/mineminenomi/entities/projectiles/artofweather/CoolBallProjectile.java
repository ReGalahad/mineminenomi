package xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class CoolBallProjectile extends WeatherBallProjectile
{
	public CoolBallProjectile(World world)
	{
		super(ArtOfWeatherProjectiles.COOL_BALL, world);
	}

	public CoolBallProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public CoolBallProjectile(World world, double x, double y, double z)
	{
		super(ArtOfWeatherProjectiles.COOL_BALL, world, x, y, z);
	}

	public CoolBallProjectile(World world, LivingEntity player)
	{
		super(ArtOfWeatherProjectiles.COOL_BALL, world, player);
	}
}
