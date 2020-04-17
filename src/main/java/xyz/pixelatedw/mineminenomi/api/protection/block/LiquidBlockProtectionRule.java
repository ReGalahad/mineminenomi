package xyz.pixelatedw.mineminenomi.api.protection.block;

import net.minecraft.block.Blocks;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;

public class LiquidBlockProtectionRule extends BlockProtectionRule
{
	public static final LiquidBlockProtectionRule INSTANCE = new LiquidBlockProtectionRule();

	public LiquidBlockProtectionRule()
	{
		this.addApprovedBlocks(Blocks.WATER, Blocks.LAVA);
	}

}