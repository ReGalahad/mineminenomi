package xyz.pixelatedw.mineminenomi.abilities.suke;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;

public class SkattingAbility extends ContinuousAbility
{
	public static final Ability INSTANCE = new SkattingAbility();

	public SkattingAbility()
	{
		super("Skatting", AbilityCategory.DEVIL_FRUIT);
		this.setDescription("Turns the user's entire body invisible.");

		this.duringContinuityEvent = this::duringContinuity;
		this.onEndContinuityEvent = this::onEndContinuityEvent;
	}

	private void duringContinuity(PlayerEntity player, int tick)
	{
		player.addPotionEffect(new EffectInstance(Effects.INVISIBILITY, 60, 1, false, false));
	}

	private boolean onEndContinuityEvent(PlayerEntity player)
	{
		player.removePotionEffect(Effects.INVISIBILITY);
		return true;
	}
}
