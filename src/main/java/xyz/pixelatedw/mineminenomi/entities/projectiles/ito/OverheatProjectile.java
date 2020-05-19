package xyz.pixelatedw.mineminenomi.entities.projectiles.ito;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class OverheatProjectile extends AbilityProjectileEntity
{
	public OverheatProjectile(World world)
	{
		super(ItoProjectiles.OVERHEAT, world);
	}

	public OverheatProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public OverheatProjectile(World world, double x, double y, double z)
	{
		super(ItoProjectiles.OVERHEAT, world, x, y, z);
	}

	public OverheatProjectile(World world, LivingEntity player)
	{
		super(ItoProjectiles.OVERHEAT, world, player);

		this.setDamage(20);
		this.setPassThroughEntities();
		
		this.onEntityImpactEvent = this::onEntityImpactEvent;
		this.onBlockImpactEvent = this::onBlockImpactEvent;
	}
	
	private void onEntityImpactEvent(LivingEntity hitEntity)
	{
		this.onBlockImpactEvent.onImpact(hitEntity.getPosition());
		hitEntity.setFire(10);
	}
	
	private void onBlockImpactEvent(BlockPos hit)
	{		
		ExplosionAbility explosion = AbilityHelper.newExplosion(this.getThrower(), hit.getX(), hit.getY(), hit.getZ(), 3);
		explosion.setExplosionSound(true);
		explosion.setDamageOwner(false);
		explosion.setDestroyBlocks(true);
		explosion.setFireAfterExplosion(false);
		explosion.setSmokeParticles(null);
		explosion.setDamageEntities(true);
		explosion.doExplosion();
	}
}
