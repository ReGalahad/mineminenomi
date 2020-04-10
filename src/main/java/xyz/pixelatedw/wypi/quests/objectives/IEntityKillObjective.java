package xyz.pixelatedw.wypi.quests.objectives;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public interface IEntityKillObjective
{
	void onKill(PlayerEntity player, LivingEntity target);
}
