package xyz.pixelatedw.mineminenomi.abilities.ushigiraffe;

import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
import xyz.pixelatedw.mineminenomi.entities.zoan.GiraffeWalkZoanInfo;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;

public class GiraffeWalkPointAbility extends ZoanAbility
{
	public static final GiraffeWalkPointAbility INSTANCE = new GiraffeWalkPointAbility();

	public GiraffeWalkPointAbility()
	{
		super("Giraffe Walk Point", AbilityCategory.DEVIL_FRUIT, GiraffeWalkZoanInfo.FORM);
		this.setDescription("Allows the user to transforms into a giraffe, which focuses on speed and high jumps, Allows the user to use 'Bigan'");
	}

}
