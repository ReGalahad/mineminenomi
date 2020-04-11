package xyz.pixelatedw.mineminenomi.abilities.horo;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.horo.NegativeHollowProjectile;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class NegativeHollowAbility extends Ability
{
	public static final NegativeHollowAbility INSTANCE = new NegativeHollowAbility();

	public NegativeHollowAbility()
	{
		super("Negative Hollow", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(4);
		this.setDescription("The user launches a ghost that drains the target's will.");

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		NegativeHollowProjectile proj = new NegativeHollowProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);

		return true;
	}
}
