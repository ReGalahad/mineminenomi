package xyz.pixelatedw.mineminenomi.quests.objectives.artofweather;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.TempoAbility;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.quests.objectives.IAbilityUseObjective;
import xyz.pixelatedw.wypi.quests.objectives.Objective;

public class TemopAbilityObjective extends Objective implements IAbilityUseObjective
{
	public TemopAbilityObjective(String title, int count)
	{
		super(title);
		this.setMaxProgress(count);
	}

	@Override
	public boolean checkAbility(PlayerEntity player, Ability ability)
	{
		return ability instanceof TempoAbility;
	}
}
