package xyz.pixelatedw.mineminenomi.entities.projectiles.swordsman;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class SanbyakurokujuPoundHoProjectile extends AbilityProjectileEntity
{
	public SanbyakurokujuPoundHoProjectile(World world)
	{
		super(SwordsmanProjectiles.SANBYAKUROKUJU_POUND_HO, world);
	}

	public SanbyakurokujuPoundHoProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public SanbyakurokujuPoundHoProjectile(World world, double x, double y, double z)
	{
		super(SwordsmanProjectiles.SANBYAKUROKUJU_POUND_HO, world, x, y, z);
	}

	public SanbyakurokujuPoundHoProjectile(World world, LivingEntity player)
	{
		super(SwordsmanProjectiles.SANBYAKUROKUJU_POUND_HO, world, player);
		
		this.setDamage(20);
		this.setPassThroughEntities();
		this.setPhysical();

		this.onBlockImpactEvent = this::onBlockImpactEvent;
	}
	
	private void onBlockImpactEvent(BlockPos hit)
	{
		ExplosionAbility explosion = AbilityHelper.newExplosion(this.getThrower(), hit.getX(), hit.getY(), hit.getZ(), 2);
		explosion.setExplosionSound(true);
		explosion.setDamageOwner(false);
		explosion.setDestroyBlocks(false);
		explosion.setFireAfterExplosion(false);
		explosion.setSmokeParticles(new CommonExplosionParticleEffect(4));
		explosion.setDamageEntities(true);
		explosion.doExplosion();
	}
}