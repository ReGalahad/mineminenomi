package xyz.pixelatedw.mineminenomi.abilities.pika;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.abilities.ChargeableAbility;
import xyz.pixelatedw.mineminenomi.api.data.ability.AbilityDataCapability.Category;
import xyz.pixelatedw.mineminenomi.entities.projectiles.pika.AmaterasuProjectile;

public class AmaterasuAbility extends ChargeableAbility
{
	public static final Ability INSTANCE = new AmaterasuAbility();
	
	public AmaterasuAbility()
	{
		super("Amaterasu", Category.DEVIL_FRUIT);
		this.setMaxCooldown(15);
		this.setMaxChargeTime(2);
		this.setDescription("Creates an immense beam of light, which causes massive damage.");

		this.onEndChargingEvent = this::onEndChargingEvent;
	}

	private boolean onEndChargingEvent(PlayerEntity player)
	{
		AmaterasuProjectile proj = new AmaterasuProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);	
		
		return true;
	}
}