package xyz.pixelatedw.mineminenomi.quests;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.quests.swordsman.objectives.FindStrongSwordObjective;
import xyz.pixelatedw.mineminenomi.quests.swordsman.objectives.SwordKillWithCriticalObjective;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.quests.Quest;
import xyz.pixelatedw.wypi.quests.objectives.Objective;

public class Test2Quest extends Quest
{
	private Objective objective01 = new FindStrongSwordObjective();
	private Objective objective02 = new SwordKillWithCriticalObjective("Kill 1 enemies with critical hits", 1);
	private Objective objective03 = new SwordKillWithCriticalObjective("Kill 1 enemies with critical hits", 1);
	private Objective objective04 = new FindStrongSwordObjective().addRequirement(this.objective03);
	private Objective objective05 = new FindStrongSwordObjective();

	public Test2Quest()
	{
		super("test_quest_2", "Test Quest 2");
		this.addObjectives(this.objective01, this.objective02, this.objective03, this.objective04, this.objective05);
		
		this.onCompleteEvent = this::giveReward;
	}

	public void giveReward(PlayerEntity player)
	{
		WyHelper.sendMsgToPlayer(player, "Test Quest 2 complete!");
	}
}
