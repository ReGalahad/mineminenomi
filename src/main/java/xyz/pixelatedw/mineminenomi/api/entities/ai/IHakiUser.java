package xyz.pixelatedw.mineminenomi.api.entities.ai;

import xyz.pixelatedw.mineminenomi.entities.mobs.GenericNewEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.ai.abilities.haki.BusoshokuHakiGoal;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.debug.WyDebug;

public interface IHakiUser
{	
	public default void addBusoshokuHaki(GenericNewEntity entity, int chance)
	{
		if(entity.world.isRemote)
			return;
		
		if(WyHelper.randomWithRange(0, 100) <= chance)
		{
			entity.goalSelector.addGoal(1, new BusoshokuHakiGoal(entity));
			
			if(WyDebug.isDebug())
				System.out.println("Bushoshoku Haki");
		}
	}
}
