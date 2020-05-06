package xyz.pixelatedw.mineminenomi.abilities.toriphoenix;

import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
import xyz.pixelatedw.mineminenomi.entities.zoan.PhoenixAssaultZoanInfo;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;

public class PhoenixAssaultPointAbility extends ZoanAbility
{
	public static final PhoenixAssaultPointAbility INSTANCE = new PhoenixAssaultPointAbility();

	public PhoenixAssaultPointAbility()
	{
		super("Phoenix Assault Point", AbilityCategory.DEVIL_FRUIT, PhoenixAssaultZoanInfo.FORM);
		this.setDescription("Allows the user to transforms into a half-phoenix hybrid, which focuses on speed and healing, Allows the user to use 'Phoenix Goen' and 'Flame of Restoration'");
	}

}
