package xyz.pixelatedw.mineminenomi.abilities.suna;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.suna.BarjanProjectile;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class BarjanAbility extends Ability
{
	public static final Ability INSTANCE = new BarjanAbility();

	public BarjanAbility()
	{
		super("Barjan", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(5);
		this.setDescription("Launches a crescent-shaped wave of sand at the opponent, that dehydrates them.");

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		BarjanProjectile proj = new BarjanProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);
		
		return true;
	}
}