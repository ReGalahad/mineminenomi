package xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.RainTempo;
import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.ThunderboltTempo;
import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.ThunderstormTempo;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.EntityCloud;
import xyz.pixelatedw.mineminenomi.items.weapons.ClimaTactItem;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.artofweather.WeatherCloudChargedParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.artofweather.WeatherCloudParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;

public class WeatherCloudEntity extends EntityCloud
{
	private static final ParticleEffect PARTICLES1 = new WeatherCloudParticleEffect();
	private static final ParticleEffect PARTICLES2 = new WeatherCloudChargedParticleEffect();

	private List<WeatherBallProjectile> weatherBalls = new ArrayList<WeatherBallProjectile>();
	private boolean charged = false;
	private boolean superCharged = false;

	public WeatherCloudEntity(World world)
	{
		super(world);
		this.setLife(300);
	}

	@Override
	public void tick()
	{
		super.tick();

		if (!this.world.isRemote)
		{
			ServerWorld world = (ServerWorld) this.world;
			IAbilityData props = AbilityDataCapability.get(this.getThrower());

			if (this.ticksExisted % 2 == 0)
			{
				PARTICLES1.spawn(this.world, this.posX, this.posY, this.posZ, 0, 0, 0);
				if (this.charged || this.superCharged)
					PARTICLES2.spawn(this.world, this.posX, this.posY, this.posZ, 0, 0, 0);
			}

			if (this.getLife() <= 0 || this.getThrower() == null)
				this.remove();

			this.setLife(this.getLife() - 1);

			// Cloud logic if its charged, meaning if it has 3 thunder balls in it
			if (this.charged)
			{
				List<LivingEntity> targets = WyHelper.getEntitiesNear(this.getPosition(), this.world, 50, LivingEntity.class);
				targets.remove(this.getThrower());
				targets = WyHelper.shuffle(targets);

				int thunderTimer = this.superCharged ? 30 : 50;

				for (LivingEntity entity : targets)
				{
					if (entity.posY <= this.posY && this.ticksExisted % thunderTimer == 0)
					{
						world.addLightningBolt(new LightningBoltEntity(world, entity.posX, entity.posY, entity.posZ, false));

						ExplosionAbility explosion = AbilityHelper.newExplosion(this.getThrower(), entity.posX, entity.posY, entity.posZ, 1);
						explosion.setExplosionSound(true);
						explosion.setDamageOwner(false);
						explosion.setDestroyBlocks(false);
						explosion.setFireAfterExplosion(false);
						explosion.setSmokeParticles(new CommonExplosionParticleEffect(1));
						explosion.setDamageEntities(true);
						explosion.doExplosion();

						// If its super charged it will hit every entity it finds under it, otherwise just stop after the first one
						if (!this.superCharged)
							break;
					}
				}
			}
			
			List<WeatherBallProjectile> thunderBalls = this.weatherBalls.stream().filter(ball -> ball instanceof ThunderBallProjectile).collect(Collectors.toList());
			List<WeatherBallProjectile> coolBalls = this.weatherBalls.stream().filter(ball -> ball instanceof CoolBallProjectile).collect(Collectors.toList());
					
			// Check if there are thunder balls nearby and absorb them for Thunderstorm Tempo, this can only happen if the player has a Perfect Clima Tact or higher
			// Can only occur if the cloud is charged but not super charged.
			ThunderstormTempo thunderstormTempo = props.getUnlockedAbility(ThunderstormTempo.INSTANCE);
			boolean canUseAbility = thunderstormTempo != null && thunderstormTempo.canUseTempo(this.getThrower(), (player, check) ->
			{
				if(!ItemsHelper.isClimaTact(this.getThrower().getHeldItemMainhand()))
					return false;			
				ClimaTactItem climaTact = ((ClimaTactItem) this.getThrower().getHeldItemMainhand().getItem());
				System.out.println(climaTact.getLevel());
				return climaTact.getLevel() >= 2 && !this.superCharged && this.charged && thunderBalls.size() >= 3;
			});
			if (canUseAbility)
			{
				thunderstormTempo.use(this.getThrower());
				this.superCharged = true;
				return;
			}
			
			// If there are thunder balls around it extend its life and charge the cloud for Thunderbolt Tempo
			ThunderboltTempo thunderboltTempo = props.getUnlockedAbility(ThunderboltTempo.INSTANCE);
			canUseAbility = thunderboltTempo != null && thunderboltTempo.canUseTempo(this.getThrower(), (player, check) ->
			{
				return thunderBalls.size() > 0 && !this.charged;
			});

			if (canUseAbility)
			{
				thunderboltTempo.use(this.getThrower());
				this.charged = true;
				int extraLife = 0;
				for (WeatherBallProjectile ball : thunderBalls)
				{
					ball.remove();
					extraLife += 200;
				}
				this.setLife(this.getLife() + extraLife);
			}

			// Two cool balls will trigger the Rain Tempo
			RainTempo rainTempo = props.getUnlockedAbility(RainTempo.INSTANCE);
			canUseAbility = rainTempo != null && rainTempo.canUseTempo(this.getThrower(), (player, check) ->
			{
				return coolBalls.size() >= 3;
			});

			if (canUseAbility)
			{
				rainTempo.use(this.getThrower());
				for (WeatherBallProjectile cb : coolBalls)
				{
					cb.remove();
				}
				this.remove();
			}
		}
	}

	public boolean isCharged()
	{
		return this.charged;
	}

	public boolean isSuperCharged()
	{
		return this.superCharged;
	}

	public void addWeatherBall(WeatherBallProjectile ball)
	{
		this.weatherBalls.add(ball);
	}
}