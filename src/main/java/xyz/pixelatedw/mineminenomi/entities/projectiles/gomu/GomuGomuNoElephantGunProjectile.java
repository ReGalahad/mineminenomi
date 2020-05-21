package xyz.pixelatedw.mineminenomi.entities.projectiles.gomu;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class GomuGomuNoElephantGunProjectile extends AbilityProjectileEntity
{
	public GomuGomuNoElephantGunProjectile(World world)
	{
		super(GomuProjectiles.GOMU_GOMU_NO_ELEPHANT_GUN, world);
	}

	public GomuGomuNoElephantGunProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public GomuGomuNoElephantGunProjectile(World world, double x, double y, double z)
	{
		super(GomuProjectiles.GOMU_GOMU_NO_ELEPHANT_GUN, world, x, y, z);
	}

	public GomuGomuNoElephantGunProjectile(World world, LivingEntity player)
	{
		super(GomuProjectiles.GOMU_GOMU_NO_ELEPHANT_GUN, world, player);

		this.setDamage(10);
		this.setMaxLife(30);
		this.setPhysical();	
		this.setCanGetStuckInGround();
		this.setPassThroughEntities();

		this.onBlockImpactEvent = this::onBlockImpactEvent;
	}
	
	private void onBlockImpactEvent(BlockPos hit)
	{		
		ExplosionAbility explosion = AbilityHelper.newExplosion(this.getThrower(), hit.getX(), hit.getY(), hit.getZ(), 2);
		explosion.setExplosionSound(true);
		explosion.setDamageOwner(false);
		explosion.setDestroyBlocks(true);
		explosion.setFireAfterExplosion(false);
		explosion.setSmokeParticles(null);
		explosion.setDamageEntities(false);
		explosion.doExplosion();
	}
}
