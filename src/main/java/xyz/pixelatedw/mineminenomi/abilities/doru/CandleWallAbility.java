package xyz.pixelatedw.mineminenomi.abilities.doru;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Direction;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class CandleWallAbility extends Ability
{
	public static final Ability INSTANCE = new CandleWallAbility();

	private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(AirBlockProtectionRule.INSTANCE, FoliageBlockProtectionRule.INSTANCE); 
	
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
			AbilityHelper.createFilledCube(player.world, player.posX, player.posY, player.posZ - 4, 3, 4, 1, ModBlocks.WAX, GRIEF_RULE);
		if (dir == Direction.SOUTH)
			AbilityHelper.createFilledCube(player.world, player.posX, player.posY, player.posZ + 4, 3, 4, 1, ModBlocks.WAX, GRIEF_RULE);
		if (dir == Direction.EAST)
			AbilityHelper.createFilledCube(player.world, player.posX + 4, player.posY, player.posZ, 1, 4, 3, ModBlocks.WAX, GRIEF_RULE);
		if (dir == Direction.WEST)
			AbilityHelper.createFilledCube(player.world, player.posX - 4, player.posY, player.posZ, 1, 4, 3, ModBlocks.WAX, GRIEF_RULE);

		return true;
	}
}
