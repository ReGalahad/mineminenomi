package xyz.pixelatedw.mineminenomi.quests.artofweather;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import xyz.pixelatedw.mineminenomi.abilities.artofweather.GustSwordAbility;
import xyz.pixelatedw.mineminenomi.abilities.artofweather.WeatherEggAbility;
import xyz.pixelatedw.mineminenomi.init.ModQuests;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;
import xyz.pixelatedw.mineminenomi.quests.objectives.ObtainItemObjective;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.wypi.network.WyNetwork;
import xyz.pixelatedw.wypi.network.packets.client.CSyncAbilityDataPacket;
import xyz.pixelatedw.wypi.quests.Quest;
import xyz.pixelatedw.wypi.quests.objectives.Objective;

public class ArtOfWeatherTrial04Quest extends Quest
{
	private Objective objective01 = new ObtainItemObjective("Obtain a Perfect Clima Tact", ModWeapons.PERFECT_CLIMA_TACT, 1);
	// Kill  5 enemies with a Thunderstorm Tempo and at least 1 with Thunder Lance Tempo during the same Thunderstorm Tempo 
	
	public ArtOfWeatherTrial04Quest()
	{
		super("art_of_weather_trial_04", "Trial: Sorcery Clima Tact");
		this.addObjectives(this.objective01);
		this.addRequirement(ModQuests.ART_OF_WEATHER_TRIAL_03);
		
		this.onCompleteEvent = this::giveReward;
	}
	
	public void giveReward(PlayerEntity player)
	{
		IAbilityData props = AbilityDataCapability.get(player);
		
		player.inventory.addItemStackToInventory(new ItemStack(ModWeapons.SORCERY_CLIMA_TACT));
		
		props.addUnlockedAbility(GustSwordAbility.INSTANCE);
		props.addUnlockedAbility(WeatherEggAbility.INSTANCE);
		WyNetwork.sendToServer(new CSyncAbilityDataPacket(props));
	}
}