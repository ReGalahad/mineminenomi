package xyz.pixelatedw.mineminenomi.api.protection.block;

import net.minecraftforge.registries.ForgeRegistries;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;

public class AllBlockProtectionRule extends BlockProtectionRule
{
	public static final AllBlockProtectionRule INSTANCE = new AllBlockProtectionRule();

	public AllBlockProtectionRule()
	{
		ForgeRegistries.BLOCKS.forEach(block ->
		{
			this.addApprovedBlocks(block);
		});
	}

}