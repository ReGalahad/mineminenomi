package xyz.pixelatedw.mineminenomi.api.quests;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public interface IKillQuest
{

	boolean isTarget(PlayerEntity player, LivingEntity target);
	
}
