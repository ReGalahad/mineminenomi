package xyz.pixelatedw.mineminenomi.quests.objectives;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.wypi.quests.objectives.IKillEntityObjective;

public interface ICriticalKillObjective extends IKillEntityObjective
{
	public default boolean checkCriticalKill(PlayerEntity player)
	{
		boolean criticalFlag = player.fallDistance > 0.0F && !player.onGround && !player.isOnLadder() && !player.isInWater() && !player.isPassenger();
		
		return criticalFlag;
	}
}
