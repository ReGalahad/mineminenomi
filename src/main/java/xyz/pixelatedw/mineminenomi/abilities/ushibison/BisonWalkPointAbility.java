package xyz.pixelatedw.mineminenomi.abilities.ushibison;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
import xyz.pixelatedw.mineminenomi.entities.zoan.BisonWalkZoanInfo;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;

import java.util.UUID;

import static net.minecraft.entity.SharedMonsterAttributes.MOVEMENT_SPEED;

public class BisonWalkPointAbility extends ZoanAbility
{
	public static final BisonWalkPointAbility INSTANCE = new BisonWalkPointAbility();
	private static final AttributeModifier SPEED_MODIFIER = new AttributeModifier(UUID.fromString("d141ef28-e77a-418d-927b-ca9a09417189"), "Walk Point Multiplier", 1.2, AttributeModifier.Operation.MULTIPLY_BASE);

	public BisonWalkPointAbility()
	{
		super("Bison Walk Point", AbilityCategory.DEVIL_FRUIT, BisonWalkZoanInfo.FORM);
		this.setDescription("Allows the user to transforms into a bison, which focuses on speed, Allows the user to use 'Fiddle Banff'");
	}

	@Override
	protected boolean onStartContinuityEvent(PlayerEntity player) {
		player.getAttribute(MOVEMENT_SPEED).applyModifier(SPEED_MODIFIER);

		return super.onStartContinuityEvent(player);
	}

	@Override
	protected boolean onEndContinuityEvent(PlayerEntity player) {
		player.getAttribute(MOVEMENT_SPEED).removeModifier(SPEED_MODIFIER);
		return super.onEndContinuityEvent(player);
	}


}
