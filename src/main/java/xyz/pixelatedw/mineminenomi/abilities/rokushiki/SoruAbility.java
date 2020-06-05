package xyz.pixelatedw.mineminenomi.abilities.rokushiki;

import java.util.UUID;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;

public class SoruAbility extends ContinuousAbility
{
	public static final Ability INSTANCE = new SoruAbility();
	
	private static final AttributeModifier STEP_HEIGHT = new AttributeModifier(UUID.fromString("1d68a133-8a0e-4b8f-8790-1360007d4741"), "Step Height Multiplier", 1, AttributeModifier.Operation.ADDITION).setSaved(false);

	public SoruAbility()
	{
		super("Soru", AbilityCategory.RACIAL);
		this.setThreshold(20);
		this.setDescription("Allows the user to move at an extremely high speed.");

		this.onStartContinuityEvent = this::onStartContinuityEvent;
		this.duringContinuityEvent = this::duringContinuity;
		this.onEndContinuityEvent = this::onEndContinuityEvent;
	}

	private boolean onStartContinuityEvent(PlayerEntity player)
	{
		player.getAttribute(ModAttributes.STEP_HEIGHT).removeModifier(STEP_HEIGHT);
		player.getAttribute(ModAttributes.STEP_HEIGHT).applyModifier(STEP_HEIGHT);
		return true;
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
		player.getAttribute(ModAttributes.STEP_HEIGHT).removeModifier(STEP_HEIGHT);
		return true;
	}
}
