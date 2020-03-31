package xyz.pixelatedw.mineminenomi.abilities.sniper;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.sniper.SakuretsuSabotenBoshiProjectile;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class SakuretsuSabotenBoshiAbility extends Ability
{
	public static final Ability INSTANCE = new SakuretsuSabotenBoshiAbility();

	public SakuretsuSabotenBoshiAbility()
	{
		super("Sakuretsu Saboten Boshi", AbilityCategory.RACIAL);
		this.setMaxCooldown(10);
		this.setDescription("The fired projectile explodes on impact and creates cacti arond the target, to trap them");

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		SakuretsuSabotenBoshiProjectile proj = new SakuretsuSabotenBoshiProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 2);

		return true;
	}
}