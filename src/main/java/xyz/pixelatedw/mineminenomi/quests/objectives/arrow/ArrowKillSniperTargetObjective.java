package xyz.pixelatedw.mineminenomi.quests.objectives.arrow;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.entities.mobs.quest.objectives.SniperTargetEntity;
import xyz.pixelatedw.mineminenomi.quests.objectives.IArrowKillObjective;
import xyz.pixelatedw.wypi.quests.objectives.Objective;

public class ArrowKillSniperTargetObjective extends Objective implements IArrowKillObjective
{
	public ArrowKillSniperTargetObjective(String title, int count)
	{
		super(title);
		this.setMaxProgress(count);
	}

	@Override
	public boolean checkKill(PlayerEntity player, LivingEntity target, DamageSource source)
	{
		boolean isTargetFlag = target instanceof SniperTargetEntity;
		
		return this.checkArrowKill(player, target, source) && isTargetFlag;
	}
}