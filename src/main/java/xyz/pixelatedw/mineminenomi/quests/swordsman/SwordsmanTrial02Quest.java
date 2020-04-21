package xyz.pixelatedw.mineminenomi.quests.swordsman;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.quests.swordsman.objectives.FindStrongSwordObjective;
import xyz.pixelatedw.mineminenomi.quests.swordsman.objectives.SwordKillWithCriticalObjective;
import xyz.pixelatedw.wypi.quests.Quest;
import xyz.pixelatedw.wypi.quests.objectives.Objective;

public class SwordsmanTrial02Quest extends Quest
{
	private Objective objective01 = new FindStrongSwordObjective();
	private Objective objective02 = new SwordKillWithCriticalObjective("Kill 5 enemies with critical hits", 5).addRequirement(this.objective01);

	public SwordsmanTrial02Quest()
	{
		super("swordsman_trial_02", "Trial: Yakkodori");
		this.addObjectives(this.objective01, this.objective02);
	}

	@Override
	public boolean canStart(PlayerEntity player)
	{
		IEntityStats eprops = EntityStatsCapability.get(player);
		return eprops.isSwordsman();
	}
}
