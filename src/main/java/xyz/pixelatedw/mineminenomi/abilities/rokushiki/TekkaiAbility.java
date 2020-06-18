package xyz.pixelatedw.mineminenomi.abilities.rokushiki;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;

public class TekkaiAbility extends ContinuousAbility
{
	public static final Ability INSTANCE = new TekkaiAbility();

	public TekkaiAbility()
	{
		super("Tekkai", AbilityCategory.RACIAL);
		this.setThreshold(10);
		this.setDescription("Hardens the user's body to protect themselves, but they're unable to move.");

		this.onStartContinuityEvent = this::onStartContinuityEvent;
		this.duringContinuityEvent = this::duringContinuity;
		this.onEndContinuityEvent = this::onEndContinuityEvent;
	}



	private void duringContinuity(PlayerEntity player, int passiveTimer)
	{
		player.addPotionEffect(new EffectInstance(ModEffects.GUARDING, 10, 2, false, false));
	}


	private boolean onStartContinuityEvent(PlayerEntity player) {
		return true;
	}

	private boolean onEndContinuityEvent(PlayerEntity player)
	{
		int cooldown = (int) Math.round(this.continueTime / 15.0);
		this.setMaxCooldown(cooldown);
		return true;
	}
}