package xyz.pixelatedw.mineminenomi.api.data.ability;

import java.util.List;

import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability.Category;

public interface IAbilityData
{

	boolean addAbility(Ability abl);
	void removeAbility(Ability ablTemplate);
	boolean hasAbility(Ability ablTemplate);
	List<Ability> getAbilities(Category category);
	void clearAbilities(Category category);
	void clearAbilityFromList(Category category, Ability[] list); 
	int countAbilities(Category category);

	Ability getPreviouslyUsedAbility();
	void setPreviouslyUsedAbility(Ability abl);
	
	boolean isInCombatMode();
	void setCombatMode(boolean value);
	/*
	public default boolean isPassiveActive(AbilityAttribute attr)
	{
		if(attr == null)
			return false;
		
		Ability ability = this.getHotbarAbilityFromName(attr.getAttributeName());
		if (ability != null && ability.isPassiveActive())
			return true;

		return false;
	}
	*/
}
