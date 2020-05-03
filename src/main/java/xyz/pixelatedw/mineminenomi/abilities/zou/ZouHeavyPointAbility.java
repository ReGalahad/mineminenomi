package xyz.pixelatedw.mineminenomi.abilities.zou;

import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
import xyz.pixelatedw.mineminenomi.entities.zoan.ZouHeavyZoanInfo;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;

public class ZouHeavyPointAbility extends ZoanAbility
{
	public static final ZouHeavyPointAbility INSTANCE = new ZouHeavyPointAbility();

	public ZouHeavyPointAbility()
	{
		super("Zou Heavy Point", AbilityCategory.DEVIL_FRUIT, ZouHeavyZoanInfo.FORM);
		this.setDescription("Allows the user to transforms into an elephant hybrid, which focuses on strength, Allows the user to use 'Trunk Shot' and 'Ivory Stomp'");
	}

}
