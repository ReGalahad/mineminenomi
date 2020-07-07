package xyz.pixelatedw.mineminenomi.abilities.mera;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.mera.HikenProjectile;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class HikenAbility extends Ability
{
	public static final Ability INSTANCE = new HikenAbility();

	public HikenAbility()
	{
		super("Hiken", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(10);
		this.setDescription("Turns the user's fist into flames and launches it towards the target.");

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		HikenProjectile proj = new HikenProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);
		
		return true;
	}
}