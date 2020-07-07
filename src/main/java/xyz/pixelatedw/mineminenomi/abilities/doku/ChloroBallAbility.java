package xyz.pixelatedw.mineminenomi.abilities.doku;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.doku.ChloroBallProjectile;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class ChloroBallAbility extends Ability
{
	public static final Ability INSTANCE = new ChloroBallAbility();

	public ChloroBallAbility()
	{
		super("Chloro Ball", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(7);
		this.setDescription("The user spits a bubble made of poison towards the enemy, which leaves poison on the ground.");

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		ChloroBallProjectile proj = new ChloroBallProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);
		
		return true;
	}
}