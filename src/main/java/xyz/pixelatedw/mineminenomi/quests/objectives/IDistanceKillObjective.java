package xyz.pixelatedw.mineminenomi.quests.objectives;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.wypi.quests.objectives.IKillEntityObjective;

public interface IDistanceKillObjective extends IKillEntityObjective
{
	public default boolean checkDistanceKill(PlayerEntity player, LivingEntity target, float distance)
	{
		boolean distanceFlag = player.getDistance(target) >= distance; 
				
		return distanceFlag;
	}
}
