package xyz.pixelatedw.mineminenomi.abilities.ope;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitsHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.ope.GammaKnifeProjectile;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;

public class GammaKnifeAbility extends Ability
{
	public static final Ability INSTANCE = new GammaKnifeAbility();

	public GammaKnifeAbility()
	{
		super("Gamma Knife", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(30);
		this.setDescription("Creates a blade of gamma radiation which massively damages the opponent's organs");

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		if (!DevilFruitsHelper.isEntityInRoom(player))
		{
			WyHelper.sendMsgToPlayer(player, "" + this.getName() + " can only be used inside ROOM !");
			return false;
		}

		GammaKnifeProjectile proj = new GammaKnifeProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);

		return true;
	}
}