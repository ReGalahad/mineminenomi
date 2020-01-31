package xyz.pixelatedw.mineminenomi.entities.projectiles.pika;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.extra.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;

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
	
	private void onBlockImpactEvent(BlockRayTraceResult hit)
	{		
		ExplosionAbility explosion = WyHelper.newExplosion(this.getThrower(), this.posX, this.posY, this.posZ, 1);
		explosion.setExplosionSound(true);
		explosion.setDamageOwner(false);
		explosion.setDestroyBlocks(true);
		explosion.setFireAfterExplosion(false);
		explosion.setSmokeParticles(new CommonExplosionParticleEffect(2));
		explosion.setDamageEntities(true);
		explosion.doExplosion();
	}
}