package xyz.pixelatedw.mineminenomi.api.protection.block;

import net.minecraft.block.Blocks;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;

public class OreBlockProtectionRule extends BlockProtectionRule
{
	public static final OreBlockProtectionRule INSTANCE = new OreBlockProtectionRule();

	public OreBlockProtectionRule()
	{
		this.addApprovedBlocks(Blocks.COAL_ORE, Blocks.IRON_ORE, Blocks.GOLD_ORE, Blocks.DIAMOND_ORE, Blocks.EMERALD_ORE, Blocks.REDSTONE_ORE,
			Blocks.LAPIS_ORE, Blocks.NETHER_QUARTZ_ORE);
	}
}