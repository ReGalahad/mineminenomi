package xyz.pixelatedw.mineminenomi.abilities.nikyu;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.nikyu.PadHoProjectile;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class PadHoAbility extends Ability
{
	public static final Ability INSTANCE = new PadHoAbility();

	public PadHoAbility()
	{
		super("Pad Ho", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(8);
		this.setDescription("Launches a paw-shaped shockwave at the opponent.");

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		PadHoProjectile proj = new PadHoProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);
		
		return true;
	}
}