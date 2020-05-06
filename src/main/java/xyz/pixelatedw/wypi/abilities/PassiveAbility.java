package xyz.pixelatedw.wypi.abilities;

import java.io.Serializable;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;

public abstract class PassiveAbility extends Ability
{	
	// Setting the defaults so that no crash occurs and so they will be null safe.
	protected IDuringPassive duringPassiveEvent = (player) -> { };
	
	public PassiveAbility(String name, AbilityCategory category)
	{
		super(name, category);
	}

	@Override
	public void use(PlayerEntity player) {}
	
	public void tick(PlayerEntity user)
	{
		this.duringPassiveEvent.duringPassive(user);
	}
	
	public interface IDuringPassive extends Serializable
	{
		void duringPassive(PlayerEntity player);
	}
}
