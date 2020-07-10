package xyz.pixelatedw.mineminenomi.quests.doctor;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Items;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.mineminenomi.abilities.doctor.FirstAidAbility;
import xyz.pixelatedw.mineminenomi.init.ModArmors;
import xyz.pixelatedw.mineminenomi.quests.objectives.BrewPotionObjective;
import xyz.pixelatedw.mineminenomi.quests.objectives.EquippedItemObjective;
import xyz.pixelatedw.mineminenomi.quests.objectives.ObtainItemObjective;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.wypi.network.WyNetwork;
import xyz.pixelatedw.wypi.network.packets.client.CSyncAbilityDataPacket;
import xyz.pixelatedw.wypi.quests.Quest;
import xyz.pixelatedw.wypi.quests.objectives.Objective;

public class DoctorTrial01Quest extends Quest
{
	private Objective objective01 = new EquippedItemObjective("Equip a %s", 1, ModArmors.MEDIC_BAG, EquipmentSlotType.CHEST);
	private Objective objective02 = new ObtainItemObjective("Collect %s nether warts", Items.NETHER_WART, 15).addRequirement(this.objective01);
	private Objective objective03 = new ObtainItemObjective("Collect %s glistering melons", Items.GLISTERING_MELON_SLICE, 15).addRequirement(this.objective01);
	private Objective objective04 = new BrewPotionObjective("Brew %s healing potions", 9, new Effect[] { Effects.INSTANT_HEALTH }).addRequirements(this.objective02, this.objective03);
	
	public DoctorTrial01Quest()
	{
		super("doctor_trial_01", "Trial: First Aid");
		this.addObjectives(this.objective01, this.objective02, this.objective03, this.objective04);
		
		this.onCompleteEvent = this::giveReward;
	}

	public void giveReward(PlayerEntity player)
	{
		IAbilityData props = AbilityDataCapability.get(player);
		
		props.addUnlockedAbility(FirstAidAbility.INSTANCE);
		WyNetwork.sendToServer(new CSyncAbilityDataPacket(props));
	}
}
