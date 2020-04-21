package xyz.pixelatedw.mineminenomi.quests.swordsman;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.quests.swordsman.objectives.FindStrongSwordObjective;
import xyz.pixelatedw.mineminenomi.quests.swordsman.objectives.SwordKillRunningObjective;
import xyz.pixelatedw.wypi.quests.Quest;
import xyz.pixelatedw.wypi.quests.objectives.Objective;

public class SwordsmanTrial01Quest extends Quest
{
	private Objective objective01 = new FindStrongSwordObjective();
	private Objective objective02 = new SwordKillRunningObjective("Kill 5 enemies with running towards them", 5).addRequirement(this.objective01);

	public SwordsmanTrial01Quest()
	{
		super("swordsman_trial_01", "Trial: Shi Shishi Sonson");
		this.addObjectives(this.objective01, this.objective02);
	}

	@Override
	public boolean canStart(PlayerEntity player)
	{
		IEntityStats eprops = EntityStatsCapability.get(player);
		return eprops.isSwordsman();
	}
}
