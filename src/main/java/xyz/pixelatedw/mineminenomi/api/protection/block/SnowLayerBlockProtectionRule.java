package xyz.pixelatedw.mineminenomi.api.protection.block;

import net.minecraft.block.Blocks;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;

public class SnowLayerBlockProtectionRule extends BlockProtectionRule
{
	public static final SnowLayerBlockProtectionRule INSTANCE = new SnowLayerBlockProtectionRule();

	public SnowLayerBlockProtectionRule()
	{
		this.addApprovedBlocks(Blocks.SNOW, Blocks.SNOW_BLOCK);
	}
}