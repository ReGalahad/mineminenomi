package xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.WeatherCloudTempo;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;

public class WeatherEggProjectile extends WeatherBallProjectile
{
	public WeatherEggProjectile(World world)
	{
		super(ArtOfWeatherProjectiles.WEATHER_EGG, world);
	}

	public WeatherEggProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public WeatherEggProjectile(World world, double x, double y, double z)
	{
		super(ArtOfWeatherProjectiles.WEATHER_EGG, world, x, y, z);
	}

	public WeatherEggProjectile(World world, LivingEntity player)
	{
		super(ArtOfWeatherProjectiles.WEATHER_EGG, world, player);
	}
	
	public void tick()
	{
		super.tick();

		if(this.world.isRemote || this.getThrower() == null || this.ticksExisted < 100)
			return;

		IAbilityData props = AbilityDataCapability.get(this.getThrower());
		WeatherCloudTempo ability = props.getUnlockedAbility(WeatherCloudTempo.INSTANCE);
		boolean canUseAbility = ability != null && ability.canUseTempo((PlayerEntity) this.getThrower(), (player, check) -> true);

		if (canUseAbility)
		{
			WeatherCloudEntity cloud = new WeatherCloudEntity(this.world);
			cloud.setLocationAndAngles(this.posX, (this.posY + 1), this.posZ, 0, 0);
			cloud.setMotion(0, 0, 0);
			cloud.setThrower((PlayerEntity) this.getThrower());
			this.world.addEntity(cloud);
		}

		this.remove();
	}
}
