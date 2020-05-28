package xyz.pixelatedw.mineminenomi.abilities.gasu;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.gasu.GasRobeProjectile;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.RepeaterAbility;

public class GasRobeAbility extends RepeaterAbility
{
	public static final GasRobeAbility INSTANCE = new GasRobeAbility();

	public GasRobeAbility()
	{
		super("Gas Robe", AbilityCategory.DEVIL_FRUIT);
		this.setDescription("Launches a cloud of poisonous gas at the opponent.");
		this.setMaxCooldown(6);
		this.setMaxRepearCount(5, 3);

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		GasRobeProjectile proj = new GasRobeProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 3f, 2);

		return true;
	}
}