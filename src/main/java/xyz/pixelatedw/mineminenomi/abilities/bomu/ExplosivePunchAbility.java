package xyz.pixelatedw.mineminenomi.abilities.bomu;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.bomu.ExplosivePunchProjectile;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class ExplosivePunchAbility extends Ability{

	public static final Ability INSTANCE = new ExplosivePunchAbility();
	public ExplosivePunchAbility() {
		super("Explosive Punch", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(3);
		this.setDescription("User punches and creates an explosion around his fist");
		// TODO Auto-generated constructor stub
		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player) {
		
		ExplosivePunchProjectile proj = new ExplosivePunchProjectile(player.world, player);
	      player.world.addEntity(proj);
	      proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);
		return true;

	}
}
