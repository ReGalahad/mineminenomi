package xyz.pixelatedw.mineminenomi.entities.projectiles.extra;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.abilities.bomu.BreezeBreathBombAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitsHelper;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;

public class NormalBulletProjectile extends AbilityProjectileEntity {

	public NormalBulletProjectile(World world) {
		super(ExtraProjectiles.NORMAL_BULLET, world);
	}

	public NormalBulletProjectile(EntityType type, World world) {
		super(type, world);
	}

	public NormalBulletProjectile(World world, double x, double y, double z) {
		super(ExtraProjectiles.NORMAL_BULLET, world, x, y, z);
	}

	public NormalBulletProjectile(World world, LivingEntity player) {
		super(ExtraProjectiles.NORMAL_BULLET, world, player);

		this.setDamage(4);
	}
}