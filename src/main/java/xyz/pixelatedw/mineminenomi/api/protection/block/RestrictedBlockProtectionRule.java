package xyz.pixelatedw.mineminenomi.api.protection.block;

import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;

public class RestrictedBlockProtectionRule extends BlockProtectionRule
{
	public static final RestrictedBlockProtectionRule INSTANCE = new RestrictedBlockProtectionRule();

	public RestrictedBlockProtectionRule()
	{
		this.addBannedBlocks(ItemsHelper.RESTRICTED_BLOCKS);
	}

}