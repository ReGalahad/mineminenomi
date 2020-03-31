package xyz.pixelatedw.mineminenomi.abilities.sniper;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.sniper.RenpatsuNamariBoshiProjectile;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.RepeaterAbility;

public class RenpatsuNamariBoshiAbility extends RepeaterAbility
{
	public static final Ability INSTANCE = new RenpatsuNamariBoshiAbility();

	public RenpatsuNamariBoshiAbility()
	{
		super("Renpatsu Namari Boshi", AbilityCategory.RACIAL);
		this.setMaxCooldown(6);
		this.setMaxRepearCount(5, 3);
		this.setDescription("Lets the user fire a barrage of exploding shots.");

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		RenpatsuNamariBoshiProjectile proj = new RenpatsuNamariBoshiProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 2);

		return true;
	}
}
