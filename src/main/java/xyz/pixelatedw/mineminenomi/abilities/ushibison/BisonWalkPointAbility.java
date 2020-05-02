package xyz.pixelatedw.mineminenomi.abilities.ushibison;

import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
import xyz.pixelatedw.mineminenomi.entities.zoan.BisonWalkZoanInfo;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.IParallelContinuousAbility;

public class BisonWalkPointAbility extends ZoanAbility implements IParallelContinuousAbility
{
	public static final BisonWalkPointAbility INSTANCE = new BisonWalkPointAbility();

	public BisonWalkPointAbility()
	{
		super("Bison Walk Point", AbilityCategory.DEVIL_FRUIT, BisonWalkZoanInfo.FORM);
		this.setDescription("Allows the user to transforms into a bison, which focuses on speed, Allows the user to use 'Fiddle Banff'");
	}

}
