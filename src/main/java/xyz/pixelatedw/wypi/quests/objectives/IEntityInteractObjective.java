package xyz.pixelatedw.wypi.quests.objectives;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public interface IEntityInteractObjective
{
	void onInteractWith(PlayerEntity player, LivingEntity entity);
}
