package xyz.pixelatedw.MineMineNoMi3.world.structures;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.api.Schematic;
import xyz.pixelatedw.MineMineNoMi3.api.WySchematicHelper;
import xyz.pixelatedw.MineMineNoMi3.api.math.WyMathHelper;
import xyz.pixelatedw.MineMineNoMi3.blocks.tileentities.TileEntityCustomSpawner;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;
import xyz.pixelatedw.MineMineNoMi3.world.MainWorldGen;

public class StructureSmallShip
{

	public static boolean build(Schematic sch, World world, int posX, int posY, int posZ, BiomeGenBase biome)
	{
		boolean flagBiome = (biome != BiomeGenBase.ocean && biome != BiomeGenBase.deepOcean);
		boolean flagSpecialCheck = !MainWorldGen.checkForWaterSpawn(sch, world, posX, posY, posZ);
		
		if(flagBiome || flagSpecialCheck)
			return false;
		
		WySchematicHelper.build(sch, world, posX, posY, posZ);
		populate(posX, posY, posZ, world, sch.getName());

		return true;
	}

	private static void populate(int posX, int posY, int posZ, World world, String faction)
	{
		String toSpawn1;
		String toSpawn2;
		Item swordToSpawn;
		if (faction.equals("marineShip"))
		{
			toSpawn1 = ID.PROJECT_ID + ".Marine with Sword";
			toSpawn2 = ID.PROJECT_ID + ".Marine with Gun";
			swordToSpawn = ListMisc.MarineSword;
		}
		else
		{
			toSpawn1 = ID.PROJECT_ID + ".Pirate with Sword";
			toSpawn2 = ID.PROJECT_ID + ".Pirate with Gun";
			swordToSpawn = ListMisc.PirateCutlass;
		}

		TileEntityCustomSpawner spw1 = new TileEntityCustomSpawner().setSpawnerMob(toSpawn2).setSpawnerLimit(5);
		TileEntityCustomSpawner spw2 = new TileEntityCustomSpawner().setSpawnerMob(toSpawn1).setSpawnerLimit(5);
		TileEntityCustomSpawner spw3 = new TileEntityCustomSpawner().setSpawnerMob(toSpawn1).setSpawnerLimit(2);

		world.setBlock(posX + 10, posY + 2, posZ + 32, ListMisc.CustomSpawner);
		world.setTileEntity(posX + 10, posY + 2, posZ + 32, spw1);
		world.setBlock(posX + 10, posY + 2, posZ + 43, ListMisc.CustomSpawner);
		world.setTileEntity(posX + 10, posY + 2, posZ + 43, spw2);
		world.setBlock(posX + 10, posY + 7, posZ + 45, ListMisc.CustomSpawner);
		world.setTileEntity(posX + 10, posY + 7, posZ + 45, spw3);

		world.setBlock(posX + 12, posY + 2, posZ + 32, Blocks.torch);
		world.setBlock(posX + 12, posY + 2, posZ + 38, Blocks.torch);
		world.setBlock(posX + 12, posY + 2, posZ + 43, Blocks.torch);

		TileEntityChest chest1 = new TileEntityChest();
		world.setTileEntity(posX + 11, posY + 2, posZ + 25, chest1);
		if (world.rand.nextInt(100) + world.rand.nextDouble() <= 100)
		{
			chest1.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(ListMisc.Bullets, (int) WyMathHelper.randomWithRange(5, 10), 0));
			chest1.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(ListMisc.Bullets, (int) WyMathHelper.randomWithRange(1, 5), 0));
		}
		if (world.rand.nextInt(100) + world.rand.nextDouble() <= 45)
		{
			chest1.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(swordToSpawn, (int) WyMathHelper.randomWithRange(0, 1), 0));
			chest1.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(ListMisc.Flintlock, (int) WyMathHelper.randomWithRange(0, 1), 0));
		}
		if (world.rand.nextInt(100) + world.rand.nextDouble() <= 45)
			chest1.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(ListMisc.BellyPouch, (int) WyMathHelper.randomWithRange(0, 1), 0));
		if (world.rand.nextInt(100) + world.rand.nextDouble() <= 15)
			chest1.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(ListMisc.BellyPouch, 1, 0));
		if (world.rand.nextInt(100) + world.rand.nextDouble() <= 50)
			chest1.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(ListMisc.KairosekiBullets, (int) WyMathHelper.randomWithRange(1, 5), 0));
		if (world.rand.nextInt(100) + world.rand.nextDouble() <= 50)
			chest1.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(Items.boat, 1, 0));
		if (world.rand.nextInt(100) + world.rand.nextDouble() <= 10)
			chest1.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(ListMisc.Box1, 1, 0));
		if (world.rand.nextInt(100) + world.rand.nextDouble() <= 5)
			chest1.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(ListMisc.Box2, 1, 0));

		TileEntityChest chest2 = new TileEntityChest();
		world.setTileEntity(posX + 8, posY + 2, posZ + 25, chest2);
		if (world.rand.nextInt(100) + world.rand.nextDouble() <= 100)
		{
			chest2.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(Items.bread, (int) WyMathHelper.randomWithRange(1, 3), 0));
			chest2.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(Items.bread, (int) WyMathHelper.randomWithRange(0, 1), 0));
		}
		if (world.rand.nextInt(100) + world.rand.nextDouble() <= 45)
		{
			chest2.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(Items.baked_potato, (int) WyMathHelper.randomWithRange(0, 5), 0));
			chest2.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(Items.apple, (int) WyMathHelper.randomWithRange(1, 4), 0));
			if (world.rand.nextInt(100) + world.rand.nextDouble() <= 70)
				chest2.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(Items.melon, (int) WyMathHelper.randomWithRange(5, 10), 0));
		}
		if (world.rand.nextInt(100) + world.rand.nextDouble() <= 10)
		{
			chest2.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(ListMisc.Cola, (int) WyMathHelper.randomWithRange(0, 3), 0));
			if (world.rand.nextInt(100) + world.rand.nextDouble() <= 50)
				chest2.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(Items.cooked_chicken, (int) WyMathHelper.randomWithRange(0, 2), 0));
			else
				chest2.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(Items.cooked_beef, (int) WyMathHelper.randomWithRange(0, 2), 0));
		}
		if (world.rand.nextInt(100) + world.rand.nextDouble() <= 10)
			chest2.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(ListMisc.Cola, (int) WyMathHelper.randomWithRange(0, 5), 0));

		world.setBlock(posX + 11, posY + 2, posZ + 24, Blocks.air);
		world.setBlock(posX + 8, posY + 2, posZ + 24, Blocks.air);

		world.setBlock(posX + 11, posY + 2, posZ + 22, Blocks.air);
		world.setBlock(posX + 8, posY + 2, posZ + 22, Blocks.air);
		world.setBlock(posX + 11, posY + 2, posZ + 21, Blocks.air);
		world.setBlock(posX + 8, posY + 2, posZ + 21, Blocks.air);

	}

}
