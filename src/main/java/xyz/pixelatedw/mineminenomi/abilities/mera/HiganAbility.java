package xyz.pixelatedw.mineminenomi.abilities.mera;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.mera.HiganProjectile;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.RepeaterAbility;

public class HiganAbility extends RepeaterAbility
{
	public static final Ability INSTANCE = new HiganAbility();

	public HiganAbility()
	{
		super("Higan", AbilityCategory.DEVIL_FRUIT);
		this.setDescription("Turns the user's fingertips into flames and shoots bullets made of fire from them.");
		this.setMaxCooldown(4);
		this.setMaxRepearCount(5, 2);

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		HiganProjectile proj = new HiganProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);

		return true;
	}
}