package xyz.pixelatedw.mineminenomi.abilities.mero;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.mero.MeroMeroMellowProjectile;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class MeroMeroMellowAbility extends Ability
{
	public static final MeroMeroMellowAbility INSTANCE = new MeroMeroMellowAbility();

	public MeroMeroMellowAbility()
	{
		super("Mero Mero Mellow", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(15);
		this.setDescription("Fires a heart-shaped beam, turning every enemy in its path into stone.");

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		MeroMeroMellowProjectile proj = new MeroMeroMellowProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);

		return true;
	}
}