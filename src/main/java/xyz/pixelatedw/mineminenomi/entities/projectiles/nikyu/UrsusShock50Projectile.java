package xyz.pixelatedw.mineminenomi.entities.projectiles.nikyu;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class UrsusShock50Projectile extends AbilityProjectileEntity
{
	public UrsusShock50Projectile(World world)
	{
		super(NikyuProjectiles.URSUS_SHOCK_50, world);
	}

	public UrsusShock50Projectile(EntityType type, World world)
	{
		super(type, world);
	}

	public UrsusShock50Projectile(World world, double x, double y, double z)
	{
		super(NikyuProjectiles.URSUS_SHOCK_50, world, x, y, z);
	}

	public UrsusShock50Projectile(World world, LivingEntity player)
	{
		super(NikyuProjectiles.URSUS_SHOCK_50, world, player);

		this.setDamage(50);

		this.onBlockImpactEvent = this::onBlockImpactEvent;
	}
	
	private void onBlockImpactEvent(BlockPos hit)
	{
		ExplosionAbility explosion = AbilityHelper.newExplosion(this.getThrower(), hit.getX(), hit.getY(), hit.getZ(), 8);
		explosion.setExplosionSound(true);
		explosion.setDamageOwner(false);
		explosion.setDestroyBlocks(true);
		explosion.setFireAfterExplosion(false);
		explosion.setSmokeParticles(new CommonExplosionParticleEffect(6));
		explosion.setDamageEntities(true);
		explosion.doExplosion();		
	}
}