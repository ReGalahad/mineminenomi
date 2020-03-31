package xyz.pixelatedw.mineminenomi.abilities.sniper;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.sniper.KaenBoshiProjectile;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class KaenBoshiAbility extends Ability
{
	public static final Ability INSTANCE = new KaenBoshiAbility();

	public KaenBoshiAbility()
	{
		super("Kaen Boshi", AbilityCategory.RACIAL);
		this.setMaxCooldown(4);
		this.setDescription("Fires a flaming pellet, that sets the target on fire");

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		KaenBoshiProjectile proj = new KaenBoshiProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);

		return true;
	}
}