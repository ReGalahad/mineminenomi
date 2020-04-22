package xyz.pixelatedw.mineminenomi.entities.mobs.quest.objectives;

import net.minecraft.entity.player.PlayerEntity;

public interface IQuestObjective
{
	void setOwner(PlayerEntity player);
	PlayerEntity getOwner();
	
	void setActive(boolean active);
	boolean isActive();
}
