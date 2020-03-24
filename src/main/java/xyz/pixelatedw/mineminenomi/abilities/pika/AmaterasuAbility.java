package xyz.pixelatedw.mineminenomi.abilities.pika;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.pika.AmaterasuProjectile;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.pika.AmaterasuParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.ChargeableAbility;

public class AmaterasuAbility extends ChargeableAbility
{
	public static final Ability INSTANCE = new AmaterasuAbility();
	
	private static final ParticleEffect PARTICLES = new AmaterasuParticleEffect();

	public AmaterasuAbility()
	{
		super("Amaterasu", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(15);
		this.setMaxChargeTime(2);
		this.setDescription("Creates an immense beam of light, which causes massive damage.");

		this.duringChargingEvent = this::duringChargingEvent;
		this.onEndChargingEvent = this::onEndChargingEvent;
	}

	private void duringChargingEvent(PlayerEntity player, int timer)
	{
		PARTICLES.spawn(player.world, player.posX, player.posY, player.posZ, 0, 0, 0);
	}
	
	private boolean onEndChargingEvent(PlayerEntity player)
	{
		AmaterasuProjectile proj = new AmaterasuProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 4f, 1);	
		
		return true;
	}
}