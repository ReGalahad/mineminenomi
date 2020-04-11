package xyz.pixelatedw.mineminenomi.abilities.mero;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.mero.PistolKissProjectile;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class PistolKissAbility extends Ability
{
	public static final PistolKissAbility INSTANCE = new PistolKissAbility();

	public PistolKissAbility()
	{
		super("Pistol Kiss", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(5);
		this.setDescription("Weaker but faster variant of 'Mero Mero Mellow'.");

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		PistolKissProjectile proj = new PistolKissProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 3.5f, 1);

		return true;
	}
}