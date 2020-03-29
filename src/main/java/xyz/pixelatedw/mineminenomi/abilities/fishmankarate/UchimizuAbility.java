package xyz.pixelatedw.mineminenomi.abilities.fishmankarate;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.fishmankarate.UchimizuProjectile;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.RepeaterAbility;

public class UchimizuAbility extends RepeaterAbility
{
	public static final Ability INSTANCE = new UchimizuAbility();

	public UchimizuAbility()
	{
		super("Uchimizu", AbilityCategory.RACIAL);
		this.setDescription("The user hurls big and fast water droplets at the opponent.");
		this.setMaxCooldown(4);
		this.setMaxRepearCount(7, 2);

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		UchimizuProjectile proj = new UchimizuProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 3f, 1.2F);

		return true;
	}
}