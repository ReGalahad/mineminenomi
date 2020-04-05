package xyz.pixelatedw.mineminenomi.abilities.yuki;

import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Direction;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class YukiGakiAbility extends Ability
{
	public static final YukiGakiAbility INSTANCE = new YukiGakiAbility();

	public YukiGakiAbility()
	{
		super("Yuki Gaki", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(12);
		this.setDescription("Creates a wall made of hardened snow to protect the user.");
		
		this.onUseEvent = this::onUseEvent;
	}
	
	private boolean onUseEvent(PlayerEntity player)
	{
		Direction dir = Direction.getFacingDirections(player)[0];

		if (dir == Direction.NORTH)
			AbilityHelper.createFilledCube(player.world, player.posX, player.posY, player.posZ - 4, new int[] { 3, 4, 1 }, Blocks.SNOW_BLOCK, "air", "foliage");
		if (dir == Direction.SOUTH)
			AbilityHelper.createFilledCube(player.world, player.posX, player.posY, player.posZ + 4, new int[] { 3, 4, 1 }, Blocks.SNOW_BLOCK, "air", "foliage");
		if (dir == Direction.EAST)
			AbilityHelper.createFilledCube(player.world, player.posX + 4, player.posY, player.posZ, new int[] { 1, 4, 3 }, Blocks.SNOW_BLOCK, "air", "foliage");
		if (dir == Direction.WEST)
			AbilityHelper.createFilledCube(player.world, player.posX - 4, player.posY, player.posZ, new int[] { 1, 4, 3 }, Blocks.SNOW_BLOCK, "air", "foliage");

		return true;
	}
}
