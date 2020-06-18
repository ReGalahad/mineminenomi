package xyz.pixelatedw.mineminenomi.abilities.supa;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;

public class SpiderAbility extends ContinuousAbility
{
	public static final SpiderAbility INSTANCE = new SpiderAbility();

	public SpiderAbility()
	{
		super("Spider", AbilityCategory.DEVIL_FRUIT);
		this.setThreshold(15);
		this.setDescription("Hardens the user's body to protect themselves, but they're unable to move.");

		this.onStartContinuityEvent = this::onStartContinuityEvent;
		this.duringContinuityEvent = this::duringContinuity;
		this.onEndContinuityEvent = this::onEndContinuityEvent;
	}

	private boolean onStartContinuityEvent(PlayerEntity player) {
		return true;
	}

	private void duringContinuity(PlayerEntity player, int passiveTimer)
	{
		player.addPotionEffect(new EffectInstance(ModEffects.GUARDING, 10, 3, false, false));
	}

	private boolean onEndContinuityEvent(PlayerEntity player)
	{
		int cooldown = (int) Math.round(this.continueTime / 15.0);
		this.setMaxCooldown(cooldown);
		return true;
	}
}