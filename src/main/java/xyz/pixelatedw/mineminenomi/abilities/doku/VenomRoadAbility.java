package xyz.pixelatedw.mineminenomi.abilities.doku;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.doku.VenomRoadProjectile;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class VenomRoadAbility extends Ability
{
	public static final Ability INSTANCE = new VenomRoadAbility();

	public VenomRoadAbility()
	{
		super("Venom Road", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(30);
		this.setDescription("The user fires a Hydra at the target location and transports there through its path.");

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		VenomRoadProjectile proj = new VenomRoadProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);
		
		return true;
	}
}