package xyz.pixelatedw.mineminenomi.abilities.hie;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.entities.projectiles.hie.IceBallProjectile;

public class IceBallAbility extends Ability
{
	public static final Ability INSTANCE = new IceBallAbility();
	
	public IceBallAbility()
	{
		super("Ice Ball", Category.DEVIL_FRUIT);
		this.setMaxCooldown(6);
		this.setDescription("Creates a sphere of ice where the projectile hits.");

		this.onUseEvent = this::onUseEvent;
	}
	
	private void onUseEvent(PlayerEntity player, Ability ability)
	{
		IceBallProjectile proj = new IceBallProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);		
	}
}
