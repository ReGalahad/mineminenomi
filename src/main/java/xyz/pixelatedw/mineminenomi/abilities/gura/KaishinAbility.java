package xyz.pixelatedw.mineminenomi.abilities.gura;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.gura.KaishinProjectile;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class KaishinAbility extends Ability
{
	public static final Ability INSTANCE = new KaishinAbility();

	public KaishinAbility()
	{
		super("Kaishin", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(18);
		this.setDescription("The user cracks the air, which launches a small but powerful shockwave towards the opponent.");

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		KaishinProjectile proj = new KaishinProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);
		
		return true;
	}
}
