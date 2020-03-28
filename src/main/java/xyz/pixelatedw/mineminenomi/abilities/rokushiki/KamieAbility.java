package xyz.pixelatedw.mineminenomi.abilities.rokushiki;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;

public class KamieAbility extends ContinuousAbility
{
	public static final Ability INSTANCE = new KamieAbility();
	
	public KamieAbility()
	{
		super("Kami-E", AbilityCategory.RACIAL);
		this.setThreshold(10);
		this.setDescription("Makes the user's body flexible in order to avoid attacks.");

		this.onEndContinuityEvent = this::onEndContinuityEvent;
	}

	private boolean onEndContinuityEvent(PlayerEntity player)
	{
		int cooldown = (int) Math.round(this.continueTime / 10.0);
		this.setMaxCooldown(cooldown);
		return true;
	}
}