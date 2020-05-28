package xyz.pixelatedw.mineminenomi.entities.projectiles.gomu;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class GomuGomuNoBazookaProjectile extends AbilityProjectileEntity
{
	public GomuGomuNoBazookaProjectile(World world)
	{
		super(GomuProjectiles.GOMU_GOMU_NO_BAZOOKA, world);
	}

	public GomuGomuNoBazookaProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public GomuGomuNoBazookaProjectile(World world, double x, double y, double z)
	{
		super(GomuProjectiles.GOMU_GOMU_NO_BAZOOKA, world, x, y, z);
	}

	public GomuGomuNoBazookaProjectile(World world, LivingEntity player)
	{
		super(GomuProjectiles.GOMU_GOMU_NO_BAZOOKA, world, player);

		this.setDamage(10);
		this.setPhysical();
		this.setHurtTime(10);

		this.onEntityImpactEvent = this::onEntityImpactEvent;
	}
	
	private void onEntityImpactEvent(LivingEntity hitEntity)
	{
		Vec3d speed = WyHelper.propulsion(this.getThrower(), 2, 2);
		hitEntity.setMotion(speed.x, 0.2, speed.z);
		hitEntity.velocityChanged = true;
	}
}
