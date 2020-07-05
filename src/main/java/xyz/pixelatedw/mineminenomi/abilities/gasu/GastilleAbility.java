package xyz.pixelatedw.mineminenomi.abilities.gasu;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.gasu.GastilleProjectile;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class GastilleAbility extends Ability
{
	public static final GastilleAbility INSTANCE = new GastilleAbility();

	public GastilleAbility()
	{
		super("Gastille", AbilityCategory.DEVIL_FRUIT);
		this.setDescription("Shoots a beam of condensed poisonous gas from the user's mouth, that explodes on impact.");
		this.setMaxCooldown(7);

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		GastilleProjectile proj = new GastilleProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2.5f, 1);

		return true;
	}
}