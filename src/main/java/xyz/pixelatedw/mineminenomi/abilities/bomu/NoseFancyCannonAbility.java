package xyz.pixelatedw.mineminenomi.abilities.bomu;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.bomu.NoseFancyCannonProjectile;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class NoseFancyCannonAbility extends Ability
{
	public static final Ability INSTANCE = new NoseFancyCannonAbility();

	public NoseFancyCannonAbility()
	{
		super("Nose Fancy Cannon", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(4);
		this.setDescription("Shoots dried mucus at the opponent, which explodes on impact.");

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		NoseFancyCannonProjectile proj = new NoseFancyCannonProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);
		
		return true;
	}
}