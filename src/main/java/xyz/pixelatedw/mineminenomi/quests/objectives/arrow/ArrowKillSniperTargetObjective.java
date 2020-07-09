package xyz.pixelatedw.mineminenomi.quests.objectives.arrow;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.entities.mobs.quest.objectives.SniperTargetEntity;
import xyz.pixelatedw.wypi.quests.objectives.IKillEntityObjective;

public class ArrowKillSniperTargetObjective extends ArrowKillObjective implements IKillEntityObjective
{
	public ArrowKillSniperTargetObjective(String title, int count)
	{
		super(title, count);
	}

	@Override
	public boolean checkKill(PlayerEntity player, LivingEntity target, DamageSource source)
	{
		boolean isTargetFlag = target instanceof SniperTargetEntity;
		
		return super.checkKill(player, target, source) && isTargetFlag;
	}
}