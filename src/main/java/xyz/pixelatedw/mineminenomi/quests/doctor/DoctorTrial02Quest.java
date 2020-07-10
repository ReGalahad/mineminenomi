package xyz.pixelatedw.mineminenomi.quests.doctor;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import xyz.pixelatedw.mineminenomi.abilities.doctor.MedicBagExplosionAbility;
import xyz.pixelatedw.mineminenomi.init.ModArmors;
import xyz.pixelatedw.mineminenomi.init.ModQuests;
import xyz.pixelatedw.mineminenomi.quests.objectives.EquippedItemObjective;
import xyz.pixelatedw.mineminenomi.quests.objectives.doctor.FirstAidHitObjective;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.wypi.network.WyNetwork;
import xyz.pixelatedw.wypi.network.packets.client.CSyncAbilityDataPacket;
import xyz.pixelatedw.wypi.quests.Quest;
import xyz.pixelatedw.wypi.quests.objectives.Objective;

public class DoctorTrial02Quest extends Quest
{
	private Objective objective01 = new EquippedItemObjective("Equip a %s", 1, ModArmors.MEDIC_BAG, EquipmentSlotType.CHEST);
	private Objective objective02 = new FirstAidHitObjective("Heal %s %s using First Aid", 10, EntityType.VILLAGER).addRequirement(this.objective01);
	
	public DoctorTrial02Quest()
	{
		super("doctor_trial_02", "Trial: Medic Bag Explosion");
		this.addObjectives(this.objective01, this.objective02);
		this.addRequirement(ModQuests.DOCTOR_TRIAL_01);
		
		this.onCompleteEvent = this::giveReward;
	}

	public void giveReward(PlayerEntity player)
	{
		IAbilityData props = AbilityDataCapability.get(player);
		
		props.addUnlockedAbility(MedicBagExplosionAbility.INSTANCE);
		WyNetwork.sendToServer(new CSyncAbilityDataPacket(props));
	}
}
