package xyz.pixelatedw.mineminenomi.entities.projectiles.sniper;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class RenpatsuNamariBoshiProjectile extends AbilityProjectileEntity
{	
	public RenpatsuNamariBoshiProjectile(World world)
	{
		super(SniperProjectiles.RENPATSU_NAMARI_BOSHI, world);
	}

	public RenpatsuNamariBoshiProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public RenpatsuNamariBoshiProjectile(World world, double x, double y, double z)
	{
		super(SniperProjectiles.RENPATSU_NAMARI_BOSHI, world, x, y, z);
	}

	public RenpatsuNamariBoshiProjectile(World world, LivingEntity player)
	{
		super(SniperProjectiles.RENPATSU_NAMARI_BOSHI, world, player);

		this.setDamage(10);
		
		this.onBlockImpactEvent = this::onBlockImpactEvent;
	}

	private void onBlockImpactEvent(BlockPos hit)
	{
		ExplosionAbility explosion = AbilityHelper.newExplosion(this.getThrower(), hit.getX(), hit.getY(), hit.getZ(), 3);
		explosion.setExplosionSound(true);
		explosion.setDamageOwner(false);
		explosion.setDestroyBlocks(true);
		explosion.setFireAfterExplosion(false);
		explosion.setSmokeParticles(new CommonExplosionParticleEffect(4));
		explosion.setDamageEntities(true);
		explosion.setDamageSource(this.causeAbilityProjectileDamage());
		explosion.doExplosion();
	}
}
