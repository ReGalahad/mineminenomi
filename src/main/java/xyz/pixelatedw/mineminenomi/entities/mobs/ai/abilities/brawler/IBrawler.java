package xyz.pixelatedw.mineminenomi.entities.mobs.ai.abilities.brawler;

import java.util.ArrayList;
import java.util.List;

import xyz.pixelatedw.mineminenomi.entities.mobs.GenericNewEntity;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.debug.WyDebug;

public interface IBrawler
{
	public default void addBrawlerAbilities(GenericNewEntity entity, int max)
	{
		if(entity.world.isRemote)
			return;
		
		int abilitiesCount = 0;
		List<String> goals = new ArrayList<String>();
	
		while(abilitiesCount < max)
		{
			if(!goals.contains("hakai_ho") && WyHelper.randomWithRange(1, 10) >= 8)
			{
				entity.goalSelector.addGoal(1, new HakaiHoGoal(entity));
				abilitiesCount++;
				goals.add("hakai_ho");
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
