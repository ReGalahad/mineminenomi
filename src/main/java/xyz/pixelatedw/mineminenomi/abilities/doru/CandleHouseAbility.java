package xyz.pixelatedw.mineminenomi.abilities.doru;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.CoreBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class CandleHouseAbility extends Ability
{
	public static final Ability INSTANCE = new CandleHouseAbility();
	private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(CoreBlockProtectionRule.INSTANCE, FoliageBlockProtectionRule.INSTANCE); 

	public CandleHouseAbility()
	{
		super("Candle House", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(60);
		this.setDescription("Creates a big house-like structure made of wax, to protect the user.");
		
		this.onUseEvent = this::onUseEvent;
	}
	
	private boolean onUseEvent(PlayerEntity player)
	{
		for (int y = 0; y <= 3; y++)
		{
			for (int x = 0; x < 1; x++)
				for (int z = -5; z < 5; z++)
					AbilityHelper.placeBlockIfAllowed(player.world, (player.posX + 6) - x, player.posY + y, player.posZ - z, ModBlocks.WAX, GRIEF_RULE);
			for (int x = 0; x < 1; x++)
				for (int z = -5; z < 5; z++)
					AbilityHelper.placeBlockIfAllowed(player.world, (player.posX - 5) - x, player.posY + y, player.posZ - z, ModBlocks.WAX, GRIEF_RULE);
			for (int x = -5; x < 5; x++)
				for (int z = 0; z < 1; z++)
					AbilityHelper.placeBlockIfAllowed(player.world, player.posX - x, player.posY + y, (player.posZ + 6) - z, ModBlocks.WAX, GRIEF_RULE);
			for (int x = -5; x < 5; x++)
				for (int z = 0; z < 1; z++)
					AbilityHelper.placeBlockIfAllowed(player.world, player.posX - x, player.posY + y, (player.posZ - 5) - z, ModBlocks.WAX, GRIEF_RULE);
		}
		for (int x = -5; x < 5; x++)
			for (int z = -5; z < 5; z++)
				AbilityHelper.placeBlockIfAllowed(player.world, player.posX - x, (player.posY + 4), player.posZ - z, ModBlocks.WAX, GRIEF_RULE);
		
		return true;
	}
}
