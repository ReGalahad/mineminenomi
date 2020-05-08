package xyz.pixelatedw.mineminenomi.abilities.mero;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.EntityRayTraceResult;
import xyz.pixelatedw.mineminenomi.entities.projectiles.mero.SlaveArrowProjectile;
import xyz.pixelatedw.mineminenomi.particles.effects.mero.SlaveArrowParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.ChargeableAbility;

public class SlaveArrowAbility extends ChargeableAbility
{
	public static final SlaveArrowAbility INSTANCE = new SlaveArrowAbility();

	private static final SlaveArrowParticleEffect PARTICLES = new SlaveArrowParticleEffect();
	private int limit = 0;

	public SlaveArrowAbility()
	{
		super("Slave Arrow", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(13);
		this.setMaxChargeTime(7);
		this.setDescription("Creates a big heart from which the user shoots multiple love arrows.");

		this.duringChargingEvent = this::duringChargingEvent;
		this.onEndChargingEvent = this::onEndChargingEvent;
		this.duringCooldownEvent = this::duringCooldownEvent;
	}

	private void duringCooldownEvent(PlayerEntity player, int cooldown)
	{
		int projectileSpace = 1;
		if(this.limit > 0 && (this.cooldown - 10) % 2 == 0)
		{
			SlaveArrowProjectile proj = new SlaveArrowProjectile(player.world, player);
			proj.setLocationAndAngles(
				player.posX + WyHelper.randomWithRange(-projectileSpace, projectileSpace) + WyHelper.randomDouble(), 
				(player.posY + 0.3) + WyHelper.randomWithRange(0, projectileSpace) + WyHelper.randomDouble(), 
				player.posZ + WyHelper.randomWithRange(-projectileSpace, projectileSpace) + WyHelper.randomDouble(),
				0, 0);
			player.world.addEntity(proj);
			proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 3f, 4f);
			this.limit--;
		}
	}
	
	private void duringChargingEvent(PlayerEntity player, int chargeTime)
	{
		if(chargeTime > this.getMaxChargeTime() / 2)
			PARTICLES.setSize(PARTICLES.getSize() + 0.20F);
		else if (chargeTime <= this.getMaxChargeTime() / 2)
			PARTICLES.setSize(PARTICLES.getSize() - 0.05F);
		
		EntityRayTraceResult trace = WyHelper.rayTraceEntities(player, 0.8);

		if (chargeTime % 2 == 0)
			PARTICLES.spawn(player.world, trace.getHitVec().getX(), player.posY, trace.getHitVec().getZ(), 0, 0, 0);
		player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 10, 2000));
		player.setMotion(0, 0, 0);
		player.velocityChanged = true;
	}

	private boolean onEndChargingEvent(PlayerEntity player)
	{
		this.limit = 20;
		
		PARTICLES.setSize(5);

		return true;
	}
}