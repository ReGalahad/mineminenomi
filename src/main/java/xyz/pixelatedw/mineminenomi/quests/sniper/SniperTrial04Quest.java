package xyz.pixelatedw.mineminenomi.quests.sniper;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import xyz.pixelatedw.mineminenomi.abilities.sniper.SakuretsuSabotenBoshiAbility;
import xyz.pixelatedw.mineminenomi.init.ModQuests;
import xyz.pixelatedw.mineminenomi.quests.objectives.ObtainBowObjective;
import xyz.pixelatedw.mineminenomi.quests.objectives.ObtainItemObjective;
import xyz.pixelatedw.mineminenomi.quests.objectives.sniper.RenpatsuKillInSecondsObjective;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.wypi.network.WyNetwork;
import xyz.pixelatedw.wypi.network.packets.client.CSyncAbilityDataPacket;
import xyz.pixelatedw.wypi.quests.Quest;
import xyz.pixelatedw.wypi.quests.objectives.Objective;

public class SniperTrial04Quest extends Quest
{
	private Objective objective01 = new ObtainBowObjective();
	private Objective objective02 = new RenpatsuKillInSecondsObjective("Kill %s enemies in less than %s seconds using Renpatsu Namari Boshi", 3, 5).addRequirement(this.objective01);
	private Objective objective03 = new ObtainItemObjective("Collect %s cacti", Items.CACTUS, 50).addRequirement(this.objective01);

	public SniperTrial04Quest()
	{
		super("sniper_trial_04", "Trial: Sakuretsu Saboten Boshi");
		this.addRequirements(ModQuests.SNIPER_TRIAL_03);
		this.addObjectives(this.objective01, this.objective02, this.objective03);
		
		this.onCompleteEvent = this::giveReward;
	}

	private void giveReward(PlayerEntity player)
	{
		IAbilityData props = AbilityDataCapability.get(player);
		
		props.addUnlockedAbility(SakuretsuSabotenBoshiAbility.INSTANCE);
		WyNetwork.sendToServer(new CSyncAbilityDataPacket(props));
	}
}
