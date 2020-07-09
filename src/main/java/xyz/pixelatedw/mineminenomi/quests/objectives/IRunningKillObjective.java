package xyz.pixelatedw.mineminenomi.quests.objectives;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.wypi.quests.objectives.IKillEntityObjective;

public interface IRunningKillObjective extends IKillEntityObjective
{
	public default boolean checkRunningKill(PlayerEntity player, LivingEntity target)
	{
		boolean runningFlag = player.isSprinting();
		
		return runningFlag;
	}
}
