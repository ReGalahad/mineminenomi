package xyz.pixelatedw.mineminenomi.abilities.zou;

import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
import xyz.pixelatedw.mineminenomi.entities.zoan.ZouGuardZoanInfo;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;

public class ZouGuardPointAbility extends ZoanAbility
{
	public static final ZouGuardPointAbility INSTANCE = new ZouGuardPointAbility();

	public ZouGuardPointAbility()
	{
		super("Zou Guard Point", AbilityCategory.DEVIL_FRUIT, ZouGuardZoanInfo.FORM);
		this.setDescription("Allows the user to transforms into an elephant hybrid, which focuses on strength, Allows the user to use 'Trunk Shot' and 'Ivory Stomp'");
	}
}
