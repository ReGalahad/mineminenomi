package xyz.pixelatedw.mineminenomi.abilities.supa;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;

import java.util.UUID;

public class SpiderAbility extends ContinuousAbility
{
	public static final SpiderAbility INSTANCE = new SpiderAbility();
	private static final AttributeModifier JUMP_MULTIPLIER = new AttributeModifier(UUID.fromString("efa11cbd-57e5-478f-b15c-6295eb1b345e"), "Jump Multiplier",  -50, AttributeModifier.Operation.ADDITION).setSaved(false);

	public SpiderAbility()
	{
		super("Spider", AbilityCategory.DEVIL_FRUIT);
		this.setThreshold(15);
		this.setDescription("Hardens the user's body to protect themselves, but they're unable to move.");

		this.onStartContinuityEvent = this::startContinuity;
		this.duringContinuityEvent = this::duringContinuity;
		this.onEndContinuityEvent = this::onEndContinuityEvent;
	}

	private boolean startContinuity(PlayerEntity player) {
		player.getAttribute(ModAttributes.JUMP_HEIGHT).applyModifier(JUMP_MULTIPLIER);
		return true;
	}

	private void duringContinuity(PlayerEntity player, int passiveTimer)
	{
		player.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 20, 2, false, false));
		player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 20, 100, false, false));
		player.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 20, 100, false, false));
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