package xyz.pixelatedw.mineminenomi.api.data.ability;

import java.util.List;

import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.data.ability.AbilityDataCapability.Category;

public interface IAbilityData
{

	boolean addAbility(Ability abl);
	void removeAbility(Ability abl);
	Ability getAbility(Ability abl);
	Ability getAbility(String ablName);
	int getAbilityPosition(Ability abl);
	int getAbilityPosition(String ablName);
	List<Ability> getAbilities(Category category);
	void clearAbilities(Category category);
	void clearAbilityFromList(Category category, List<Ability> list); 
	int countAbilities(Category category);

	void setAbilityInHotbar(int slot, Ability abl);
	void removeAbilityFromHotbar(int slot);
	boolean hasAbilityInHotbar(Ability abl);
	Ability getAbilityInSlot(int slot);
	Ability[] getHotbarAbilities();
	void clearHotbar();
	
	Ability getPreviouslyUsedAbility();
	void setPreviouslyUsedAbility(Ability abl);
	
	boolean isInCombatMode();
	void setCombatMode(boolean value);
}
