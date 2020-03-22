package xyz.pixelatedw.mineminenomi.abilities.suna;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.suna.DesertSpadaProjectile;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class DesertSpadaAbility extends Ability
{
	public static final Ability INSTANCE = new DesertSpadaAbility();
		
	public DesertSpadaAbility()
	{
		super("Desert Spada", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(10);
		this.setDescription("The user extends their sand along the ground, erroding and turning into sand everything it its path.");

		this.onUseEvent = this::onUseEvent;
	}
	
	private boolean onUseEvent(PlayerEntity player)
	{
		DesertSpadaProjectile proj = new DesertSpadaProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);
		
		return true;
	}
}
