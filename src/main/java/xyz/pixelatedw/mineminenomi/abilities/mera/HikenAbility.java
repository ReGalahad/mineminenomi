package xyz.pixelatedw.mineminenomi.abilities.mera;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.entities.projectiles.mera.HikenProjectile;

public class HikenAbility extends Ability
{
	public static final Ability INSTANCE = new HikenAbility();
	
	public HikenAbility()
	{
		super("Hiken", Category.DEVIL_FRUIT);
		this.setMaxCooldown(8);
		this.setDescription("Turns the user's fist into flames and launches it towards the target.");

		this.onUseEvent = this::onUseEvent;
	}
	
	private void onUseEvent(PlayerEntity player, Ability ability)
	{
		HikenProjectile proj = new HikenProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);		
	}
}