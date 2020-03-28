package xyz.pixelatedw.mineminenomi.abilities.rokushiki;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.rokushiki.RankyakuProjectile;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class RankyakuAbility extends Ability
{
	public static final Ability INSTANCE = new RankyakuAbility();

	public RankyakuAbility()
	{
		super("Rankyaku", AbilityCategory.RACIAL);
		this.setMaxCooldown(8);
		this.setDescription("By kicking at a very high speed, the user launches an air blade at the opponent.");

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		RankyakuProjectile proj = new RankyakuProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 4f, 1);
		
		return true;
	}
}