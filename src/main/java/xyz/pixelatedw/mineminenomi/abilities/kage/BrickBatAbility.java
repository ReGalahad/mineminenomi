package xyz.pixelatedw.mineminenomi.abilities.kage;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.kage.BrickBatProjectile;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.RepeaterAbility;

public class BrickBatAbility extends RepeaterAbility
{
	public static final BrickBatAbility INSTANCE = new BrickBatAbility();

	public BrickBatAbility()
	{
		super("Brick Bat", AbilityCategory.DEVIL_FRUIT);
		this.setDescription("Launches bats created from the user's shadow at the opponent.");
		this.setMaxCooldown(4);
		this.setMaxRepearCount(7, 3);

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		BrickBatProjectile proj = new BrickBatProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1.5f);

		return true;
	}
}