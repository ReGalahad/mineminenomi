package xyz.pixelatedw.mineminenomi.abilities.yuki;

import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.LiquidBlockProtectionRule;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;

public class KamakuraAbility extends Ability
{
	public static final KamakuraAbility INSTANCE = new KamakuraAbility();

	private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(AirBlockProtectionRule.INSTANCE, FoliageBlockProtectionRule.INSTANCE, LiquidBlockProtectionRule.INSTANCE);

	public KamakuraAbility()
	{
		super("Kamakura", AbilityCategory.DEVIL_FRUIT);
		this.setDescription("Creates an igloo-like snow barrier around the user.");
		this.setMaxCooldown(5);

		this.onUseEvent = this::onUseEvent;
	}
	
	private boolean onUseEvent(PlayerEntity player)
	{
		RayTraceResult mop = WyHelper.rayTraceBlocks(player);
		World world = player.world;
		if (player.isSneaking())
			AbilityHelper.createEmptySphere(world, (int) player.posX, (int) player.posY, (int) player.posZ, 4, Blocks.SNOW_BLOCK, GRIEF_RULE);
		else if (mop != null)
			AbilityHelper.createEmptySphere(world, (int) mop.getHitVec().x, (int) mop.getHitVec().y, (int) mop.getHitVec().z, 4, Blocks.SNOW_BLOCK, GRIEF_RULE);
	
		return true;
	}
}
