package xyz.pixelatedw.mineminenomi.abilities.gomu;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiHardeningAbility;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;

import java.util.UUID;

public class GearFourthAbility extends ContinuousAbility
{
	public static final GearFourthAbility INSTANCE = new GearFourthAbility();

	private static final AttributeModifier MODIFIER = new AttributeModifier(UUID.fromString("a44a9644-369a-4e18-88d9-323727d3d85b"), "Gear Fourth Modifier", 5, Operation.ADDITION).setSaved(false);

	public GearFourthAbility()
	{
		super("Gear Fourth", AbilityCategory.DEVIL_FRUIT);
		this.setThreshold(30);
		this.setDescription("The user inflates their muscle structure to tremendously increase the power of their attacks.");

		this.onStartContinuityEvent = this::onStartContinuityEvent;
		this.duringContinuityEvent = this::duringContinuity;
		this.onEndContinuityEvent = this::onEndContinuityEvent;
	}
	
	private boolean onStartContinuityEvent(PlayerEntity player)
	{
		IAbilityData props = AbilityDataCapability.get(player);
		return props.hasUnlockedAbility(BusoshokuHakiHardeningAbility.INSTANCE);
	}

	private void duringContinuity(PlayerEntity player, int passiveTimer)
	{
		IAttributeInstance attr = player.getAttribute(ModAttributes.JUMP_HEIGHT);
		if(!attr.hasModifier(MODIFIER))
			attr.applyModifier(MODIFIER);
	}

	private boolean onEndContinuityEvent(PlayerEntity player)
	{
		int cooldown = (int) Math.round(this.continueTime / 15.0);
		this.setMaxCooldown(cooldown);
		
		IAttributeInstance attr = player.getAttribute(ModAttributes.JUMP_HEIGHT);
		if(attr.hasModifier(MODIFIER))
			attr.removeModifier(MODIFIER);
		
		return true;
	}
}
