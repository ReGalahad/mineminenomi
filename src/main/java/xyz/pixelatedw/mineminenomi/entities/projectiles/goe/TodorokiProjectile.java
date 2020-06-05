package xyz.pixelatedw.mineminenomi.entities.projectiles.goe;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class TodorokiProjectile extends AbilityProjectileEntity
{
	public TodorokiProjectile(World world)
	{
		super(GoeProjectiles.TODOROKI, world);
	}

	public TodorokiProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public TodorokiProjectile(World world, double x, double y, double z)
	{
		super(GoeProjectiles.TODOROKI, world, x, y, z);
	}

	public TodorokiProjectile(World world, LivingEntity player)
	{
		super(GoeProjectiles.TODOROKI, world, player);

		this.setDamage(8);
		this.setPassThroughEntities();
		
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
		explosion.doExplosion();
	}
}
