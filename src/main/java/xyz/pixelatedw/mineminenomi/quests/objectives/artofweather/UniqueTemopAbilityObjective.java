package xyz.pixelatedw.mineminenomi.quests.objectives.artofweather;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.TempoAbility;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.quests.objectives.IAbilityUseObjective;
import xyz.pixelatedw.wypi.quests.objectives.Objective;

public class UniqueTemopAbilityObjective extends Objective implements IAbilityUseObjective
{
	private List<String> usedTempos = new ArrayList<String>();
	
	public UniqueTemopAbilityObjective(String title, int count)
	{
		super(title);
		this.setMaxProgress(count);
	}

	@Override
	public boolean checkAbility(PlayerEntity player, Ability ability)
	{
		boolean isTempo = false;
		String abilityName = WyHelper.getResourceName(ability.getName());
		
		if(this.usedTempos.contains(abilityName))
			return false;
		
		if(ability instanceof TempoAbility)
		{
			isTempo = true;
			this.usedTempos.add(abilityName);
		}
		
		return isTempo;
	}
}
