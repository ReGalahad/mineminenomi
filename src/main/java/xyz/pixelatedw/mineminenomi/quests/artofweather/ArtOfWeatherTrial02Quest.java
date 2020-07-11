package xyz.pixelatedw.mineminenomi.quests.artofweather;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.abilities.artofweather.ThunderBallAbility;
import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.FogTempo;
import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.MirageTempo;
import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.RainTempo;
import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.ThunderLanceTempo;
import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.ThunderboltTempo;
import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.ThunderstormTempo;
import xyz.pixelatedw.mineminenomi.init.ModQuests;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;
import xyz.pixelatedw.mineminenomi.quests.objectives.ObtainItemObjective;
import xyz.pixelatedw.mineminenomi.quests.objectives.artofweather.WeatherCloudAbilityObjective;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.WyRegistry;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.wypi.network.WyNetwork;
import xyz.pixelatedw.wypi.network.packets.client.CSyncAbilityDataPacket;
import xyz.pixelatedw.wypi.quests.Quest;
import xyz.pixelatedw.wypi.quests.objectives.Objective;

public class ArtOfWeatherTrial02Quest extends Quest
{
	private Objective objective01 = new ObtainItemObjective("Obtain a Clima Tact", ModWeapons.CLIMA_TACT, 1);
	private Objective objective02 = new WeatherCloudAbilityObjective().addRequirement(this.objective01);
	
	private static final String TIP = WyRegistry.registerName("quest.art_of_weather_trial_02.tip", "<Weather Wizard> In order to use Weather Cloud Tempo a Heat Ball and Cool Ball must collide");

	public ArtOfWeatherTrial02Quest()
	{
		super("art_of_weather_trial_02", "Trial: Thunder Ball");
		this.addObjectives(this.objective01, this.objective02);
		this.addRequirement(ModQuests.ART_OF_WEATHER_TRIAL_01);
		
		this.onStartEvent = this::startQuest;
		this.onCompleteEvent = this::giveReward;
	}

	public void startQuest(PlayerEntity player)
	{
		WyHelper.sendMsgToPlayer(player, new TranslationTextComponent(TIP).getFormattedText());
	}
	
	public void giveReward(PlayerEntity player)
	{
		IAbilityData props = AbilityDataCapability.get(player);
		
		props.addUnlockedAbility(ThunderBallAbility.INSTANCE);
		props.addUnlockedAbility(ThunderboltTempo.INSTANCE);
		props.addUnlockedAbility(ThunderLanceTempo.INSTANCE);
		props.addUnlockedAbility(ThunderstormTempo.INSTANCE);
		props.addUnlockedAbility(RainTempo.INSTANCE);
		props.addUnlockedAbility(MirageTempo.INSTANCE);
		props.addUnlockedAbility(FogTempo.INSTANCE);
		WyNetwork.sendToServer(new CSyncAbilityDataPacket(props));
	}
}