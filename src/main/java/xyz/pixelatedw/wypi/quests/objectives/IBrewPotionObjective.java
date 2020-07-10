package xyz.pixelatedw.wypi.quests.objectives;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public interface IBrewPotionObjective
{
	boolean checkPotion(PlayerEntity player, ItemStack stack);
}
