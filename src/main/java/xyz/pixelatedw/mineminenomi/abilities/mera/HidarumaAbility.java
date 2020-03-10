package xyz.pixelatedw.mineminenomi.abilities.mera;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.mera.HidarumaProjectile;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class HidarumaAbility extends Ability
{
	public static final Ability INSTANCE = new HidarumaAbility();

	public HidarumaAbility()
	{
		super("Hidaruma", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(6);
		this.setDescription("Creates small green fireballs that set the target on fire.");

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		HidarumaProjectile proj = new HidarumaProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);

		return true;
	}
}