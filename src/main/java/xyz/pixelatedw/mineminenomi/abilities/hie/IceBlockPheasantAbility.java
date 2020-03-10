package xyz.pixelatedw.mineminenomi.abilities.hie;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.hie.IceBlockPheasantProjectile;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class IceBlockPheasantAbility extends Ability
{
	public static final Ability INSTANCE = new IceBlockPheasantAbility();

	public IceBlockPheasantAbility()
	{
		super("Ice Block: Pheasant", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(20);
		this.setDescription("Releases a massive wave of ice in the shape of a pheasant.");

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		IceBlockPheasantProjectile proj = new IceBlockPheasantProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);

		return true;
	}
}
