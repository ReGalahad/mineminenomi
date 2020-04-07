package xyz.pixelatedw.mineminenomi.abilities.supa;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.supa.SpiralHollowProjectile;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class SpiralHollowAbility extends Ability
{
	public static final SpiralHollowAbility INSTANCE = new SpiralHollowAbility();

	public SpiralHollowAbility()
	{
		super("Spiral Hollow", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(10);
		this.setDescription("Creates circular blades along the user's forearms slicing enemies in a close line.");

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		SpiralHollowProjectile proj = new SpiralHollowProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);
		
		return true;
	}
}