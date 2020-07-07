package xyz.pixelatedw.mineminenomi.abilities.mera;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.mera.DaiEnkaiEnteiProjectile;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.mera.DaiEnkai2ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.mera.DaiEnkaiParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.ChargeableAbility;

public class DaiEnkaiEnteiAbility extends ChargeableAbility
{
	public static final Ability INSTANCE = new DaiEnkaiEnteiAbility();
	
	private static final ParticleEffect PARTICLES_1 = new DaiEnkaiParticleEffect();
	private static final ParticleEffect PARTICLES_2 = new DaiEnkai2ParticleEffect();

	public DaiEnkaiEnteiAbility()
	{
		super("Dai Enkai: Entei", AbilityCategory.DEVIL_FRUIT);
		this.setDescription("Amasses the user's flames into a gigantic fireball that the user hurls at the opponent.");
		this.setMaxCooldown(20);
		this.setMaxChargeTime(2);
		
		this.duringChargingEvent = this::duringChargingEvent;
		this.onEndChargingEvent = this::onEndChargingEvent;
	}

	private void duringChargingEvent(PlayerEntity player, int chargeTime)
	{
		if(chargeTime % 2 == 0)
			PARTICLES_2.spawn(player.world, player.posX, player.posY, player.posZ, 0, 0, 0);
	}
	
	private boolean onEndChargingEvent(PlayerEntity player)
	{
		DaiEnkaiEnteiProjectile proj = new DaiEnkaiEnteiProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);	
		
		PARTICLES_1.spawn(player.world, player.posX, player.posY, player.posZ, 0, 0, 0);
		return true;
	}
}
