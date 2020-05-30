package xyz.pixelatedw.mineminenomi.abilities.ushigiraffe;

import java.util.UUID;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
import xyz.pixelatedw.mineminenomi.entities.zoan.GiraffeWalkZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;

public class GiraffeWalkPointAbility extends ZoanAbility
{
	public static final GiraffeWalkPointAbility INSTANCE = new GiraffeWalkPointAbility();
	private static final AttributeModifier SPEED_MODIFIER = new AttributeModifier(UUID.fromString("d171ef28-e77a-418d-927b-ca9a09417189"), "Walk Point Speed Multiplier", 1.5f, AttributeModifier.Operation.MULTIPLY_BASE).setSaved(false);
	private static final AttributeModifier JUMP_BOOST = new AttributeModifier(UUID.fromString("13b3d607-ed90-4459-832c-01e616a5ef16"), "Walk Point Jump Multiplier", 3, AttributeModifier.Operation.ADDITION).setSaved(false);
	private static final AttributeModifier ARMOR_MODIFIER = new AttributeModifier(UUID.fromString("0847f786-0a5a-4e83-9ea6-f924c259a798"), "Heavy Point Multiplier", 2, AttributeModifier.Operation.ADDITION).setSaved(false);
	private static final AttributeModifier STRENGTH_MODIFIER = new AttributeModifier(UUID.fromString("4b03a4b4-1eb5-464a-8312-0f9079044462"), "Heavy Point Multiplier", 1, AttributeModifier.Operation.ADDITION).setSaved(false);
	private static final AttributeModifier ATTACK_SPEED_MODIFIER = new AttributeModifier(UUID.fromString("1d78a133-8a0e-4b8f-8790-1360007d4741"), "Heavy Point Multiplier", 1f, AttributeModifier.Operation.ADDITION).setSaved(false);
	private static final AttributeModifier STEP_HEIGHT = new AttributeModifier(UUID.fromString("1d68a133-8a0e-4b8f-8790-1360007d4741"), "Heavy Point Multiplier", 1, AttributeModifier.Operation.ADDITION).setSaved(false);

	public GiraffeWalkPointAbility()
	{
		super("Giraffe Walk Point", AbilityCategory.DEVIL_FRUIT, GiraffeWalkZoanInfo.FORM);
		this.setDescription("Allows the user to transforms into a giraffe, which focuses on speed and high jumps, Allows the user to use 'Bigan'");
		this.addZoanModifier(SharedMonsterAttributes.MOVEMENT_SPEED, SPEED_MODIFIER);
		this.addZoanModifier(SharedMonsterAttributes.ARMOR, ARMOR_MODIFIER);
		this.addZoanModifier(SharedMonsterAttributes.ATTACK_DAMAGE, STRENGTH_MODIFIER);
		this.addZoanModifier(SharedMonsterAttributes.ATTACK_SPEED, ATTACK_SPEED_MODIFIER);
		this.addZoanModifier(ModAttributes.JUMP_HEIGHT, JUMP_BOOST);
		this.addZoanModifier(ModAttributes.STEP_HEIGHT, STEP_HEIGHT);
	}
}
