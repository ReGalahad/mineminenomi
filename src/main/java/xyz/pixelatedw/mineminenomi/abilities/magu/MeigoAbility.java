package xyz.pixelatedw.mineminenomi.abilities.magu;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.magu.MeigoProjectile;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class MeigoAbility extends Ability
{
	public static final Ability INSTANCE = new MeigoAbility();

	public MeigoAbility()
	{
		super("Meigo", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(10);
		this.setDescription("The user transforms their arm into magma and thrusts it at the opponent.");

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		MeigoProjectile proj = new MeigoProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);
		
		return true;
	}
}