package xyz.pixelatedw.mineminenomi.entities.mobs.quest.givers;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.wypi.quests.Quest;

public interface IQuestGiver
{

	public Quest[] getAvailableQuests(PlayerEntity player);
	
}
