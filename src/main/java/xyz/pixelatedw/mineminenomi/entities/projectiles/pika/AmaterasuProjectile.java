package xyz.pixelatedw.mineminenomi.entities.projectiles.pika;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitsHelper;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class AmaterasuProjectile extends AbilityProjectileEntity
{
	public AmaterasuProjectile(World world)
	{
		super(PikaProjectiles.AMATERASU, world);
	}

	public AmaterasuProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public AmaterasuProjectile(World world, double x, double y, double z)
	{
		super(PikaProjectiles.AMATERASU, world, x, y, z);
	}

	public AmaterasuProjectile(World world, LivingEntity player)
	{
		super(PikaProjectiles.AMATERASU, world, player);
		
		this.setDamage(35);
		
		this.onBlockImpactEvent = this::onBlockImpactEvent;
	}
	
	private void onBlockImpactEvent(BlockPos hit)
	{		
		ExplosionAbility explosion = DevilFruitsHelper.newExplosion(this.getThrower(), hit.getX(), hit.getY(), hit.getZ(), 6);
		explosion.setExplosionSound(true);
		explosion.setDamageOwner(false);
		explosion.setDestroyBlocks(true);
		explosion.setFireAfterExplosion(false);
		explosion.setSmokeParticles(new CommonExplosionParticleEffect(2));
		explosion.setDamageEntities(true);
		explosion.doExplosion();
	}
}