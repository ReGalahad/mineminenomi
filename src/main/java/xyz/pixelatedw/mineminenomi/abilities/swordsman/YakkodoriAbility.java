package xyz.pixelatedw.mineminenomi.abilities.swordsman;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitsHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.swordsman.YakkodoriProjectile;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;

public class YakkodoriAbility extends Ability
{
	public static final Ability INSTANCE = new YakkodoriAbility();

	public YakkodoriAbility()
	{
		super("Yakkodori", AbilityCategory.RACIAL);
		this.setMaxCooldown(5);
		this.setDescription("Launches a crescent moon-shaped slash, which destroys everything in its path");

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		if (!DevilFruitsHelper.canUseSwordsmanAbilities(player))
		{
			WyHelper.sendMsgToPlayer(player, "You need a sword to use this ability !");
			return false;
		}

		YakkodoriProjectile proj = new YakkodoriProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);
		((ServerWorld) player.world).getChunkProvider().sendToTrackingAndSelf(player, new SAnimateHandPacket(player, 0));

		return true;
	}
}