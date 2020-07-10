package xyz.pixelatedw.wypi.quests.objectives;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public interface IEquipItemObjective
{
	boolean checkEquippedItem(PlayerEntity player, ItemStack stack);
}
