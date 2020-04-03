package xyz.pixelatedw.mineminenomi.entities.projectiles.bomu;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class ExplosivePunchProjectile extends AbilityProjectileEntity {

	public ExplosivePunchProjectile(World world)
	{
		super(BomuProjectiles.EXPLOSIVE_PUNCH, world);
	}

	public ExplosivePunchProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public ExplosivePunchProjectile(World world, double x, double y, double z)
	{
		super(BomuProjectiles.EXPLOSIVE_PUNCH, world, x, y, z);
	}

	public ExplosivePunchProjectile(World world, LivingEntity player)
	{
		super(BomuProjectiles.EXPLOSIVE_PUNCH, world, player);

		this.setDamage(10);
		this.setMaxLife(6);
		this.onBlockImpactEvent = this::onBlockImpactEvent;
		this.onEntityImpactEvent = this::onEntityImpactEvent;
	}
	
	private void onBlockImpactEvent(BlockPos hit)
	{		
		this.doPunchExplosion(hit);
	}
	
	private void onEntityImpactEvent(LivingEntity e) {
		BlockPos hit = e.getPosition();

		this.doPunchExplosion(hit);
	}
	//to not reuse code so i can save 2 bits of ram :)
	private void doPunchExplosion(BlockPos pos) {
		ExplosionAbility explosion = AbilityHelper.newExplosion(this.getThrower(), pos.getX(), pos.getY(), pos.getZ(), 3);
		explosion.setExplosionSound(true);
		explosion.setDamageOwner(false);
		explosion.setDestroyBlocks(true);
		explosion.setFireAfterExplosion(false);
		explosion.setSmokeParticles(new CommonExplosionParticleEffect(3));
		explosion.setDamageEntities(true);
		explosion.doExplosion();

	}
}
