package xyz.pixelatedw.mineminenomi.abilities.ushigiraffe;

import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
import xyz.pixelatedw.mineminenomi.entities.zoan.GiraffeHeavyZoanInfo;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;

public class GiraffeHeavyPointAbility extends ZoanAbility
{
	public static final GiraffeHeavyPointAbility INSTANCE = new GiraffeHeavyPointAbility();

	public GiraffeHeavyPointAbility()
	{
		super("Giraffe Heavy Point", AbilityCategory.DEVIL_FRUIT, GiraffeHeavyZoanInfo.FORM);
		this.setDescription("Allows the user to transforms into a half-giraffe hybrid, which focuses on strength, Allows the user to use 'Bigan'");
	}

}
