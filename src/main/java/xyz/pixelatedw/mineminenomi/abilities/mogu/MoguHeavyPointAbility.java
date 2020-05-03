package xyz.pixelatedw.mineminenomi.abilities.mogu;

import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
import xyz.pixelatedw.mineminenomi.entities.zoan.MoguHeavyZoanInfo;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;

public class MoguHeavyPointAbility extends ZoanAbility
{
	public static final MoguHeavyPointAbility INSTANCE = new MoguHeavyPointAbility();

	public MoguHeavyPointAbility()
	{
		super("Mogu Heavy Point", AbilityCategory.DEVIL_FRUIT, MoguHeavyZoanInfo.FORM);
		this.setDescription("Allows the user to transforms into a mole, which focuses on strength and digging speed, Allows the user to use 'Mogura Banana' and 'Mogura Tonpo'");
	}

}
