package xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.TempoAbility;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;

public class ThunderstormTempo extends TempoAbility
{
	public static final ThunderstormTempo INSTANCE = new ThunderstormTempo();

	public ThunderstormTempo()
	{
		super("Thunderstorm Tempo", AbilityCategory.RACIAL);
		
		this.onUseEvent = this::onUseEvent;
	}
	
	private boolean onUseEvent(PlayerEntity player)
	{
		return true;
	}
}
