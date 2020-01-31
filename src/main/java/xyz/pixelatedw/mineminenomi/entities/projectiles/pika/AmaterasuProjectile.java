package xyz.pixelatedw.mineminenomi.entities.projectiles.pika;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.extra.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;

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
	
	private void onBlockImpactEvent(BlockRayTraceResult hit)
	{		
		ExplosionAbility explosion = WyHelper.newExplosion(this.getThrower(), this.posX, this.posY, this.posZ, 6);
		explosion.setExplosionSound(true);
		explosion.setDamageOwner(false);
		explosion.setDestroyBlocks(true);
		explosion.setFireAfterExplosion(false);
		explosion.setSmokeParticles(new CommonExplosionParticleEffect(2));
		explosion.setDamageEntities(true);
		explosion.doExplosion();
	}
}