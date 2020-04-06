package xyz.pixelatedw.mineminenomi.abilities.horo;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.horo.MiniHollowProjectile;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.RepeaterAbility;

public class MiniHollowAbility extends RepeaterAbility
{
	public static final MiniHollowAbility INSTANCE = new MiniHollowAbility();

	public MiniHollowAbility()
	{
		super("Mini Hollow", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(2);
		this.setMaxRepearCount(5, 3);
		this.setDescription("Launches small ghosts at the opponent, exploding upon impact.");

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		MiniHollowProjectile proj = new MiniHollowProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 3);

		return true;
	}
}
