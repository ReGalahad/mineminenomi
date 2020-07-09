package xyz.pixelatedw.mineminenomi.quests.objectives;

import net.minecraft.entity.LivingEntity;
import xyz.pixelatedw.wypi.quests.objectives.IKillEntityObjective;

public interface IAirborneKillObjective extends IKillEntityObjective
{
	public default boolean checkAirborneKill(LivingEntity target)
	{
		return !target.onGround;
	}
}
