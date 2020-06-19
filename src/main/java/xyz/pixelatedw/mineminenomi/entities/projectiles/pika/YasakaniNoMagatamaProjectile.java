package xyz.pixelatedw.mineminenomi.entities.projectiles.pika;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;
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
		
		this.setDamage(2.5F);
		this.setChangeHurtTime(true);
		this.setHurtTime(20);
		
		this.onBlockImpactEvent = this::onBlockImpactEvent;
		this.onTickEvent = this::onTickEvent;
	}
	
	private void onBlockImpactEvent(BlockPos hit)
	{		
		ExplosionAbility explosion = AbilityHelper.newExplosion(this.getThrower(), hit.getX(), hit.getY(), hit.getZ(), 1);
		explosion.setExplosionSound(true);
		explosion.setDamageOwner(false);
		explosion.setDestroyBlocks(true);
		explosion.setFireAfterExplosion(false);
		explosion.setSmokeParticles(new CommonExplosionParticleEffect(2));
		explosion.setDamageEntities(true);
		explosion.doExplosion();
	}
	
	private void onTickEvent()
	{
		if (!this.world.isRemote)
		{
			GenericParticleData data = new GenericParticleData();
			data.setTexture(ModResources.PIKA);
			data.setLife(2);
			data.setSize(2.5F);
			data.setHasRotation();
			WyHelper.spawnParticles(data, (ServerWorld) this.world, this.posX, this.posY , this.posZ);
		}
	}
}