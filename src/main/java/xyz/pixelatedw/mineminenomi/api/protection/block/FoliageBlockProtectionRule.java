package xyz.pixelatedw.mineminenomi.api.protection.block;

import net.minecraft.block.Blocks;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;

public class FoliageBlockProtectionRule extends BlockProtectionRule
{
	public static final FoliageBlockProtectionRule INSTANCE = new FoliageBlockProtectionRule();

	public FoliageBlockProtectionRule()
	{
		this.addApprovedBlocks(Blocks.ACACIA_LEAVES, Blocks.BIRCH_LEAVES, Blocks.DARK_OAK_LEAVES, Blocks.JUNGLE_LEAVES, Blocks.OAK_LEAVES,
			Blocks.SPRUCE_LEAVES, Blocks.LILY_PAD, Blocks.TALL_GRASS, Blocks.CHORUS_FLOWER, Blocks.LILAC, Blocks.PEONY, Blocks.ROSE_BUSH,
			Blocks.MUSHROOM_STEM, Blocks.BROWN_MUSHROOM, Blocks.BROWN_MUSHROOM_BLOCK, Blocks.RED_MUSHROOM, Blocks.RED_MUSHROOM_BLOCK, Blocks.POTATOES,
			Blocks.CARROTS, Blocks.CACTUS, Blocks.DEAD_BUSH, Blocks.GRASS, Blocks.AZURE_BLUET, Blocks.DANDELION, Blocks.VINE, Blocks.PUMPKIN,
			Blocks.PUMPKIN_STEM, Blocks.MELON, Blocks.MELON_STEM, Blocks.NETHER_WART, Blocks.NETHER_WART_BLOCK, Blocks.CHORUS_FLOWER,
			Blocks.CHORUS_PLANT, Blocks.BRAIN_CORAL, Blocks.BRAIN_CORAL_FAN, Blocks.BRAIN_CORAL_WALL_FAN, Blocks.DEAD_BRAIN_CORAL,
			Blocks.DEAD_BRAIN_CORAL_FAN, Blocks.DEAD_BRAIN_CORAL_WALL_FAN, Blocks.BUBBLE_CORAL, Blocks.BUBBLE_CORAL_FAN, Blocks.BUBBLE_CORAL_WALL_FAN,
			Blocks.DEAD_BUBBLE_CORAL, Blocks.DEAD_BUBBLE_CORAL_FAN, Blocks.DEAD_BUBBLE_CORAL_WALL_FAN, Blocks.FIRE_CORAL, Blocks.FIRE_CORAL_FAN,
			Blocks.FIRE_CORAL_WALL_FAN, Blocks.DEAD_FIRE_CORAL, Blocks.DEAD_FIRE_CORAL_FAN, Blocks.DEAD_FIRE_CORAL_WALL_FAN, Blocks.DEAD_TUBE_CORAL,
			Blocks.DEAD_TUBE_CORAL_FAN, Blocks.DEAD_TUBE_CORAL_WALL_FAN, Blocks.TUBE_CORAL, Blocks.TUBE_CORAL_FAN, Blocks.TUBE_CORAL_WALL_FAN,
			Blocks.DEAD_HORN_CORAL, Blocks.DEAD_HORN_CORAL_FAN, Blocks.DEAD_HORN_CORAL_WALL_FAN, Blocks.HORN_CORAL, Blocks.HORN_CORAL_FAN,
			Blocks.HORN_CORAL_WALL_FAN, Blocks.COCOA, Blocks.SUGAR_CANE, Blocks.BEETROOTS, Blocks.WHEAT, Blocks.FERN, Blocks.LARGE_FERN,
			Blocks.ACACIA_SAPLING, Blocks.BAMBOO_SAPLING, Blocks.BIRCH_SAPLING, Blocks.DARK_OAK_SAPLING, Blocks.JUNGLE_SAPLING, Blocks.OAK_SAPLING,
			Blocks.BAMBOO, Blocks.SNOW);
	}
}
