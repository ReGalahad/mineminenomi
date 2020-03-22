package xyz.pixelatedw.mineminenomi.abilities.hie;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.hie.IceBlockPartisanProjectile;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.RepeaterAbility;

public class IceBlockPartisanAbility extends RepeaterAbility
{
	public static final Ability INSTANCE = new IceBlockPartisanAbility();

	public IceBlockPartisanAbility()
	{
		super("Ice Block: Partisan", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(7);
		this.setMaxRepearCount(5, 3);
		this.setDescription("Creates several spears of ice that the user hurls at the enemy.");

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		IceBlockPartisanProjectile proj = new IceBlockPartisanProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);

		return true;
	}
}
