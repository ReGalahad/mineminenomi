package xyz.pixelatedw.mineminenomi.abilities.nikyu;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.nikyu.PadHoProjectile;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.RepeaterAbility;

public class TsuppariPadHoAbility extends RepeaterAbility
{
	public static final Ability INSTANCE = new TsuppariPadHoAbility();

	public TsuppariPadHoAbility()
	{
		super("Tsuppari Pad Ho", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(13);
		this.setMaxRepearCount(3, 5);
		this.setDescription("Launches a barrage of paw-shaped shockwaves at the opponent.");

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		PadHoProjectile proj = new PadHoProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);
		
		return true;
	}
}