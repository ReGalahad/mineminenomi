package xyz.pixelatedw.mineminenomi.entities.projectiles.swordsman;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class YakkodoriProjectile extends AbilityProjectileEntity
{
	public YakkodoriProjectile(World world)
	{
		super(SwordsmanProjectiles.YAKKODORI, world);
	}

	public YakkodoriProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public YakkodoriProjectile(World world, double x, double y, double z)
	{
		super(SwordsmanProjectiles.YAKKODORI, world, x, y, z);
	}

	public YakkodoriProjectile(World world, LivingEntity player)
	{
		super(SwordsmanProjectiles.YAKKODORI, world, player);
		
		this.setDamage(10);
		this.setCanGetStuckInGround();
		this.setPhysical();

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