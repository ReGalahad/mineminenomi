package xyz.pixelatedw.mineminenomi.quests.swordsman;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import xyz.pixelatedw.mineminenomi.abilities.swordsman.ShiShishiSonsonAbility;
import xyz.pixelatedw.mineminenomi.quests.objectives.ObtainItemObjective;
import xyz.pixelatedw.mineminenomi.quests.objectives.sword.SwordKillRunningObjective;
import xyz.pixelatedw.mineminenomi.quests.swordsman.objectives.FindStrongSwordObjective;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.wypi.network.WyNetwork;
import xyz.pixelatedw.wypi.network.packets.client.CSyncAbilityDataPacket;
import xyz.pixelatedw.wypi.quests.Quest;
import xyz.pixelatedw.wypi.quests.objectives.Objective;

public class SwordsmanTrial01Quest extends Quest
{
	private Objective objective01 = new FindStrongSwordObjective();
	private Objective objective02 = new ObtainItemObjective("Collect %s bones", Items.BONE, 30).addRequirement(this.objective01);
	private Objective objective03 = new SwordKillRunningObjective("Kill %s enemies while running towards them", 15).addRequirement(this.objective01);

	public SwordsmanTrial01Quest()
	{
		super("swordsman_trial_01", "Trial: Shi Shishi Sonson");
		this.addObjectives(this.objective01, this.objective02, this.objective03);
		
		this.onCompleteEvent = this::giveReward;
	}

	public void giveReward(PlayerEntity player)
	{
		IAbilityData props = AbilityDataCapability.get(player);
		
		props.addUnlockedAbility(ShiShishiSonsonAbility.INSTANCE);
		WyNetwork.sendToServer(new CSyncAbilityDataPacket(props));
	}
}
