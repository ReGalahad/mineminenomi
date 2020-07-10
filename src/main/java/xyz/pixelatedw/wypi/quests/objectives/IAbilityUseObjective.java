package xyz.pixelatedw.wypi.quests.objectives;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.wypi.abilities.Ability;

public interface IAbilityUseObjective
{
	boolean checkAbility(PlayerEntity player, Ability ability);
}
