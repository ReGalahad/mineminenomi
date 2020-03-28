package xyz.pixelatedw.mineminenomi.abilities.rokushiki;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.PunchAbility;

public class ShiganAbility extends PunchAbility
{
	public static final Ability INSTANCE = new ShiganAbility();

	public ShiganAbility()
	{
		super("Shigan", AbilityCategory.RACIAL);
		this.setMaxCooldown(5);
		this.setDescription("The user thrusts their finger at the opponent, to pierce them.");
		
		this.onHitEntity = this::onHitEntity;
	}

	private float onHitEntity(PlayerEntity player, LivingEntity target)
	{
		return 20;
	}

}
