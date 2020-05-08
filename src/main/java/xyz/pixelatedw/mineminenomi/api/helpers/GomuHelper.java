package xyz.pixelatedw.mineminenomi.api.helpers;

import xyz.pixelatedw.mineminenomi.abilities.gomu.GearFourthAbility;
import xyz.pixelatedw.mineminenomi.abilities.gomu.GearSecondAbility;
import xyz.pixelatedw.mineminenomi.abilities.gomu.GearThirdAbility;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;

public class GomuHelper
{

	public static boolean hasGearSecondActive(IAbilityData props)
	{
		Ability ability = props.getEquippedAbility(GearSecondAbility.INSTANCE);
		boolean isActive = ability != null && ability.isContinuous();
		return isActive;
	}
	
	public static boolean hasGearThirdActive(IAbilityData props)
	{
		Ability ability = props.getEquippedAbility(GearThirdAbility.INSTANCE);
		boolean isActive = ability != null && ability.isContinuous();
		return isActive;
	}
	
	public static boolean hasGearFourthActive(IAbilityData props)
	{
		Ability ability = props.getEquippedAbility(GearFourthAbility.INSTANCE);
		boolean isActive = ability != null && ability.isContinuous();
		return isActive;
	}
}
