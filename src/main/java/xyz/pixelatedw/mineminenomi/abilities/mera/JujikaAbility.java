package xyz.pixelatedw.mineminenomi.abilities.mera;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.mera.JujikaProjectile;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class JujikaAbility extends Ability
{
	public static final Ability INSTANCE = new JujikaAbility();

	public JujikaAbility()
	{
		super("Jujika", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(6);
		this.setDescription("Launches a cross-shaped column of fire at the opponent, leaving a cross of fire.");

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		JujikaProjectile proj = new JujikaProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);

		return true;
	}
}
