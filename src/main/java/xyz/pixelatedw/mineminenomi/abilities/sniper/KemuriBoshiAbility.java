package xyz.pixelatedw.mineminenomi.abilities.sniper;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.sniper.KemuriBoshiProjectile;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class KemuriBoshiAbility extends Ability
{
	public static final Ability INSTANCE = new KemuriBoshiAbility();

	public KemuriBoshiAbility()
	{
		super("Kemuri Boshi", AbilityCategory.RACIAL);
		this.setMaxCooldown(6);
		this.setDescription("On impact releases smoke that poisons and confuses targets");

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		KemuriBoshiProjectile proj = new KemuriBoshiProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);

		return true;
	}
}