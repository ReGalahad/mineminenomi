package xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.TempoAbility;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;

public class FogTempo extends TempoAbility
{
	public static final FogTempo INSTANCE = new FogTempo();

	public FogTempo()
	{
		super("Fog Tempo", AbilityCategory.RACIAL);
		
		this.onUseEvent = this::onUseEvent;
	}
	
	private boolean onUseEvent(PlayerEntity player)
	{
		return true;
	}
}
