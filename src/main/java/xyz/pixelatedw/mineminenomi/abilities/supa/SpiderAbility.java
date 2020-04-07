package xyz.pixelatedw.mineminenomi.abilities.supa;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
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

		this.duringContinuityEvent = this::duringContinuity;
		this.onEndContinuityEvent = this::onEndContinuityEvent;
	}

	private void duringContinuity(PlayerEntity player, int passiveTimer)
	{
		player.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 20, 2, false, false));
		player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 20, 100, false, false));
		player.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 20, 100, false, false));
	}

	private boolean onEndContinuityEvent(PlayerEntity player)
	{
		int cooldown = (int) Math.round(this.continueTime / 15.0);
		this.setMaxCooldown(cooldown);
		player.removePotionEffect(Effects.RESISTANCE);
		player.removePotionEffect(Effects.SLOWNESS);
		player.removePotionEffect(Effects.MINING_FATIGUE);
		return true;
	}
}