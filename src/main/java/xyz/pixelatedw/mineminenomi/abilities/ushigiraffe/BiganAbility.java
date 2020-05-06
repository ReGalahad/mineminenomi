package xyz.pixelatedw.mineminenomi.abilities.ushigiraffe;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.ushigiraffe.BiganProjectile;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class BiganAbility extends Ability
{
	public static final BiganAbility INSTANCE = new BiganAbility();

	public BiganAbility()
	{
		super("Bigan", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(5);
		
		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		BiganProjectile proj = new BiganProjectile(player.world, player);
		proj.setLocationAndAngles(player.posX, player.posY + 3, player.posZ, 0, 0);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 0);
		
		return true;
	}
}
