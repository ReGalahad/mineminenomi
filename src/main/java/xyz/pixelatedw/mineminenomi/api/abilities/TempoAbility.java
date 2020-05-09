package xyz.pixelatedw.mineminenomi.api.abilities;

import java.io.Serializable;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;

public abstract class TempoAbility extends Ability
{
	protected ICanUse canUseCheck = (player, check) -> { return false; };

	public TempoAbility(String name, AbilityCategory category)
	{
		super(name, category);
	}

	public boolean canUseTempo(PlayerEntity player, ICanUse check)
	{
		return check != null ? check.canUse(player, check) : canUseCheck.canUse(player, check);
	}

	/*
	 *	Interfaces
	 */
	public interface ICanUse extends Serializable
	{
		boolean canUse(PlayerEntity player, ICanUse check);
	}
}
