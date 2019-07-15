package xyz.pixelatedw.MineMineNoMi3.world.structures;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.api.math.WyMathHelper;

public abstract class Structure
{
	
	protected static void addChestLoot(World world, TileEntityChest te, double rarity, Item loot, int min, int max)
	{
		addChestLoot(world, te, rarity, loot, 0, min, max);
	}

	protected static void addChestLoot(World world, TileEntityChest te, double rarity, Item loot, int metadata, int min, int max)
	{
		if (getRandomChance(world) <= rarity)
		{
			int chance = max <= 0 ? min : (int) WyMathHelper.randomWithRange(min, max);
			te.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(loot, chance, metadata));		
		}
	}
	
	protected static double getRandomChance(World world)
	{
		return world.rand.nextInt(100) + world.rand.nextDouble();
	}
}
