package xyz.pixelatedw.mineminenomi.abilities.bari;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.bari.BarrierCrashProjectile;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class BarrierCrashAbility extends Ability
{
	public static final BarrierCrashAbility INSTANCE = new BarrierCrashAbility();

	public BarrierCrashAbility()
	{
		super("Barrier Crash", AbilityCategory.DEVIL_FRUIT);
		this.setDescription("Launches a barrier towards the opponent, smashing it against them.");
		
		this.setMaxCooldown(8);
		
		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{	
		BarrierCrashProjectile proj = new BarrierCrashProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);

		return true;
	}
}