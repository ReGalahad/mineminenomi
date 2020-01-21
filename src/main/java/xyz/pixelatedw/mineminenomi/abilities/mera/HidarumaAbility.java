package xyz.pixelatedw.mineminenomi.abilities.mera;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.data.ability.AbilityDataCapability.Category;
import xyz.pixelatedw.mineminenomi.entities.projectiles.mera.HidarumaProjectile;

public class HidarumaAbility extends Ability
{
	public static final Ability INSTANCE = new HidarumaAbility();
	
	public HidarumaAbility()
	{
		super("Hidaruma", Category.DEVIL_FRUIT);
		this.setMaxCooldown(6);
		this.setDescription("Creates small green fireballs that set the target on fire.");

		this.onUseEvent = this::onUseEvent;
	}
	
	private void onUseEvent(PlayerEntity player, Ability ability)
	{
		HidarumaProjectile proj = new HidarumaProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);		
	}
}