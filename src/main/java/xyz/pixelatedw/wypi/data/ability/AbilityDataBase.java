package xyz.pixelatedw.wypi.data.ability;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class AbilityDataBase implements IAbilityData
{
	private List<Ability> unlockedAbilities = new ArrayList<Ability>();
	private Ability[] equippedAbilities = new Ability[APIConfig.MAX_SELECTED_ABILITIES];

	
	private Ability previouslyUsedAbility;

	/*
	 * Unlocked Abilities
	 */

	@Override
	public boolean addUnlockedAbility(Ability abl)
	{
		Ability ogAbl = this.getUnlockedAbility(abl);
		if (ogAbl == null)
		{
			this.unlockedAbilities.add(abl);
			return true;
		}
		return false;
	}

	@Override
	public boolean setUnlockedAbility(int slot, Ability abl)
	{
		Ability ogAbl = this.getUnlockedAbility(abl);
		if (ogAbl == null)
		{
			if(this.unlockedAbilities.size() > slot)
				this.unlockedAbilities.set(slot, abl);
			else
				this.unlockedAbilities.add(slot, abl);
			return true;
		}
		return false;
	}

	@Override
	public boolean removeUnlockedAbility(Ability abl)
	{
		Ability ogAbl = this.getUnlockedAbility(abl);
		if (ogAbl != null)
		{
			this.unlockedAbilities.remove(ogAbl);
			return true;
		}
		return false;
	}

	@Override
	public boolean hasUnlockedAbility(Ability abl)
	{
		this.unlockedAbilities.removeIf(ability -> ability == null);
		return this.unlockedAbilities.parallelStream().anyMatch(ability -> ability.equals(abl));
	}

	@Override
	public Ability getUnlockedAbility(Ability abl)
	{
		this.unlockedAbilities.removeIf(ability -> ability == null);
		return this.unlockedAbilities.parallelStream().filter(ability -> ability.equals(abl)).findFirst().orElse(null);
	}

	@Override
	public Ability getUnlockedAbility(int slot)
	{
		this.unlockedAbilities.removeIf(ability -> ability == null);
		return this.unlockedAbilities.size() > slot ? this.unlockedAbilities.get(slot) : null;
	}

	@Override
	public List<Ability> getUnlockedAbilities(AbilityCategory category)
	{
		this.unlockedAbilities.removeIf(ability -> ability == null);
		return this.unlockedAbilities.parallelStream().filter(ability -> ability.getCategory() == category || category == AbilityCategory.ALL).collect(Collectors.toList());
	}

	@Override
	public void clearUnlockedAbilities(AbilityCategory category)
	{
		this.unlockedAbilities.removeIf(ability -> ability == null || ability.getCategory() == category || category == AbilityCategory.ALL);
	}

	@Override
	public void clearUnlockedAbilityFromList(AbilityCategory category, List<Ability> list)
	{
		this.unlockedAbilities.removeIf(ability -> (ability == null || ability.getCategory() == category || category == AbilityCategory.ALL) && list.contains(ability));
	}

	@Override
	public int countUnlockedAbilities(AbilityCategory category)
	{
		this.unlockedAbilities.removeIf(ability -> ability == null);
		return this.unlockedAbilities.parallelStream().filter(ability -> ability.getCategory() == category || category == AbilityCategory.ALL).collect(Collectors.toList()).size();
	}

	/*
	 * Equipped Abilities
	 */

	@Override
	public boolean addEquippedAbility(Ability abl)
	{
		for(int i = 0; i < this.equippedAbilities.length; i++)
		{
			Ability ability = this.equippedAbilities[i];
			if(ability == null)
			{
				 this.equippedAbilities[i] = abl;
				 return true;
			}
		}
		return false;
	}

	@Override
	public boolean setEquippedAbility(int slot, Ability abl)
	{
		Ability ogAbl = this.getEquippedAbility(abl);
		if (ogAbl == null && slot <= APIConfig.MAX_SELECTED_ABILITIES)
		{
			this.equippedAbilities[slot] = abl;
			return true;
		}
		return false;
	}

	@Override
	public boolean removeEquippedAbility(Ability abl)
	{
		Ability ogAbl = this.getUnlockedAbility(abl);
		if (ogAbl != null)
		{		
			for(int i = 0; i < this.equippedAbilities.length; i++)
			{
				Ability ability = this.equippedAbilities[i];
				if(ability != null)
				{
					 this.equippedAbilities[i] = null;
					 return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean hasEquippedAbility(Ability abl)
	{
		return Arrays.stream(this.equippedAbilities)
				.parallel()
				.filter(ability -> ability != null)
				.anyMatch(ability -> ability.equals(abl));
	}

	@Override
	public Ability getEquippedAbility(Ability abl)
	{
		return Arrays.stream(this.equippedAbilities)
				.parallel()
				.filter(ability -> ability != null)
				.filter(ability -> ability.equals(abl))
				.findFirst().orElse(null);
	}

	@Override
	public Ability getEquippedAbility(int slot)
	{
		return this.equippedAbilities[slot];
	}

	@Override
	public Ability[] getEquippedAbilities()
	{
		return this.equippedAbilities;
	}
	
	@Override
	public Ability[] getEquippedAbilities(AbilityCategory category)
	{
		return this.equippedAbilities;
	}

	@Override
	public void clearEquippedAbilities(AbilityCategory category)
	{
		for(int i = 0; i < this.equippedAbilities.length; i++)
		{
			Ability ability = this.equippedAbilities[i];
			if((ability != null && ability.getCategory() != category) || category == AbilityCategory.ALL)
			{
				this.equippedAbilities[i] = null;
			}
		}
	}

	@Override
	public void clearEquippedAbilityFromList(AbilityCategory category, List<Ability> list)
	{
		for(int i = 0; i < this.equippedAbilities.length; i++)
		{
			Ability ability = this.equippedAbilities[i];
			if((ability != null && ability.getCategory() != category && !list.contains(ability)) || category != AbilityCategory.ALL)
			{
				this.equippedAbilities[i] = null;
			}
		}
	}

	@Override
	public int countEquippedAbilities(AbilityCategory category)
	{
		return Arrays.stream(this.equippedAbilities)
				.parallel()
				.filter(ability -> ability != null)
				.filter(ability -> ability.getCategory() == category || category == AbilityCategory.ALL)
				.collect(Collectors.toList())
				.size();
	}

	/*
	 * Previously Used Ability
	 */

	@Override
	public Ability getPreviouslyUsedAbility()
	{
		return this.previouslyUsedAbility;
	}

	@Override
	public void setPreviouslyUsedAbility(Ability abl)
	{
		this.previouslyUsedAbility = abl;
	}
}
