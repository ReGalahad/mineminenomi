package xyz.pixelatedw.mineminenomi.entities.projectiles.rokushiki;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class RankyakuProjectile extends AbilityProjectileEntity
{
	public RankyakuProjectile(World world)
	{
		super(RokushikiProjectiles.RANKYAKU, world);
	}

	public RankyakuProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public RankyakuProjectile(World world, double x, double y, double z)
	{
		super(RokushikiProjectiles.RANKYAKU, world, x, y, z);
	}

	public RankyakuProjectile(World world, LivingEntity player)
	{
		super(RokushikiProjectiles.RANKYAKU, world, player);
		
		this.setDamage(40);
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
		explosion.setSmokeParticles(new CommonExplosionParticleEffect(4));
		explosion.setDamageEntities(true);
		explosion.doExplosion();
	}
}