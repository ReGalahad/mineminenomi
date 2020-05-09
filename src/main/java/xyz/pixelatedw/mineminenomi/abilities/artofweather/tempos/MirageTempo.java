package xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.TempoAbility;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;

public class MirageTempo extends TempoAbility
{
	public static final MirageTempo INSTANCE = new MirageTempo();

	public MirageTempo()
	{
		super("Mirage Tempo", AbilityCategory.RACIAL);
		
		this.onUseEvent = this::onUseEvent;
	}
	
	private boolean onUseEvent(PlayerEntity player)
	{
		return true;
	}
}
