package xyz.pixelatedw.mineminenomi.abilities.kage;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.kage.BlackBoxProjectile;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class BlackBoxAbility extends Ability
{
	public static final BlackBoxAbility INSTANCE = new BlackBoxAbility();

	public BlackBoxAbility()
	{
		super("Black Box", AbilityCategory.DEVIL_FRUIT);
		this.setDescription("Encases and suffocates the opponent in a box made of shadows.");
		this.setMaxCooldown(8);

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		BlackBoxProjectile proj = new BlackBoxProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 0.5f);

		return true;
	}
}