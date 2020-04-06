package xyz.pixelatedw.mineminenomi.abilities.rokushiki;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;

public class SoruAbility extends ContinuousAbility
{
	public static final Ability INSTANCE = new SoruAbility();
	
	public SoruAbility()
	{
		super("Soru", AbilityCategory.RACIAL);
		this.setThreshold(20);
		this.setDescription("Allows the user to move at an extremely high speed.");

		this.duringContinuityEvent = this::duringContinuity;
		this.onEndContinuityEvent = this::onEndContinuityEvent;
	}

	private void duringContinuity(PlayerEntity player, int passiveTimer)
	{
		player.addPotionEffect(new EffectInstance(Effects.SPEED, 20, 6, false, false));
	}

	private boolean onEndContinuityEvent(PlayerEntity player)
	{
		int cooldown = (int) Math.round(this.continueTime / 40.0);
		this.setMaxCooldown(cooldown);
		player.removePotionEffect(Effects.SPEED);
		return true;
	}
}
