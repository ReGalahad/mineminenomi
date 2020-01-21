package xyz.pixelatedw.mineminenomi.abilities.bane;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.abilities.ChargeableAbility;
import xyz.pixelatedw.mineminenomi.api.data.ability.AbilityDataCapability.Category;

public class SpringHopperAbility extends ChargeableAbility
{
	public static final Ability INSTANCE = new SpringHopperAbility();
	
	public SpringHopperAbility()
	{
		super("Spring Hopper", Category.DEVIL_FRUIT);
		this.setMaxCooldown(0.6);
		this.setMaxChargeTime(1);
		this.setDescription("Turning the user's legs into springs, which launches them into the air.");

		this.onUseEvent = this::onUseEvent;
	}
	
	private void onUseEvent(PlayerEntity player, Ability ability)
	{
		double[] speed = WyHelper.propulsion(player, 2.5, 2.5);
		player.setMotion(speed[0], 5.0, speed[1]);	
	}
}