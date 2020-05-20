package xyz.pixelatedw.mineminenomi.abilities.ushigiraffe;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
import xyz.pixelatedw.mineminenomi.entities.zoan.GiraffeWalkZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;

import java.util.UUID;

import static net.minecraft.entity.SharedMonsterAttributes.MOVEMENT_SPEED;

public class GiraffeWalkPointAbility extends ZoanAbility
{
	public static final GiraffeWalkPointAbility INSTANCE = new GiraffeWalkPointAbility();
	private static final AttributeModifier SPEED_MODIFIER = new AttributeModifier(UUID.fromString("d171ef28-e77a-418d-927b-ca9a09417189"), "Walk Point Speed Multiplier", 1.05, AttributeModifier.Operation.MULTIPLY_BASE);
	private static final AttributeModifier JUMP_MODIFIER = new AttributeModifier(UUID.fromString("13b3d607-ed90-4459-832c-01e616a5ef16"), "Walk Point Jump Multiplier", 3, AttributeModifier.Operation.ADDITION);

	public GiraffeWalkPointAbility()
	{
		super("Giraffe Walk Point", AbilityCategory.DEVIL_FRUIT, GiraffeWalkZoanInfo.FORM);
		this.setDescription("Allows the user to transforms into a giraffe, which focuses on speed and high jumps, Allows the user to use 'Bigan'");
	}

	@Override
	protected boolean onStartContinuityEvent(PlayerEntity player) {
		player.getAttribute(MOVEMENT_SPEED).applyModifier(SPEED_MODIFIER);
		player.getAttribute(ModAttributes.JUMP_HEIGHT).applyModifier(JUMP_MODIFIER);
		return super.onStartContinuityEvent(player);
	}

	@Override
	protected boolean onEndContinuityEvent(PlayerEntity player) {
		player.getAttribute(MOVEMENT_SPEED).removeModifier(SPEED_MODIFIER);
		player.getAttribute(ModAttributes.JUMP_HEIGHT).removeModifier(JUMP_MODIFIER);
		return super.onEndContinuityEvent(player);
	}


}
