package xyz.pixelatedw.mineminenomi.entities.projectiles.bomu;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class NoseFancyCannonProjectile extends AbilityProjectileEntity
{
	public NoseFancyCannonProjectile(World world)
	{
		super(BomuProjectiles.NOSE_FANCY_CANNON, world);
	}

	public NoseFancyCannonProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public NoseFancyCannonProjectile(World world, double x, double y, double z)
	{
		super(BomuProjectiles.NOSE_FANCY_CANNON, world, x, y, z);
	}

	public NoseFancyCannonProjectile(World world, LivingEntity player)
	{
		super(BomuProjectiles.NOSE_FANCY_CANNON, world, player);

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
		explosion.setSmokeParticles(new CommonExplosionParticleEffect(3));
		explosion.setDamageEntities(true);
		explosion.doExplosion();
	}
}
