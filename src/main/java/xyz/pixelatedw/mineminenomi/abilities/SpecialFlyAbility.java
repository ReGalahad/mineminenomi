package xyz.pixelatedw.mineminenomi.abilities;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.PassiveAbility;

public class SpecialFlyAbility extends PassiveAbility
{
	public static final SpecialFlyAbility INSTANCE = new SpecialFlyAbility();

	public SpecialFlyAbility()
	{
		super("Special Fly", AbilityCategory.DEVIL_FRUIT);
		
		this.duringPassiveEvent = this::duringPassiveEvent;
	}

	private void duringPassiveEvent(PlayerEntity player)
	{
		if(CommonConfig.instance.isSpecialFlyingEnabled())
			player.abilities.allowFlying = true;
	}
}
