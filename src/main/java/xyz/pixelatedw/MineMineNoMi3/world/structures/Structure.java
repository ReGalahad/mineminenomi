package xyz.pixelatedw.MineMineNoMi3.world.structures;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.api.math.WyMathHelper;

public abstract class Structure
{

	public static void addChestLoot(World world, TileEntityChest te, double rarity, Item loot, int min, int max)
	{
		if (world.rand.nextInt(100) + world.rand.nextDouble() <= rarity)
		{
			int chance = max <= 0 ? min : (int) WyMathHelper.randomWithRange(min, max);
			te.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(loot, chance, 0));		
		}
	}
	
}
