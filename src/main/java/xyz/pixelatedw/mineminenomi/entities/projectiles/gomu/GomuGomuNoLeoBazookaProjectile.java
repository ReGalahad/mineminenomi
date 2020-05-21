package xyz.pixelatedw.mineminenomi.entities.projectiles.gomu;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class GomuGomuNoLeoBazookaProjectile extends AbilityProjectileEntity
{
	public GomuGomuNoLeoBazookaProjectile(World world)
	{
		super(GomuProjectiles.GOMU_GOMU_NO_LEO_BAZOOKA, world);
	}

	public GomuGomuNoLeoBazookaProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public GomuGomuNoLeoBazookaProjectile(World world, double x, double y, double z)
	{
		super(GomuProjectiles.GOMU_GOMU_NO_LEO_BAZOOKA, world, x, y, z);
	}

	public GomuGomuNoLeoBazookaProjectile(World world, LivingEntity player)
	{
		super(GomuProjectiles.GOMU_GOMU_NO_LEO_BAZOOKA, world, player);

		this.setDamage(50);
		this.setMaxLife(30);
		this.setPhysical();
		this.setPassThroughEntities();
		this.setChangeHurtTime(false);
		this.onEntityImpactEvent = this::onEntityImpactEvent;
	}
	
	private void onEntityImpactEvent(LivingEntity hitEntity)
	{
		Vec3d speed = WyHelper.propulsion(this.getThrower(), 5.5, 5.5);
		hitEntity.setMotion(speed.x, 0.2, speed.z);
		hitEntity.velocityChanged = true;
	}
}
