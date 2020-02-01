package xyz.pixelatedw.mineminenomi.entities.projectiles.suke;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.extra.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;

public class ShishaNoTeProjectile  extends AbilityProjectileEntity
{
	public ShishaNoTeProjectile(World world)
	{
		super(SukeProjectiles.SHISHA_NO_TE, world);
	}

	public ShishaNoTeProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public ShishaNoTeProjectile(World world, double x, double y, double z)
	{
		super(SukeProjectiles.SHISHA_NO_TE, world, x, y, z);
	}

	public ShishaNoTeProjectile(World world, LivingEntity player)
	{
		super(SukeProjectiles.SHISHA_NO_TE, world, player);

		this.setDamage(20);
		
		this.onBlockImpactEvent = this::onBlockImpactEvent;
	}
	
	private void onBlockImpactEvent(BlockRayTraceResult hit)
	{		
		ExplosionAbility explosion = WyHelper.newExplosion(this.getThrower(), this.posX, this.posY, this.posZ, 3);
		explosion.setExplosionSound(true);
		explosion.setDamageOwner(false);
		explosion.setDestroyBlocks(true);
		explosion.setFireAfterExplosion(false);
		explosion.setSmokeParticles(new CommonExplosionParticleEffect(2));
		explosion.setDamageEntities(true);
		explosion.doExplosion();
	}
}
