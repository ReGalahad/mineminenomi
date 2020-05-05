package xyz.pixelatedw.mineminenomi.abilities.yami;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.yami.DarkMatterProjectile;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class DarkMatterAbility extends Ability
{
	public static final DarkMatterAbility INSTANCE = new DarkMatterAbility();

	public DarkMatterAbility()
	{
		super("Dark Matter", AbilityCategory.DEVIL_FRUIT);
		this.setDescription("Launches a ball of darkness that engulfs the opponent.");
		this.setMaxCooldown(10);

		this.onUseEvent = this::onUseEvent;
	}
	
	private boolean onUseEvent(PlayerEntity player)
	{
		DarkMatterProjectile proj = new DarkMatterProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);
		
		return true;
	}
}
