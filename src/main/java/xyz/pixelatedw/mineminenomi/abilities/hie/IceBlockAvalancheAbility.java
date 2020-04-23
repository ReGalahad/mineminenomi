package xyz.pixelatedw.mineminenomi.abilities.hie;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import xyz.pixelatedw.mineminenomi.entities.projectiles.hie.IceBlockAvalancheProjectile;
import xyz.pixelatedw.mineminenomi.particles.effects.hie.IceBlockAvalancheParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.ChargeableAbility;

public class IceBlockAvalancheAbility extends ChargeableAbility {

	public static final IceBlockAvalancheAbility INSTANCE = new IceBlockAvalancheAbility();
	private IceBlockAvalancheProjectile proj;
	private int dmg = 0;
	public static final IceBlockAvalancheParticleEffect PARTICLES = new IceBlockAvalancheParticleEffect();

	public IceBlockAvalancheAbility() {
		super("Ice Block: Avalanche", AbilityCategory.DEVIL_FRUIT);
		this.setMaxChargeTime(6);
		this.setMaxCooldown(15);
		this.onStartChargingEvent = this::onStartChargingEvent;
		this.duringChargingEvent = this::duringChargingEvent;
		this.onEndChargingEvent = this::onEndChargingEvent;
		this.setCancelable();
	}

	private boolean onStartChargingEvent(PlayerEntity p) {
		BlockRayTraceResult ray = WyHelper.rayTraceBlocksWithDistance(p, 20);
		EntityRayTraceResult eray = WyHelper.rayTraceEntities(p, 50);
		this.removeDuplicate();
		this.proj = new IceBlockAvalancheProjectile(p.world, p);
		if (eray.getEntity() != null) {
			this.proj.setPosition(eray.getEntity().posX, eray.getEntity().posY + 15, eray.getEntity().posZ);
		} else {
			this.proj.setPosition(ray.getPos().getX(), ray.getPos().getY() + 15, ray.getPos().getZ());
		}
		this.proj.setMotion(0, 0, 0);
		p.world.addEntity(this.proj);
		return true;
	}

	private void duringChargingEvent(PlayerEntity p, int ticks) {
		this.dmg = ticks;

		if (p != null && this.proj != null) {
			PARTICLES.spawn(p.world, this.proj.posX, this.proj.posY, this.proj.posZ, 0, 0, 0);
		}
	}

	private boolean onEndChargingEvent(PlayerEntity p) {

		if (this.proj == null)
			return false;
		this.proj.setFinalized(true);
		this.proj.setDamage((this.getMaxChargeTime() - this.dmg) / 20 * 2);
		this.proj.setMotion(0, -2, 0);
		return true;
	}

	private void removeDuplicate() {
		if (this.proj != null) {
			if (this.proj.isAddedToWorld()) {
				this.proj.remove();
			}
		}
	}
}
