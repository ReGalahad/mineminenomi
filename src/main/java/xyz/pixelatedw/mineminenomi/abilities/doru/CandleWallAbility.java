package xyz.pixelatedw.mineminenomi.abilities.doru;

import net.minecraft.entity.player.PlayerEntity;
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
		/*
		 * if (WyHelper.get4Directions(player) == WyHelper.Direction.NORTH)
		 * WyHelper.createFilledCube(player.world, player.posX, player.posY, player.posZ - 3, new int[]
		 * {
		 * 3, 4, 1
		 * }, ModBlocks.waxBlock, "air", "foliage");
		 * if (WyHelper.get4Directions(player) == WyHelper.Direction.SOUTH)
		 * WyHelper.createFilledCube(player.world, player.posX, player.posY, player.posZ + 3, new int[]
		 * {
		 * 3, 4, 1
		 * }, ModBlocks.waxBlock, "air", "foliage");
		 * if (WyHelper.get4Directions(player) == WyHelper.Direction.EAST)
		 * WyHelper.createFilledCube(player.world, player.posX + 3, player.posY, player.posZ, new int[]
		 * {
		 * 1, 4, 3
		 * }, ModBlocks.waxBlock, "air", "foliage");
		 * if (WyHelper.get4Directions(player) == WyHelper.Direction.WEST)
		 * WyHelper.createFilledCube(player.world, player.posX - 3, player.posY, player.posZ, new int[]
		 * {
		 * 1, 4, 3
		 * }, ModBlocks.waxBlock, "air", "foliage");
		 */
		return true;
	}
}
