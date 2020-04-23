package xyz.pixelatedw.mineminenomi.abilities.ito;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.ito.OverheatProjectile;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.RepeaterAbility;

public class OverheatAbility extends RepeaterAbility
{
	public static final OverheatAbility INSTANCE = new OverheatAbility();

	public OverheatAbility()
	{
		super("Overheat", AbilityCategory.DEVIL_FRUIT);
		this.setDescription("The user shoots a rope made of heated strings at the opponent, exploding upon impact.");
		this.setMaxCooldown(12);
		this.setMaxRepearCount(4, 2);

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		OverheatProjectile proj = new OverheatProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2.5f, 0);

		return true;
	}
}