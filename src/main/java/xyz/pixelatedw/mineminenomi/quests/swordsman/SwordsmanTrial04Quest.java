package xyz.pixelatedw.mineminenomi.quests.swordsman;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.abilities.swordsman.OTatsumakiAbility;
import xyz.pixelatedw.mineminenomi.init.ModQuests;
import xyz.pixelatedw.mineminenomi.quests.swordsman.objectives.FindStrongSwordObjective;
import xyz.pixelatedw.mineminenomi.quests.swordsman.objectives.SwordKillInSecondsObjective;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.wypi.network.WyNetwork;
import xyz.pixelatedw.wypi.network.packets.client.CSyncAbilityDataPacket;
import xyz.pixelatedw.wypi.quests.Quest;
import xyz.pixelatedw.wypi.quests.objectives.Objective;

public class SwordsmanTrial04Quest extends Quest
{
	private Objective objective01 = new FindStrongSwordObjective();
	private Objective objective02 = new SwordKillInSecondsObjective("Kill 3 enemies in less than 5 seconds", 3).addRequirement(this.objective01);
	
	public SwordsmanTrial04Quest()
	{
		super("swordsman_trial_04", "Trial: O Tatsumaki");
		this.addRequirements(ModQuests.SWORDSMAN_TRIAL_03);
		this.addObjectives(this.objective01, this.objective02);
		
		this.onCompleteEvent = this::giveReward;
	}
	
	public void giveReward(PlayerEntity player)
	{
		IAbilityData props = AbilityDataCapability.get(player);
		
		props.addUnlockedAbility(OTatsumakiAbility.INSTANCE);
		WyNetwork.sendToServer(new CSyncAbilityDataPacket(props));
	}
}
