package xyz.pixelatedw.wypi.abilities.events;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;
import xyz.pixelatedw.wypi.abilities.Ability;

public class AbilityEvent
{
	@Cancelable
	public static class Use extends Event
	{	
		private PlayerEntity player;
		private Ability ability;
		
		public Use(PlayerEntity player, Ability ability)
		{
			this.player = player;
			this.ability = ability;
		}
		
		public PlayerEntity getPlayer()
		{
			return this.player;
		}
		
		public Ability getAbility()
		{
			return this.ability;
		}
	}
}
