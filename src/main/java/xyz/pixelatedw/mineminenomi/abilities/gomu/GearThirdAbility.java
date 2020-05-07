package xyz.pixelatedw.mineminenomi.abilities.gomu;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;

public class GearThirdAbility extends ContinuousAbility
{
	public static final GearThirdAbility INSTANCE = new GearThirdAbility();
	
	public GearThirdAbility()
	{
		super("Gear Third", AbilityCategory.DEVIL_FRUIT);
		this.setThreshold(30);
		this.setDescription("By blowing air and inflating their body, the user's attacks get bigger and gain incredible strength.");

		this.onEndContinuityEvent = this::onEndContinuityEvent;
	}

	private boolean onEndContinuityEvent(PlayerEntity player)
	{
		int cooldown = (int) Math.round(this.continueTime / 20.0);
		this.setMaxCooldown(cooldown);
		
		return true;
	}
}
