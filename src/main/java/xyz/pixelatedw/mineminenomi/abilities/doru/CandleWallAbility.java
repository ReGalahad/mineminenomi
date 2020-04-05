package xyz.pixelatedw.mineminenomi.abilities.doru;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Direction;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class CandleWallAbility extends Ability
{
	public static final Ability INSTANCE = new CandleWallAbility();

	public CandleWallAbility()
	{
		super("Candle Wall", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(4);
		this.setDescription("Creates a wax wall to protect the user.");
		
		this.onUseEvent = this::onUseEvent;
	}
	
	private boolean onUseEvent(PlayerEntity player)
	{
		Direction dir = Direction.getFacingDirections(player)[0];

		if (dir == Direction.NORTH)
			AbilityHelper.createFilledCube(player.world, player.posX, player.posY, player.posZ - 4, new int[] { 3, 4, 1 }, ModBlocks.WAX, "air", "foliage");
		if (dir == Direction.SOUTH)
			AbilityHelper.createFilledCube(player.world, player.posX, player.posY, player.posZ + 4, new int[] { 3, 4, 1 }, ModBlocks.WAX, "air", "foliage");
		if (dir == Direction.EAST)
			AbilityHelper.createFilledCube(player.world, player.posX + 4, player.posY, player.posZ, new int[] { 1, 4, 3 }, ModBlocks.WAX, "air", "foliage");
		if (dir == Direction.WEST)
			AbilityHelper.createFilledCube(player.world, player.posX - 4, player.posY, player.posZ, new int[] { 1, 4, 3 }, ModBlocks.WAX, "air", "foliage");

		return true;
	}
}
