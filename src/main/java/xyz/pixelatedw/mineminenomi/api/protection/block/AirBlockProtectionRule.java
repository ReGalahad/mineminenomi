package xyz.pixelatedw.mineminenomi.api.protection.block;

import net.minecraft.block.Blocks;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;

public class AirBlockProtectionRule extends BlockProtectionRule
{
	public static final AirBlockProtectionRule INSTANCE = new AirBlockProtectionRule();

	public AirBlockProtectionRule()
	{
		this.addApprovedBlocks(Blocks.AIR);
	}
}
