package xyz.pixelatedw.mineminenomi.abilities.horo;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.horo.TokuHollowProjectile;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class TokuHollowAbility extends Ability
{
	public static final TokuHollowAbility INSTANCE = new TokuHollowAbility();

	public TokuHollowAbility()
	{
		super("Toku Hollow", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(10);
		this.setDescription("Creates a huge ghost that causes a massive explosion upon impact.");

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		TokuHollowProjectile proj = new TokuHollowProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);

		return true;
	}
}
