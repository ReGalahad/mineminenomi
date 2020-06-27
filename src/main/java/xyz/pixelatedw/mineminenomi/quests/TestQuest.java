package xyz.pixelatedw.mineminenomi.quests;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.quests.swordsman.objectives.FindStrongSwordObjective;
import xyz.pixelatedw.mineminenomi.quests.swordsman.objectives.SwordKillRunningObjective;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.quests.Quest;
import xyz.pixelatedw.wypi.quests.objectives.Objective;

public class TestQuest extends Quest
{
	private Objective objective01 = new FindStrongSwordObjective();
	private Objective objective02 = new SwordKillRunningObjective("Kill 1 enemies while running towards them", 1).addRequirement(this.objective01);
	private Objective objective03 = new SwordKillRunningObjective("Kill 1 enemies while running towards them", 1).addRequirement(this.objective02).markHidden();
	private Objective objective04 = new FindStrongSwordObjective().addRequirement(this.objective03);
	private Objective objective05 = new FindStrongSwordObjective().addRequirement(this.objective04).markHidden();

	public TestQuest()
	{
		super("test_quest", "Test Quest");
		this.addObjectives(this.objective01, this.objective02, this.objective03, this.objective04, this.objective05);
		
		this.onCompleteEvent = this::giveReward;
	}

	public void giveReward(PlayerEntity player)
	{
		WyHelper.sendMsgToPlayer(player, "Test Quest complete!");
	}
}
