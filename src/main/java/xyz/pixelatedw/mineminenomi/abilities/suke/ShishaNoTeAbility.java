package xyz.pixelatedw.mineminenomi.abilities.suke;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.suke.ShishaNoTeProjectile;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class ShishaNoTeAbility extends Ability
{
	public static final Ability INSTANCE = new ShishaNoTeAbility();

	public ShishaNoTeAbility()
	{
		super("Shisha no Te", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(8);
		this.setDescription("Shoots invisible projectiles that explode upon impact.");

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		ShishaNoTeProjectile proj = new ShishaNoTeProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);

		return true;
	}
}