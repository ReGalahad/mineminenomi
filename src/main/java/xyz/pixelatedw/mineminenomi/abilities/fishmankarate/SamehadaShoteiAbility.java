package xyz.pixelatedw.mineminenomi.abilities.fishmankarate;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;

public class SamehadaShoteiAbility extends ContinuousAbility
{
	public static final Ability INSTANCE = new SamehadaShoteiAbility();
	
	public SamehadaShoteiAbility()
	{
		super("Samehada Shotei", AbilityCategory.RACIAL);
		this.setThreshold(10);
		this.setDescription("The user concentrates their power to their palms, protecting themselves from attacks.");

		this.duringContinuity = this::duringContinuity;
		this.onEndContinuityEvent = this::onEndContinuityEvent;
	}

	private void duringContinuity(PlayerEntity player, int passiveTimer)
	{
		player.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 20, 6, false, false));
		player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 20, 100, false, false));
		player.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 20, 5, false, false));
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