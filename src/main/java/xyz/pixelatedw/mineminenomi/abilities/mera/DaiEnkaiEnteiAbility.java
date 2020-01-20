package xyz.pixelatedw.mineminenomi.abilities.mera;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.abilities.ChargeableAbility;
import xyz.pixelatedw.mineminenomi.entities.projectiles.mera.DaiEnkaiEnteiProjectile;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.mera.ParticleEffectDaiEnkai;
import xyz.pixelatedw.mineminenomi.particles.effects.mera.ParticleEffectDaiEnkai2;

public class DaiEnkaiEnteiAbility extends ChargeableAbility
{
	public static final Ability INSTANCE = new DaiEnkaiEnteiAbility();
	
	private static final ParticleEffect DAI_ENKAI_ENTEI_1 = new ParticleEffectDaiEnkai();
	private static final ParticleEffect DAI_ENKAI_ENTEI_2 = new ParticleEffectDaiEnkai2();

	public DaiEnkaiEnteiAbility()
	{
		super("Dai Enkai: Entei", Category.DEVIL_FRUIT);
		this.setDescription("Amasses the user's flames into a gigantic fireball that the user hurls at the opponent.");
		this.setMaxCooldown(25);
		this.setMaxChargeTime(2);
		
		this.duringChargingEvent = this::duringChargingEvent;
		this.onEndChargingEvent = this::onEndChargingEvent;
	}

	private void duringChargingEvent(PlayerEntity player, int chargeTime)
	{
		if(chargeTime % 2 == 0)
			DAI_ENKAI_ENTEI_2.spawn(player.world, player.posX, player.posY, player.posZ, 0, 0, 0);
	}
	
	private void onEndChargingEvent(PlayerEntity player)
	{
		DaiEnkaiEnteiProjectile proj = new DaiEnkaiEnteiProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);	
		
		DAI_ENKAI_ENTEI_1.spawn(player.world, player.posX, player.posY, player.posZ, 0, 0, 0);
	}
}
