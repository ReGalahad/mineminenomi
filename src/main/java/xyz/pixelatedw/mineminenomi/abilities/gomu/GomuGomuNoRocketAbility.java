package xyz.pixelatedw.mineminenomi.abilities.gomu;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.gomu.GomuGomuNoRocketProjectile;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class GomuGomuNoRocketAbility extends Ability
{
	public static final GomuGomuNoRocketAbility INSTANCE = new GomuGomuNoRocketAbility();

	public GomuGomuNoRocketAbility()
	{
		super("Gomu Gomu no Rocket", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(8);

		this.onUseEvent = this::onUseEvent;
	}
	
	private boolean onUseEvent(PlayerEntity player)
	{
		AbilityProjectileEntity projectile = new GomuGomuNoRocketProjectile(player.world, player);
		player.world.addEntity(projectile);
		projectile.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2.5f, 0);
		
		return true;
	}
}
