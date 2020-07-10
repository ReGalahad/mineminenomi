package xyz.pixelatedw.mineminenomi.quests.artofweather;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.abilities.artofweather.CoolBallAbility;
import xyz.pixelatedw.mineminenomi.abilities.artofweather.HeatBallAbility;
import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.WeatherCloudTempo;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;
import xyz.pixelatedw.mineminenomi.quests.objectives.ObtainItemObjective;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.wypi.network.WyNetwork;
import xyz.pixelatedw.wypi.network.packets.client.CSyncAbilityDataPacket;
import xyz.pixelatedw.wypi.quests.Quest;
import xyz.pixelatedw.wypi.quests.objectives.Objective;

public class ArtOfWeatherTrial01Quest extends Quest
{
	private Objective objective01 = new ObtainItemObjective("Obtain a Clima Tact", ModWeapons.CLIMA_TACT, 1);

	public ArtOfWeatherTrial01Quest()
	{
		super("art_of_weather_trial_01", "Trial: Heat Ball & Cool Ball");
		this.addObjectives(this.objective01);
		
		this.onCompleteEvent = this::giveReward;
	}

	public void giveReward(PlayerEntity player)
	{
		IAbilityData props = AbilityDataCapability.get(player);
		
		props.addUnlockedAbility(HeatBallAbility.INSTANCE);
		props.addUnlockedAbility(CoolBallAbility.INSTANCE);
		props.addUnlockedAbility(WeatherCloudTempo.INSTANCE);
		WyNetwork.sendToServer(new CSyncAbilityDataPacket(props));
	}
}