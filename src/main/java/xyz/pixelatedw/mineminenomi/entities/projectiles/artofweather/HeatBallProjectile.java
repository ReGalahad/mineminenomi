package xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class HeatBallProjectile extends WeatherBallProjectile
{
	public HeatBallProjectile(World world)
	{
		super(ArtOfWeatherProjectiles.HEAT_BALL, world);
	}

	public HeatBallProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public HeatBallProjectile(World world, double x, double y, double z)
	{
		super(ArtOfWeatherProjectiles.HEAT_BALL, world, x, y, z);
	}

	public HeatBallProjectile(World world, LivingEntity player)
	{
		super(ArtOfWeatherProjectiles.HEAT_BALL, world, player);
		
		this.setMaxLife(300);
	}
}
