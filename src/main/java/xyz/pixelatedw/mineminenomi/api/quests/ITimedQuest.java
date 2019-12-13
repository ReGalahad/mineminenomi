package xyz.pixelatedw.mineminenomi.api.quests;

import net.minecraft.entity.player.PlayerEntity;

public interface ITimedQuest
{

	void onTimePassEvent(PlayerEntity player);
	
}
