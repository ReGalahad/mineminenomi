package xyz.pixelatedw.mineminenomi.entities.projectiles.pika;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitsHelper;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class YasakaniNoMagatamaProjectile extends AbilityProjectileEntity
{
	public YasakaniNoMagatamaProjectile(World world)
	{
		super(PikaProjectiles.YASAKANI_NO_MAGATAMA, world);
	}

	public YasakaniNoMagatamaProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public YasakaniNoMagatamaProjectile(World world, double x, double y, double z)
	{
		super(PikaProjectiles.YASAKANI_NO_MAGATAMA, world, x, y, z);
	}

	public YasakaniNoMagatamaProjectile(World world, LivingEntity player)
	{
		super(PikaProjectiles.YASAKANI_NO_MAGATAMA, world, player);
		
		this.setDamage(2);
		
		this.onBlockImpactEvent = this::onBlockImpactEvent;
	}
	
	private void onBlockImpactEvent(BlockPos hit)
	{		
		ExplosionAbility explosion = DevilFruitsHelper.newExplosion(this.getThrower(), hit.getX(), hit.getY(), hit.getZ(), 1);
		explosion.setExplosionSound(true);
		explosion.setDamageOwner(false);
		explosion.setDestroyBlocks(true);
		explosion.setFireAfterExplosion(false);
		explosion.setSmokeParticles(new CommonExplosionParticleEffect(2));
		explosion.setDamageEntities(true);
		explosion.doExplosion();
	}
}