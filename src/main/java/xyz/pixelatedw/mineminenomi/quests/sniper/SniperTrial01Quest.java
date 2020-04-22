package xyz.pixelatedw.mineminenomi.quests.sniper;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.abilities.sniper.KaenBoshiAbility;
import xyz.pixelatedw.mineminenomi.quests.sniper.objectives.ArrowKillFromDistanceObjective;
import xyz.pixelatedw.mineminenomi.quests.sniper.objectives.HoldBowObjective;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.wypi.network.WyNetwork;
import xyz.pixelatedw.wypi.network.packets.client.CSyncAbilityDataPacket;
import xyz.pixelatedw.wypi.quests.Quest;
import xyz.pixelatedw.wypi.quests.objectives.Objective;

public class SniperTrial01Quest extends Quest
{
	private Objective objective01 = new HoldBowObjective();
	private Objective objective02 = new ArrowKillFromDistanceObjective("Kill an enemy from 30 blocks away", 1, 30).addRequirement(this.objective01);

	public SniperTrial01Quest()
	{
		super("sniper_trial_01", "Trial: Kaen Boshi");
		this.addObjectives(this.objective01, this.objective02);
		
		this.onCompleteEvent = this::giveReward;
	}

	public void giveReward(PlayerEntity player)
	{
		IAbilityData props = AbilityDataCapability.get(player);
		
		props.addUnlockedAbility(KaenBoshiAbility.INSTANCE);
		WyNetwork.sendToServer(new CSyncAbilityDataPacket(props));
	}
}
