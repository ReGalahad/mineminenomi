package xyz.pixelatedw.mineminenomi.entities.projectiles.cyborg;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class RadicalBeamProjectile extends AbilityProjectileEntity
{
	public RadicalBeamProjectile(World world)
	{
		super(CyborgProjectiles.RADICAL_BEAM, world);
	}

	public RadicalBeamProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public RadicalBeamProjectile(World world, double x, double y, double z)
	{
		super(CyborgProjectiles.RADICAL_BEAM, world, x, y, z);
	}

	public RadicalBeamProjectile(World world, LivingEntity player)
	{
		super(CyborgProjectiles.RADICAL_BEAM, world, player);
		
		this.setDamage(30);
		
		this.onBlockImpactEvent = this::onBlockImpactEvent;
	}
	
	private void onBlockImpactEvent(BlockPos hit)
	{		
		ExplosionAbility explosion = AbilityHelper.newExplosion(this.getThrower(), hit.getX(), hit.getY(), hit.getZ(), 6);
		explosion.setExplosionSound(true);
		explosion.setDamageOwner(false);
		explosion.setDestroyBlocks(true);
		explosion.setFireAfterExplosion(true);
		explosion.setSmokeParticles(new CommonExplosionParticleEffect(7));
		explosion.setDamageEntities(true);
		explosion.doExplosion();
	}
}