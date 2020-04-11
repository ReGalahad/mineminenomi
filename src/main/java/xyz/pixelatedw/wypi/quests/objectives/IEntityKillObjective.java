package xyz.pixelatedw.wypi.quests.objectives;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;

public interface IEntityKillObjective
{
	boolean checkKill(PlayerEntity player, LivingEntity target, DamageSource source);
}
