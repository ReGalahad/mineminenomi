package xyz.pixelatedw.mineminenomi.abilities.mera;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.abilities.ChargeableAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.mera.DaiEnkaiEnteiProjectile;

public class DaiEnkaiEnteiAbility extends ChargeableAbility
{
	public static final Ability INSTANCE = new DaiEnkaiEnteiAbility();
	
	public DaiEnkaiEnteiAbility()
	{
		super("Dai Enkai: Entei", Category.DEVIL_FRUIT);
		this.setDescription("Amasses the user's flames into a gigantic fireball that the user hurls at the opponent.");
		this.setMaxCooldown(25);
		this.setMaxChargeTime(2);
		
		this.onEndChargingEvent = this::onEndChargingEvent;
	}
	
	private void onEndChargingEvent(PlayerEntity player)
	{
		DaiEnkaiEnteiProjectile proj = new DaiEnkaiEnteiProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);		
	}
}
