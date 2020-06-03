package xyz.pixelatedw.mineminenomi.api.entities.ai;

import java.util.ArrayList;
import java.util.List;

import xyz.pixelatedw.mineminenomi.entities.mobs.GenericNewEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.ai.abilities.swordsman.OTatsumakiGoal;
import xyz.pixelatedw.mineminenomi.entities.mobs.ai.abilities.swordsman.YakkodoriGoal;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.debug.WyDebug;

public interface ISwordsman
{
	public default void addSwordsmanAbilities(GenericNewEntity entity, int max)
	{
		if(entity.world.isRemote)
			return;
		
		int abilitiesCount = 0;
		List<String> goals = new ArrayList<String>();
	
		while(abilitiesCount < max)
		{
			if(!goals.contains("yakkodori") && WyHelper.randomWithRange(1, 10) >= 6)
			{
				entity.goalSelector.addGoal(1, new YakkodoriGoal(entity));
				abilitiesCount++;
				goals.add("yakkodori");
				continue;
			}
			
			if(!goals.contains("otatsumaki") && WyHelper.randomWithRange(1, 10) >= 8)
			{
				entity.goalSelector.addGoal(1, new OTatsumakiGoal(entity));
				abilitiesCount++;
				goals.add("otatsumaki");
				continue;
			}

			abilitiesCount++;
		}
		
		if(WyDebug.isDebug())
		{
			System.out.println("Abilities : ");
			System.out.println(goals);
		}
	}
}
