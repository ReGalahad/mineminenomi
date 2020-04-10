package xyz.pixelatedw.mineminenomi.quests.swordsman;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.quests.swordsman.objectives.StrongSwordObjective;
import xyz.pixelatedw.wypi.quests.Quest;

public class SwordsmanTrial01 extends Quest
{

	public SwordsmanTrial01()
	{
		super("swordsman_trial_01", "Trial: Shi Shishi Sonson");
		this.addObjective(new StrongSwordObjective());
	}

	@Override
	public boolean canStart(PlayerEntity player)
	{
		IEntityStats eprops = EntityStatsCapability.get(player);
		return eprops.isSwordsman();
	}

}
