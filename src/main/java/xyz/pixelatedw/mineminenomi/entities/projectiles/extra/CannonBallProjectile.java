package xyz.pixelatedw.mineminenomi.entities.projectiles.extra;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class CannonBallProjectile extends AbilityProjectileEntity
{

	public CannonBallProjectile(World world)
	{
		super(ExtraProjectiles.CANNON_BALL, world);
	}

	public CannonBallProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public CannonBallProjectile(World world, double x, double y, double z)
	{
		super(ExtraProjectiles.CANNON_BALL, world, x, y, z);
	}

	public CannonBallProjectile(World world, LivingEntity player)
	{
		super(ExtraProjectiles.CANNON_BALL, world, player);
		this.setPhysical();
		this.setDamage(10);
		this.setGravity(0.01f);
		
		this.onBlockImpactEvent = this::onBlockImpactEvent;
	}
	
	private void onBlockImpactEvent(BlockPos hit)
	{		
		if(this.ticksExisted < 2)
			return;
		
		ExplosionAbility explosion = AbilityHelper.newExplosion(this.getThrower(), hit.getX(), hit.getY(), hit.getZ(), 2);
		explosion.setExplosionSound(true);
		explosion.setDamageOwner(false);
		explosion.setDestroyBlocks(true);
		explosion.setFireAfterExplosion(true);
		explosion.setSmokeParticles(new CommonExplosionParticleEffect(2));
		explosion.setDamageEntities(true);
		explosion.doExplosion();
	}
}