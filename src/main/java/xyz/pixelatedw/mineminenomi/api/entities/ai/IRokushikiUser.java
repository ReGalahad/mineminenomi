package xyz.pixelatedw.mineminenomi.api.entities.ai;

import java.util.ArrayList;
import java.util.List;

import xyz.pixelatedw.mineminenomi.entities.mobs.GenericNewEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.ai.abilities.rokushiki.GeppoGoal;
import xyz.pixelatedw.mineminenomi.entities.mobs.ai.abilities.rokushiki.RankyakuGoal;
import xyz.pixelatedw.mineminenomi.entities.mobs.ai.abilities.rokushiki.SoruGoal;
import xyz.pixelatedw.mineminenomi.entities.mobs.ai.abilities.rokushiki.TekkaiGoal;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.debug.WyDebug;

public interface IRokushikiUser
{
	public default void addRokushikiAbilities(GenericNewEntity entity, int max)
	{
		if(entity.world.isRemote)
			return;
		
		int rokushikiCount = 0;
		List<String> goals = new ArrayList<String>();
	
		while(rokushikiCount < max)
		{
			if(!goals.contains("soru") && WyHelper.randomWithRange(1, 10) >= 8)
			{
				entity.goalSelector.addGoal(1, new SoruGoal(entity));
				rokushikiCount++;
				goals.add("soru");
				continue;
			}
	
			if(!goals.contains("tekkai") && WyHelper.randomWithRange(1, 10) >= 8)
			{
				entity.goalSelector.addGoal(1, new TekkaiGoal(entity));
				rokushikiCount++;
				goals.add("tekkai");
				continue;
			}
			
			if(!goals.contains("rankyaku") && WyHelper.randomWithRange(1, 10) >= 5)
			{
				entity.goalSelector.addGoal(1, new RankyakuGoal(entity));
				rokushikiCount++;
				goals.add("rankyaku");
				continue;
			}
			
			if(!goals.contains("geppo") && WyHelper.randomWithRange(1, 10) >= 5)
			{
				entity.goalSelector.addGoal(1, new GeppoGoal(entity));
				rokushikiCount++;
				goals.add("geppo");
				continue;
			}

			rokushikiCount++;
		}
		
		if(WyDebug.isDebug())
		{
			System.out.println("Abilities : ");
			System.out.println(goals);
		}
	}
}
