package xyz.pixelatedw.mineminenomi.abilities.doku;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.doku.DokuFuguProjectile;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.RepeaterAbility;

public class DokuFuguAbility extends RepeaterAbility
{
	public static final Ability INSTANCE = new DokuFuguAbility();

	public DokuFuguAbility()
	{
		super("Doku Fugu", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(6);
		this.setMaxRepearCount(3, 4);
		this.setDescription("Shoots multiple poisonous bullets at the opponent.");

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		DokuFuguProjectile proj = new DokuFuguProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 4);
		
		return true;
	}
}