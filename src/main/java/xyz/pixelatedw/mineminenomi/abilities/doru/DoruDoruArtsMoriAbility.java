package xyz.pixelatedw.mineminenomi.abilities.doru;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.doru.DoruDoruArtsMoriProjectile;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class DoruDoruArtsMoriAbility extends Ability
{
	public static final Ability INSTANCE = new DoruDoruArtsMoriAbility();

	public DoruDoruArtsMoriAbility()
	{
		super("Doru Doru Arts: Mori", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(5);
		this.setDescription("The user fires a harpoon made of wax at the opponent.");

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		DoruDoruArtsMoriProjectile proj = new DoruDoruArtsMoriProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);

		return true;
	}
}
