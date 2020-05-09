package xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class GustSwordProjectile extends AbilityProjectileEntity
{
	public GustSwordProjectile(World world)
	{
		super(ArtOfWeatherProjectiles.GUST_SWORD, world);
	}

	public GustSwordProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public GustSwordProjectile(World world, double x, double y, double z)
	{
		super(ArtOfWeatherProjectiles.GUST_SWORD, world, x, y, z);
	}

	public GustSwordProjectile(World world, LivingEntity player)
	{
		super(ArtOfWeatherProjectiles.GUST_SWORD, world, player);
		
		this.onTickEvent = this::onTickEvent;
		this.onEntityImpactEvent = this::onEntityImpactEvent;
	}
	
	private void onEntityImpactEvent(LivingEntity hitEntity)
	{
		Vec3d speed = WyHelper.propulsion(this.getThrower(), 2, 2);

		hitEntity.attackEntityFrom(DamageSource.causePlayerDamage((PlayerEntity) this.getThrower()), 2);
		hitEntity.setMotion(speed.x, 0.2, speed.z);
		hitEntity.velocityChanged = true;
	}
	
	private void onTickEvent()
	{
		if (!this.world.isRemote)
		{
			for (int i = 0; i < 2; i++)
			{
				double offsetX = WyHelper.randomDouble() / 3;
				double offsetY = WyHelper.randomDouble() / 3;
				double offsetZ = WyHelper.randomDouble() / 3;

				GenericParticleData data = new GenericParticleData();
				if(i % 2 == 0)
					data.setTexture(ModResources.MOKU);
				else
					data.setTexture(ModResources.MOKU2);
				data.setLife(10);
				data.setSize(1.5F);
				WyHelper.spawnParticles(data, (ServerWorld) this.world, this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ);
			}
		}	
	}
}
