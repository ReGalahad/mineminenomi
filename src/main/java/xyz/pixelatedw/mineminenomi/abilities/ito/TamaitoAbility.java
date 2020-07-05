package xyz.pixelatedw.mineminenomi.abilities.ito;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.ito.TamaitoProjectile;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class TamaitoAbility extends Ability
{
	public static final TamaitoAbility INSTANCE = new TamaitoAbility();

	public TamaitoAbility()
	{
		super("Tamaito", AbilityCategory.DEVIL_FRUIT);
		this.setDescription("The user shoots a small bundle of strings, acting like a bullet.");
		this.setMaxCooldown(2);

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		TamaitoProjectile proj = new TamaitoProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1.2F);

		return true;
	}
}