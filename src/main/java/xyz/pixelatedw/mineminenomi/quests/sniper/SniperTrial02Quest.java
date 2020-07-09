package xyz.pixelatedw.mineminenomi.quests.sniper;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.abilities.sniper.KemuriBoshiAbility;
import xyz.pixelatedw.mineminenomi.init.ModQuests;
import xyz.pixelatedw.mineminenomi.quests.objectives.ObtainBowObjective;
import xyz.pixelatedw.mineminenomi.quests.sniper.objectives.KaenHitObjective;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.wypi.network.WyNetwork;
import xyz.pixelatedw.wypi.network.packets.client.CSyncAbilityDataPacket;
import xyz.pixelatedw.wypi.quests.Quest;
import xyz.pixelatedw.wypi.quests.objectives.Objective;

public class SniperTrial02Quest extends Quest
{
	private Objective objective01 = new ObtainBowObjective();
	private Objective objective02 = new KaenHitObjective("Set %s enemies on fire using Kaen Boshi", 20).addRequirement(this.objective01);

	public SniperTrial02Quest()
	{
		super("sniper_trial_01", "Trial: Kemuri Boshi");
		this.addRequirements(ModQuests.SNIPER_TRIAL_01);
		this.addObjectives(this.objective01, this.objective02);
		
		this.onCompleteEvent = this::giveReward;
	}

	public void giveReward(PlayerEntity player)
	{
		IAbilityData props = AbilityDataCapability.get(player);
		
		props.addUnlockedAbility(KemuriBoshiAbility.INSTANCE);
		WyNetwork.sendToServer(new CSyncAbilityDataPacket(props));
	}
}
