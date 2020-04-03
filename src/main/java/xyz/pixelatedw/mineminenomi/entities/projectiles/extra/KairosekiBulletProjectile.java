package xyz.pixelatedw.mineminenomi.entities.projectiles.extra;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitsHelper;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class KairosekiBulletProjectile extends AbilityProjectileEntity
{
	public KairosekiBulletProjectile(World world)
	{
		super(ExtraProjectiles.KAIROSEKI_BULLET, world);
	}

	public KairosekiBulletProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public KairosekiBulletProjectile(World world, double x, double y, double z)
	{
		super(ExtraProjectiles.KAIROSEKI_BULLET, world, x, y, z);
	}

	public KairosekiBulletProjectile(World world, LivingEntity player)
	{
		super(ExtraProjectiles.KAIROSEKI_BULLET, world, player);
		
		this.setDamage(6);
	}


}