package xyz.pixelatedw.mineminenomi.quests.swordsman;

import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.abilities.swordsman.YakkodoriAbility;
import xyz.pixelatedw.mineminenomi.quests.objectives.ObtainEnchantedItemObjective;
import xyz.pixelatedw.mineminenomi.quests.objectives.sword.SwordKillWithCriticalObjective;
import xyz.pixelatedw.mineminenomi.quests.swordsman.objectives.FindStrongSwordObjective;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.wypi.network.WyNetwork;
import xyz.pixelatedw.wypi.network.packets.client.CSyncAbilityDataPacket;
import xyz.pixelatedw.wypi.quests.Quest;
import xyz.pixelatedw.wypi.quests.objectives.Objective;

public class SwordsmanTrial02Quest extends Quest
{
	private Objective objective01 = new FindStrongSwordObjective();
	private Objective objective02 = new SwordKillWithCriticalObjective("Kill %s enemies with critical hits", 20).addRequirement(this.objective01);
	private Objective objective03 = new ObtainEnchantedItemObjective("Obtain a sword with Sharpness", Enchantments.SHARPNESS, 1).addRequirement(this.objective01);

	public SwordsmanTrial02Quest()
	{
		super("swordsman_trial_02", "Trial: Yakkodori");
		this.addObjectives(this.objective01, this.objective02, this.objective03);
		
		this.onCompleteEvent = this::giveReward;
	}

	public void giveReward(PlayerEntity player)
	{
		IAbilityData props = AbilityDataCapability.get(player);
		
		props.addUnlockedAbility(YakkodoriAbility.INSTANCE);
		WyNetwork.sendToServer(new CSyncAbilityDataPacket(props));
	}
}
