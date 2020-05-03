package xyz.pixelatedw.mineminenomi.abilities.ushibison;

import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
import xyz.pixelatedw.mineminenomi.entities.zoan.BisonHeavyZoanInfo;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;

public class BisonHeavyPointAbility extends ZoanAbility
{
	public static final BisonHeavyPointAbility INSTANCE = new BisonHeavyPointAbility();

	public BisonHeavyPointAbility()
	{
		super("Bison Heavy Point", AbilityCategory.DEVIL_FRUIT, BisonHeavyZoanInfo.FORM);
		this.setDescription("Allows the user to transforms into a half-bison hybrid, which focuses on strength, Allows the user to use 'Kokutei Cross' and 'Fiddle Banff'");
	}

}
