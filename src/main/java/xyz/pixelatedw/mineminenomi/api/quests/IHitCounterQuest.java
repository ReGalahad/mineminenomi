package xyz.pixelatedw.mineminenomi.api.quests;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;

public interface IHitCounterQuest
{

	boolean checkHit(PlayerEntity player, LivingEntity target, DamageSource source);
	
}
