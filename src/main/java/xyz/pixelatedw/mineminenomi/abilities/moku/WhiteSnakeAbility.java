package xyz.pixelatedw.mineminenomi.abilities.moku;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.moku.WhiteSnakeProjectile;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class WhiteSnakeAbility extends Ability
{
	public static final Ability INSTANCE = new WhiteSnakeAbility();

	public WhiteSnakeAbility()
	{
		super("White Snake", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(10);
		this.setDescription("Launches a long dense smoke cloud in the shape of a snake that will damage and give poison its target.");

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		WhiteSnakeProjectile proj = new WhiteSnakeProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);
		
		return true;
	}
}
