package xyz.pixelatedw.mineminenomi.abilities.toriphoenix;

import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
import xyz.pixelatedw.mineminenomi.entities.zoan.PhoenixFlyZoanInfo;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;

public class PhoenixFlyPointAbility extends ZoanAbility
{
	public static final PhoenixFlyPointAbility INSTANCE = new PhoenixFlyPointAbility();

	public PhoenixFlyPointAbility()
	{
		super("Phoenix Fly Point", AbilityCategory.DEVIL_FRUIT, PhoenixFlyZoanInfo.FORM);
		this.setDescription("Allows the user to transforms into a phoenix, which focuses on speed and healing, Allows the user to use 'Phoenix Goen', 'Tensei no Soen' and 'Blue Flames of Resurrection'");
	}
}
