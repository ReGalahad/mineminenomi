package xyz.pixelatedw.mineminenomi.api.abilities;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.data.ability.AbilityDataCapability.Category;

public abstract class PassiveAbility extends Ability
{
	
	public PassiveAbility(String name, Category category)
	{
		super(name, category);
	}

	@Override
	public void use(PlayerEntity player) {}
	
	public abstract void tick(PlayerEntity user);
}
