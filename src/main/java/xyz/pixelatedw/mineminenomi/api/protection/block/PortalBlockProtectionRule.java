package xyz.pixelatedw.mineminenomi.api.protection.block;

import net.minecraft.block.Blocks;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;

public class PortalBlockProtectionRule extends BlockProtectionRule
{
	public static final PortalBlockProtectionRule INSTANCE = new PortalBlockProtectionRule();

	public PortalBlockProtectionRule()
	{
		this.addApprovedBlocks(Blocks.END_PORTAL, Blocks.NETHER_PORTAL, Blocks.END_GATEWAY);
	}

}