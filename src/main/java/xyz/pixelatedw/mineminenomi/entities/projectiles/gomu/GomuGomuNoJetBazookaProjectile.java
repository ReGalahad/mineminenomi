package xyz.pixelatedw.mineminenomi.entities.projectiles.gomu;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class GomuGomuNoJetBazookaProjectile extends AbilityProjectileEntity
{
	public GomuGomuNoJetBazookaProjectile(World world)
	{
		super(GomuProjectiles.GOMU_GOMU_NO_JET_BAZOOKA, world);
	}

	public GomuGomuNoJetBazookaProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public GomuGomuNoJetBazookaProjectile(World world, double x, double y, double z)
	{
		super(GomuProjectiles.GOMU_GOMU_NO_JET_BAZOOKA, world, x, y, z);
	}

	public GomuGomuNoJetBazookaProjectile(World world, LivingEntity player)
	{
		super(GomuProjectiles.GOMU_GOMU_NO_JET_BAZOOKA, world, player);

		this.setDamage(12);
		this.setPhysical();	
		
		this.onEntityImpactEvent = this::onEntityImpactEvent;
	}
	
	private void onEntityImpactEvent(LivingEntity hitEntity)
	{
		Vec3d speed = WyHelper.propulsion(this.getThrower(), 2.5, 2.5);
		hitEntity.setMotion(speed.x, 0.2, speed.z);
		hitEntity.velocityChanged = true;
	}
}
