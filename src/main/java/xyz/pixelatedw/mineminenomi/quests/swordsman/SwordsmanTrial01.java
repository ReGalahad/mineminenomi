package xyz.pixelatedw.mineminenomi.quests.swordsman;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.quests.objectives.KillWithCriticalObjective;
import xyz.pixelatedw.mineminenomi.quests.swordsman.objectives.FindStrongSwordObjective;
import xyz.pixelatedw.mineminenomi.quests.swordsman.objectives.TalkWithSwordsmanSenseiObjective;
import xyz.pixelatedw.wypi.quests.Quest;
import xyz.pixelatedw.wypi.quests.objectives.Objective;

public class SwordsmanTrial01 extends Quest
{	
	private Objective objective01 = new FindStrongSwordObjective();
	private Objective objective02 = new KillWithCriticalObjective("Kill 5 enemies with critical hits", 5).addRequirement(this.objective01);
	private Objective objective03 = new TalkWithSwordsmanSenseiObjective().addRequirement(this.objective02);

	public SwordsmanTrial01()
	{
		super("swordsman_trial_01", "Trial: Shi Shishi Sonson");
		this.addObjectives(this.objective01, this.objective02, this.objective03);
	}

	@Override
	public boolean canStart(PlayerEntity player)
	{
		IEntityStats eprops = EntityStatsCapability.get(player);
		return eprops.isSwordsman();
	}

}
