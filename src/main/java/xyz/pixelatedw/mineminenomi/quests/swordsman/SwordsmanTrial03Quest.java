package xyz.pixelatedw.mineminenomi.quests.swordsman;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.abilities.swordsman.SanbyakurokujuPoundHoAbility;
import xyz.pixelatedw.mineminenomi.init.ModQuests;
import xyz.pixelatedw.mineminenomi.quests.objectives.swordsman.ObtainStrongSwordObjective;
import xyz.pixelatedw.mineminenomi.quests.objectives.swordsman.ShiShishiSonsonKillObjective;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.wypi.network.WyNetwork;
import xyz.pixelatedw.wypi.network.packets.client.CSyncAbilityDataPacket;
import xyz.pixelatedw.wypi.quests.Quest;
import xyz.pixelatedw.wypi.quests.objectives.Objective;

public class SwordsmanTrial03Quest extends Quest
{
	private Objective objective01 = new ObtainStrongSwordObjective();
	private Objective objective02 = new ShiShishiSonsonKillObjective("Kill %s enemies using Shi Shishi Sonson", 15).addRequirement(this.objective01);
	
	public SwordsmanTrial03Quest()
	{
		super("swordsman_trial_03", "Trial: Sanbyakurokuju Pound Ho");
		this.addRequirement(ModQuests.SWORDSMAN_TRIAL_01);
		this.addObjectives(this.objective01, this.objective02);
		
		this.onCompleteEvent = this::giveReward;
	}
	
	public void giveReward(PlayerEntity player)
	{
		IAbilityData props = AbilityDataCapability.get(player);
		
		props.addUnlockedAbility(SanbyakurokujuPoundHoAbility.INSTANCE);
		WyNetwork.sendToServer(new CSyncAbilityDataPacket(props));
	}
}
