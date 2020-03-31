package xyz.pixelatedw.mineminenomi.abilities.doru;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.doru.CandleLockProjectile;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class CandleLockAbility extends Ability
{
	public static final Ability INSTANCE = new CandleLockAbility();

	public CandleLockAbility()
	{
		super("Candle Lock", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(12);
		this.setDescription("Traps the opponent's feet in hardened wax, which makes them unable to move.");

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		CandleLockProjectile proj = new CandleLockProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 3f, 1);

		return true;
	}
}
