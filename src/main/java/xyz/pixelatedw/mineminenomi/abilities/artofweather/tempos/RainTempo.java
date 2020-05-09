package xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.storage.WorldInfo;
import xyz.pixelatedw.mineminenomi.api.abilities.TempoAbility;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;

public class RainTempo extends TempoAbility
{
	public static final RainTempo INSTANCE = new RainTempo();

	public RainTempo()
	{
		super("Rain Tempo", AbilityCategory.RACIAL);
		
		this.onUseEvent = this::onUseEvent;
	}
	
	private boolean onUseEvent(PlayerEntity player)
	{
		WorldInfo worldinfo = player.world.getWorldInfo();
		worldinfo.setRaining(true);
		return true;
	}
}
