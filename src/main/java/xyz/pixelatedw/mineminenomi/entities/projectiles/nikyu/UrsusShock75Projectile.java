package xyz.pixelatedw.mineminenomi.entities.projectiles.nikyu;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitsHelper;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class UrsusShock75Projectile extends AbilityProjectileEntity
{
	public UrsusShock75Projectile(World world)
	{
		super(NikyuProjectiles.URSUS_SHOCK_75, world);
	}

	public UrsusShock75Projectile(EntityType type, World world)
	{
		super(type, world);
	}

	public UrsusShock75Projectile(World world, double x, double y, double z)
	{
		super(NikyuProjectiles.URSUS_SHOCK_75, world, x, y, z);
	}

	public UrsusShock75Projectile(World world, LivingEntity player)
	{
		super(NikyuProjectiles.URSUS_SHOCK_75, world, player);

		this.setDamage(75);

		this.onBlockImpactEvent = this::onBlockImpactEvent;
	}
	
	private void onBlockImpactEvent(BlockPos hit)
	{
		ExplosionAbility explosion = DevilFruitsHelper.newExplosion(this.getThrower(), hit.getX(), hit.getY(), hit.getZ(), 3);
		explosion.setExplosionSound(true);
		explosion.setDamageOwner(false);
		explosion.setDestroyBlocks(true);
		explosion.setFireAfterExplosion(false);
		explosion.setSmokeParticles(new CommonExplosionParticleEffect(5));
		explosion.setDamageEntities(true);
		explosion.doExplosion();		
	}
}