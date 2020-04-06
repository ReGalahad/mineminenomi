package xyz.pixelatedw.mineminenomi.abilities.yuki;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.yuki.YukiRabiProjectile;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.RepeaterAbility;

public class YukiRabiAbility extends RepeaterAbility
{
	public static final YukiRabiAbility INSTANCE = new YukiRabiAbility();

	public YukiRabiAbility()
	{
		super("Yuki Rabi", AbilityCategory.DEVIL_FRUIT);
		this.setDescription("Launches numerous hardened snowballs, that have the shape of a rabbit's head.");
		this.setMaxCooldown(2);
		this.setMaxRepearCount(6, 3);

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		YukiRabiProjectile proj = new YukiRabiProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 2);

		return true;
	}
}