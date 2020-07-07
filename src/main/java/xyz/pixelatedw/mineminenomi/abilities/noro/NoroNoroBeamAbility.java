package xyz.pixelatedw.mineminenomi.abilities.noro;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.noro.NoroNoroBeamProjectile;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class NoroNoroBeamAbility extends Ability
{
	public static final Ability INSTANCE = new NoroNoroBeamAbility();

	public NoroNoroBeamAbility()
	{
		super("Noro Noro Beam", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(4);
		this.setDescription("Shoots a beam of photons at the opponent, completely slowing them down");

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		NoroNoroBeamProjectile proj = new NoroNoroBeamProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);

		return true;
	}
}