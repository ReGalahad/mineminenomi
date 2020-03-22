package xyz.pixelatedw.mineminenomi.abilities.ope;

import java.util.List;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitsHelper;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;

public class ShamblesAbility extends Ability
{
	public static final Ability INSTANCE = new ShamblesAbility();

	public ShamblesAbility()
	{
		super("Shambles", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(8);
		this.setDescription("The user swaps place with the closest entity within the ROOM");

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		if (!DevilFruitsHelper.isEntityInRoom(player))
		{
			WyHelper.sendMsgToPlayer(player, "" + this.getName() + " can only be used inside ROOM !");
			return false;
		}

		List<LivingEntity> list = WyHelper.<LivingEntity>getEntitiesNear(player.getPosition(), player.world, 4);
		list.remove(player);
		if (list.size() <= 0)
			return true;

		LivingEntity target = list.get(0);

		double[] beforeCoords = new double[]
			{
					player.posX, player.posY, player.posZ
			};

		player.setPositionAndRotation(target.posX, target.posY, target.posZ, target.rotationYaw, target.rotationPitch);
		player.setPositionAndUpdate(target.posX, target.posY, target.posZ);
		target.setPositionAndUpdate(beforeCoords[0], beforeCoords[1], beforeCoords[2]);

		return true;

	}
}
