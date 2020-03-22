package xyz.pixelatedw.mineminenomi.abilities.goro;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.goro.SangoProjectile;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.RepeaterAbility;

public class SangoAbility extends RepeaterAbility
{
	public static final Ability INSTANCE = new SangoAbility();

	public SangoAbility()
	{
		super("Sango", AbilityCategory.DEVIL_FRUIT);
		this.setDescription("Launches a huge concentrated chunk of electricity at the opponent");
		this.setMaxCooldown(10);
		this.setMaxRepearCount(5, 3);

		this.onUseEvent = this::onUseEvent;
	}
	
	private boolean onUseEvent(PlayerEntity player)
	{
		SangoProjectile proj = new SangoProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);
		
		return true;
	}
}
