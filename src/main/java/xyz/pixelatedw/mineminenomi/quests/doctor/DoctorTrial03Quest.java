package xyz.pixelatedw.mineminenomi.quests.doctor;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import xyz.pixelatedw.mineminenomi.abilities.doctor.FailedExperimentAbility;
import xyz.pixelatedw.mineminenomi.init.ModArmors;
import xyz.pixelatedw.mineminenomi.init.ModQuests;
import xyz.pixelatedw.mineminenomi.quests.objectives.BrewPotionObjective;
import xyz.pixelatedw.mineminenomi.quests.objectives.EquippedItemObjective;
import xyz.pixelatedw.mineminenomi.quests.objectives.doctor.MedicBagExplosionObjective;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.wypi.network.WyNetwork;
import xyz.pixelatedw.wypi.network.packets.client.CSyncAbilityDataPacket;
import xyz.pixelatedw.wypi.quests.Quest;
import xyz.pixelatedw.wypi.quests.objectives.Objective;

public class DoctorTrial03Quest extends Quest
{
	private Objective objective01 = new EquippedItemObjective("Equip a %s", 1, ModArmors.MEDIC_BAG, EquipmentSlotType.CHEST);
	private Objective objective02 = new BrewPotionObjective("Brew any %s splash potions", 9, new Item[] { Items.SPLASH_POTION }, null).addRequirement(this.objective01);
	private Objective objective03 = new MedicBagExplosionObjective("Catch 5 or more enemies in your Medic Bag Explosion").addRequirement(this.objective02);
	
	public DoctorTrial03Quest()
	{
		super("doctor_trial_03", "Trial: Failed Experiment");
		this.addObjectives(this.objective01, this.objective02, this.objective03);
		this.addRequirement(ModQuests.DOCTOR_TRIAL_02);
		
		this.onCompleteEvent = this::giveReward;
	}

	public void giveReward(PlayerEntity player)
	{
		IAbilityData props = AbilityDataCapability.get(player);
		
		props.addUnlockedAbility(FailedExperimentAbility.INSTANCE);
		WyNetwork.sendToServer(new CSyncAbilityDataPacket(props));
	}
}
