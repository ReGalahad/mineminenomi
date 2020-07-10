package xyz.pixelatedw.mineminenomi.quests.objectives.artofweather;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.WeatherCloudTempo;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.quests.objectives.IAbilityUseObjective;
import xyz.pixelatedw.wypi.quests.objectives.Objective;

public class WeatherCloudAbilityObjective extends Objective implements IAbilityUseObjective
{

	public WeatherCloudAbilityObjective()
	{
		super("Use Weather Cloud Tempo once");
	}

	@Override
	public boolean checkAbility(PlayerEntity player, Ability ability)
	{
		return ability instanceof WeatherCloudTempo;
	}

}
