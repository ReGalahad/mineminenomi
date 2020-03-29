package xyz.pixelatedw.mineminenomi.abilities.fishmankarate;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.fishmankarate.MurasameProjectile;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.RepeaterAbility;

public class MurasameAbility extends RepeaterAbility
{
	public static final Ability INSTANCE = new MurasameAbility();

	public MurasameAbility()
	{
		super("Murasame", AbilityCategory.RACIAL);
		this.setDescription("The user fires densely compressed shark-shaped waterbullet at the opponent.");
		this.setMaxCooldown(12);
		this.setMaxRepearCount(3, 5);

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		MurasameProjectile proj = new MurasameProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);

		return true;
	}
}