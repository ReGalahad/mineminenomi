package xyz.pixelatedw.wypi.abilities.events;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;
import xyz.pixelatedw.wypi.abilities.Ability;

@Cancelable
public class AbilityUseEvent extends Event
{
	private Ability ability;
	private PlayerEntity user;

	public AbilityUseEvent(Ability ability, PlayerEntity user)
	{
		this.ability = ability;
		this.user = user;
	}

	public Ability getAbility()
	{
		return this.ability;
	}

	public PlayerEntity getUser()
	{
		return this.user;
	}
}
