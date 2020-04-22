package xyz.pixelatedw.mineminenomi.quests.sniper;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.abilities.sniper.SakuretsuSabotenBoshiAbility;
import xyz.pixelatedw.mineminenomi.init.ModQuests;
import xyz.pixelatedw.mineminenomi.quests.sniper.objectives.HoldBowObjective;
import xyz.pixelatedw.mineminenomi.quests.sniper.objectives.RenpatsuKillInSecondsObjective;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.wypi.network.WyNetwork;
import xyz.pixelatedw.wypi.network.packets.client.CSyncAbilityDataPacket;
import xyz.pixelatedw.wypi.quests.Quest;
import xyz.pixelatedw.wypi.quests.objectives.Objective;

public class SniperTrial04Quest extends Quest
{
	private Objective objective01 = new HoldBowObjective();
	private Objective objective02 = new RenpatsuKillInSecondsObjective("Kill 3 enemies in less than 5 seconds using Renpatsu Namari Boshi", 3, 100).addRequirement(this.objective01);
	
	public SniperTrial04Quest()
	{
		super("sniper_trial_04", "Trial: Sakuretsu Saboten Boshi");
		this.addRequirements(ModQuests.SNIPER_TRIAL_03);
		this.addObjectives(this.objective01, this.objective02);
		
		this.onCompleteEvent = this::giveReward;
	}

	private void giveReward(PlayerEntity player)
	{
		IAbilityData props = AbilityDataCapability.get(player);
		
		props.addUnlockedAbility(SakuretsuSabotenBoshiAbility.INSTANCE);
		WyNetwork.sendToServer(new CSyncAbilityDataPacket(props));
	}
}
