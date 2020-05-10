package xyz.pixelatedw.mineminenomi.abilities.rokushiki;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;

import java.util.UUID;

public class TekkaiAbility extends ContinuousAbility
{
	public static final Ability INSTANCE = new TekkaiAbility();
	private static final AttributeModifier JUMP_MULTIPLIER = new AttributeModifier(UUID.fromString("efa28cbd-57e5-478f-b15c-6295eb1b375e"), "Jump Multiplier",  -50, AttributeModifier.Operation.ADDITION).setSaved(false);

	public TekkaiAbility()
	{
		super("Tekkai", AbilityCategory.RACIAL);
		this.setThreshold(10);
		this.setDescription("Hardens the user's body to protect themselves, but they're unable to move.");

		this.onStartContinuityEvent = this::startContinuity;
		this.duringContinuityEvent = this::duringContinuity;
		this.onEndContinuityEvent = this::onEndContinuityEvent;
	}



	private void duringContinuity(PlayerEntity player, int passiveTimer)
	{
		player.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 20, 6, false, false));
		player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 20, 100, false, false));
		player.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 20, 5, false, false));
	}


	private boolean startContinuity(PlayerEntity player) {
		player.getAttribute(ModAttributes.JUMP_HEIGHT).applyModifier(JUMP_MULTIPLIER);
		return true;
	}

	private boolean onEndContinuityEvent(PlayerEntity player)
	{
		if(player.getAttribute(ModAttributes.JUMP_HEIGHT).hasModifier(JUMP_MULTIPLIER))
			player.getAttribute(ModAttributes.JUMP_HEIGHT).removeModifier(JUMP_MULTIPLIER);

		int cooldown = (int) Math.round(this.continueTime / 15.0);
		this.setMaxCooldown(cooldown);
		player.removePotionEffect(Effects.RESISTANCE);
		player.removePotionEffect(Effects.SLOWNESS);
		player.removePotionEffect(Effects.MINING_FATIGUE);
		return true;
	}
}