package xyz.pixelatedw.mineminenomi.api.protection.block;

import net.minecraft.block.Blocks;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;

public class ValuableBlockProtectionRule extends BlockProtectionRule
{
	public static final ValuableBlockProtectionRule INSTANCE = new ValuableBlockProtectionRule();

	public ValuableBlockProtectionRule()
	{
		this.addApprovedBlocks(Blocks.COAL_BLOCK, Blocks.IRON_BLOCK, Blocks.GOLD_BLOCK, Blocks.DIAMOND_BLOCK, Blocks.EMERALD_BLOCK,
			Blocks.REDSTONE_BLOCK, Blocks.LAPIS_BLOCK, Blocks.QUARTZ_BLOCK, Blocks.CHISELED_QUARTZ_BLOCK, Blocks.SMOOTH_QUARTZ,
			Blocks.SMOOTH_QUARTZ_SLAB, Blocks.SMOOTH_QUARTZ_STAIRS, Blocks.BEACON, Blocks.DRAGON_EGG);
	}

}