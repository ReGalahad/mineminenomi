package xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.TempoAbility;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;

public class WeatherCloudTempo extends TempoAbility
{
	public static final WeatherCloudTempo INSTANCE = new WeatherCloudTempo();

	public WeatherCloudTempo()
	{
		super("Weather Cloud Tempo", AbilityCategory.RACIAL);
		
		this.onUseEvent = this::onUseEvent;
	}
	
	private boolean onUseEvent(PlayerEntity player)
	{
		return true;
	}
}
