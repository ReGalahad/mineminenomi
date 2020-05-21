package xyz.pixelatedw.mineminenomi.entities.projectiles.gomu;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class GomuGomuNoKongGunProjectile extends AbilityProjectileEntity
{
	public GomuGomuNoKongGunProjectile(World world)
	{
		super(GomuProjectiles.GOMU_GOMU_NO_KONG_GUN, world);
	}

	public GomuGomuNoKongGunProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public GomuGomuNoKongGunProjectile(World world, double x, double y, double z)
	{
		super(GomuProjectiles.GOMU_GOMU_NO_KONG_GUN, world, x, y, z);
	}

	public GomuGomuNoKongGunProjectile(World world, LivingEntity player)
	{
		super(GomuProjectiles.GOMU_GOMU_NO_KONG_GUN, world, player);

		this.setDamage(15);
		this.setMaxLife(30);
		this.setPhysical();	
		this.setPassThroughBlocks();
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
