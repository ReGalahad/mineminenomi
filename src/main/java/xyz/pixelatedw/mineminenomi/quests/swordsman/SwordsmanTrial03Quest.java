package xyz.pixelatedw.mineminenomi.quests.swordsman;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.init.ModQuests;
import xyz.pixelatedw.mineminenomi.quests.swordsman.objectives.FindStrongSwordObjective;
import xyz.pixelatedw.mineminenomi.quests.swordsman.objectives.ShiShishiSonsonKillObjective;
import xyz.pixelatedw.mineminenomi.quests.swordsman.objectives.TalkWithSwordsmanSenseiObjective;
import xyz.pixelatedw.wypi.quests.Quest;
import xyz.pixelatedw.wypi.quests.objectives.Objective;

public class SwordsmanTrial03Quest extends Quest
{
	private Objective objective01 = new FindStrongSwordObjective();
	private Objective objective02 = new ShiShishiSonsonKillObjective("Kill 10 enemies using Shi Shishi Sonson", 10).addRequirement(this.objective01);
	private Objective objective03 = new TalkWithSwordsmanSenseiObjective().addRequirement(this.objective02);
	
	public SwordsmanTrial03Quest()
	{
		super("swordsman_trial_03", "Trial: Sanbyakurokuju Pound Ho");
		this.addRequirement(ModQuests.SWORDSMAN_TRIAL_01);
		this.addObjectives(this.objective01, this.objective02, this.objective03);
	}

	@Override
	public boolean canStart(PlayerEntity player)
	{
		IEntityStats eprops = EntityStatsCapability.get(player);
		return eprops.isSwordsman();
	}
}
