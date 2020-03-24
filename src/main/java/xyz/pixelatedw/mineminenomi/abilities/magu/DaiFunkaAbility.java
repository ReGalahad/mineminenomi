package xyz.pixelatedw.mineminenomi.abilities.magu;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.magu.DaiFunkaProjectile;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class DaiFunkaAbility extends Ability
{
	public static final Ability INSTANCE = new DaiFunkaAbility();

	public DaiFunkaAbility()
	{
		super("Dai Funka", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(8);
		this.setDescription("The user covers their fist in lava and fires it at the opponent.");

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		DaiFunkaProjectile proj = new DaiFunkaProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);
		
		return true;
	}
}