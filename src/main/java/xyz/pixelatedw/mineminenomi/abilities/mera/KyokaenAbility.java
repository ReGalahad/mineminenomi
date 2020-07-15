package xyz.pixelatedw.mineminenomi.abilities.mera;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.common.MinecraftForge;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.KairosekiBulletProjectile;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.NormalBulletProjectile;
import xyz.pixelatedw.mineminenomi.particles.effects.mera.KyokaenParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.wypi.abilities.events.SetOnFireEvent;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class KyokaenAbility extends ContinuousAbility {
	public static final KyokaenAbility INSTANCE = new KyokaenAbility();
	private static final KyokaenParticleEffect PARTICLES = new KyokaenParticleEffect();

	public KyokaenAbility() {
		super("Kyokaen", AbilityCategory.DEVIL_FRUIT);
		this.setThreshold(12);
		this.setDescription("Creates a wall of fire protecting its user.");

		this.duringContinuityEvent = this::duringContinuity;
		this.onEndContinuityEvent = this::onEndContinuityEvent;
	}

	private void duringContinuity(PlayerEntity player, int passiveTimer) {
		int range = 2;
		double boxSize = 1.1D;
		for (int i = 0; i < range * 2; i++) {
			double distance = i / 2D;
			Vec3d lookVec = player.getLookVec();
			Vec3d pos = new Vec3d(player.posX + lookVec.x * distance, player.posY + player.getEyeHeight() + lookVec.y * distance, player.posZ + lookVec.z * distance);
			List<Entity> list = player.world.getEntitiesInAABBexcluding(player, new AxisAlignedBB(pos.x - boxSize, pos.y - boxSize, pos.z - boxSize, pos.x + boxSize, pos.y + (boxSize * 2), pos.z + boxSize), (entity) -> { return entity != player; });

			for (Entity e : list) {
				if (e instanceof LivingEntity) {
					if (!e.isBurning()) {
						SetOnFireEvent event = new SetOnFireEvent(player, (LivingEntity) e, 3);
						if (!MinecraftForge.EVENT_BUS.post(event))
							e.setFire(3);
						e.attackEntityFrom(DamageSource.causePlayerDamage(player), 3);
					}
					Vec3d speed = WyHelper.propulsion((LivingEntity) e, 3, 3);
					e.setMotion(-speed.x, 0.5, -speed.z);
				} else if (e instanceof ArrowEntity || e instanceof KairosekiBulletProjectile || e instanceof NormalBulletProjectile) {
					e.remove();
				} else if(e instanceof AbilityProjectileEntity) {
					e.setMotion(0, -1, 0);
				}
			}
		}

		EntityRayTraceResult trace = WyHelper.rayTraceEntities(player, range);
		if (passiveTimer % 2 == 0)
			PARTICLES.spawn(player.world, trace.getHitVec().getX(), player.posY, trace.getHitVec().getZ(), 0, 0, 0);
	}

	private boolean onEndContinuityEvent(PlayerEntity player) {
		int cooldown = (int) Math.round(this.continueTime / 14.0);
		this.setMaxCooldown(cooldown);
		return true;
	}
}