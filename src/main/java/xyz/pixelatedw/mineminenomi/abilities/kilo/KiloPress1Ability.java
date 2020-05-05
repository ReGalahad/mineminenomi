package xyz.pixelatedw.mineminenomi.abilities.kilo;

import java.util.UUID;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;

public class KiloPress1Ability extends ContinuousAbility
{
	public static final KiloPress1Ability INSTANCE = new KiloPress1Ability();
	
	private static final AttributeModifier KILO_PRESS = new AttributeModifier(UUID.fromString("692759d2-5d8d-4809-912d-86ad362f8f95"), "Kilo Press", 10.0, Operation.ADDITION);

	public KiloPress1Ability()
	{
		super("1 Kilo Press", AbilityCategory.DEVIL_FRUIT);
		this.setThreshold(60);
		
		this.onStartContinuityEvent = this::onStartContinuityEvent;
		this.duringContinuityEvent = this::duringContinuityEvent;
		this.onEndContinuityEvent = this::onEndContinuityEvent;
	}
	
	private boolean onStartContinuityEvent(PlayerEntity player)
	{
		player.getAttribute(ModAttributes.JUMP_HEIGHT).applyModifier(KILO_PRESS);
		
		return true;
	}
	
	private void duringContinuityEvent(PlayerEntity player, int time)
	{
		player.fallDistance = 0;
	}
	
	private boolean onEndContinuityEvent(PlayerEntity player)
	{
		player.getAttribute(ModAttributes.JUMP_HEIGHT).removeModifier(KILO_PRESS);
	
		int cooldown = (int) Math.round(this.continueTime / 30.0);
		this.setMaxCooldown(cooldown);
		
		return true;
	}
}
