package xyz.pixelatedw.mineminenomi.abilities.moku;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.moku.WhiteOutProjectile;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class WhiteOutAbility extends Ability
{
	public static final Ability INSTANCE = new WhiteOutAbility();

	public WhiteOutAbility()
	{
		super("White Out", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(4);
		this.setDescription("Shoots clouds of smoke to engulf the opponent and trap them, dragging them towards the user.");

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		WhiteOutProjectile proj = new WhiteOutProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);
		
		return true;
	}
}