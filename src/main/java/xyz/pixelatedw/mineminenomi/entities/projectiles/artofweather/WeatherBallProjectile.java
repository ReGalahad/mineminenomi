package xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather;

import java.util.List;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.WeatherCloudTempo;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;

public class WeatherBallProjectile extends AbilityProjectileEntity
{
	protected Item weaponUsed;
	
	public WeatherBallProjectile(EntityType type, World world)
	{
		super(type, world);
	}
	
	public WeatherBallProjectile(EntityType type, World world, double x, double y, double z)
	{
		super(type, world, x, y, z);
	}
	
	public WeatherBallProjectile(EntityType type, World world, LivingEntity player)
	{
		super(type, world, player);
		
		this.setMaxLife(300);
	}
	
	@Override
	public void tick()
	{
		super.tick();

		this.setMotion(this.getMotion().x / 1.5, this.getMotion().y, this.getMotion().z / 1.5);
		if(this.ticksExisted < 100)
			this.getMotion().add(0, 0.25, 0);
		else
			this.setMotion(0, 0, 0);

		if(this.world.isRemote || this.getThrower() == null || this.ticksExisted < 70)
			return;

		List<WeatherCloudEntity> clouds = WyHelper.getEntitiesNear(this.getPosition(), this.world, 5, WeatherCloudEntity.class);
		
		if(clouds.size() > 0)
		{
			clouds.get(0).addWeatherBall(this);
			this.remove();
			return;
		}
		
		if(this instanceof CoolBallProjectile)
		{
			List<HeatBallProjectile> heatBalls = WyHelper.getEntitiesNear(this.getPosition(), this.world, 4, HeatBallProjectile.class);
			IAbilityData props = AbilityDataCapability.get(this.getThrower());
			WeatherCloudTempo ability = props.getUnlockedAbility(WeatherCloudTempo.INSTANCE);
			boolean canUseAbility = ability != null && ability.canUseTempo((PlayerEntity) this.getThrower(), (player, check) -> 
			{
				return heatBalls.size() > 0;
			});
			
			if(canUseAbility)
			{
				for (HeatBallProjectile heatBall : heatBalls)
				{
					ability.use((PlayerEntity) this.getThrower());
					
					WeatherCloudEntity cloud = new WeatherCloudEntity(this.world);
					cloud.setLocationAndAngles(this.posX, (this.posY + 1), this.posZ, 0, 0);
					cloud.setMotion(0, 0, 0);
					cloud.setThrower((PlayerEntity) this.getThrower());
					this.world.addEntity(cloud);
					
					heatBall.remove();
				}
				this.remove();
			}
		}
	}
	
	public Item getWeaponUsed()
	{
		return this.weaponUsed;
	}
}
