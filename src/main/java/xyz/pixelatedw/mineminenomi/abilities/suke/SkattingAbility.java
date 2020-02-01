package xyz.pixelatedw.mineminenomi.abilities.suke;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.abilities.ContinuousAbility;
import xyz.pixelatedw.mineminenomi.api.data.ability.AbilityDataCapability.Category;

public class SkattingAbility extends ContinuousAbility
{
	public static final Ability INSTANCE = new SkattingAbility();

	public SkattingAbility()
	{
		super("Skatting", Category.DEVIL_FRUIT);
		this.setDescription("Turns the user's entire body invisible.");
		
		this.duringContinuity = this::duringContinuity;
		this.onEndContinuityEvent = this::onEndContinuityEvent;
	}

	private void duringContinuity(PlayerEntity player, int tick)
	{
		player.addPotionEffect(new EffectInstance(Effects.INVISIBILITY, 200, 1, false, false));
	}
	
	private boolean onEndContinuityEvent(PlayerEntity player)
	{
		player.removePotionEffect(Effects.INVISIBILITY);
		return true;
	}
}
