package xyz.pixelatedw.MineMineNoMi3.world.structures;

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

public class StructureLargeShip
{
	public static boolean build(Schematic sch, World world, int posX, int posY, int posZ, BiomeGenBase biome)
	{
		boolean flagBiome = (biome != BiomeGenBase.ocean && biome != BiomeGenBase.deepOcean);
		boolean flagSpecialCheck = !MainWorldGen.checkForWaterSpawn(sch, world, posX, posY, posZ);
		
		if( flagBiome || flagSpecialCheck )
			return false;	
		
		WySchematicHelper.build(sch, world, posX, posY, posZ);
		populate(posX, posY, posZ, world, sch.getName());

		return true;
	}
	
	public static void populate(int posX, int posY, int posZ, World world, String faction)
	{
		String toSpawn1;
		String toSpawn2;
		String toSpawnCpt;
		int x1 = 0, x2 = 0, x3 = 0;
		
		Item swordToSpawn;
		if(faction.equals("marineLargeShip"))
		{
			toSpawn1 = ID.PROJECT_ID + ".Marine with Sword";
			toSpawn2 = ID.PROJECT_ID + ".Marine with Gun";
			toSpawnCpt = ID.PROJECT_ID + ".Marine Captain";
			swordToSpawn = ListMisc.MarineSword;
			x1 = 13;
			x2 = 6;
			x3 = 10;
		}
		else
		{
			toSpawn1 = ID.PROJECT_ID + ".Pirate with Sword";
			toSpawn2 = ID.PROJECT_ID + ".Pirate with Gun";	
			toSpawnCpt = ID.PROJECT_ID + ".Pirate Captain";
			swordToSpawn = ListMisc.PirateCutlass;
			x1 = 16;
			x2 = 9;
			x3 = 13;
		}
			
		TileEntityCustomSpawner spw1 = new TileEntityCustomSpawner().setSpawnerMob(toSpawn1).setSpawnerLimit(5);
		TileEntityCustomSpawner spw2 = new TileEntityCustomSpawner().setSpawnerMob(toSpawn2).setSpawnerLimit(5);
		TileEntityCustomSpawner spw3 = new TileEntityCustomSpawner().setSpawnerMob(toSpawn1).setSpawnerLimit(5);
		TileEntityCustomSpawner spw4 = new TileEntityCustomSpawner().setSpawnerMob(toSpawn2).setSpawnerLimit(5);
		TileEntityCustomSpawner spw5 = new TileEntityCustomSpawner().setSpawnerMob(toSpawn1).setSpawnerLimit(5);
		TileEntityCustomSpawner spw6 = new TileEntityCustomSpawner().setSpawnerMob(toSpawnCpt).setSpawnerLimit(1);
		
		world.setBlock(posX + 10, posY + 2, posZ + 27, ListMisc.CustomSpawner );
		world.setTileEntity(posX + 10, posY + 2, posZ + 27, spw1);
		world.setBlock(posX + 14, posY + 2, posZ + 32, ListMisc.CustomSpawner );
		world.setTileEntity(posX + 14, posY + 2, posZ + 32, spw2);
		
		world.setBlock(posX + 12, posY + 8, posZ + 17, ListMisc.CustomSpawner );
		world.setTileEntity(posX + 12, posY + 8, posZ + 17, spw3);
		world.setBlock(posX + 12, posY + 8, posZ + 11, ListMisc.CustomSpawner );
		world.setTileEntity(posX + 12, posY + 8, posZ + 11, spw4);
		
		world.setBlock(posX + 14, posY + 8, posZ + 42, ListMisc.CustomSpawner);
		world.setTileEntity(posX + 14, posY + 8, posZ + 42, spw5);
		world.setBlock(posX + 8, posY + 8, posZ + 42, ListMisc.CustomSpawner );
		world.setTileEntity(posX + 8, posY + 8, posZ + 42, spw6);
		
		TileEntityChest chest1 = new TileEntityChest();
		world.setTileEntity(posX + x1, posY + 2, posZ + 39, chest1);
		if(world.rand.nextInt(100) + world.rand.nextDouble() <= 100)
		{ 
			chest1.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(ListMisc.Bullets, (int) WyMathHelper.randomWithRange(5, 10), 0));	
			chest1.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(ListMisc.Bullets, (int) WyMathHelper.randomWithRange(1, 5), 0));
		}
		if(world.rand.nextInt(100) + world.rand.nextDouble() <= 45)
		{
			chest1.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(swordToSpawn, (int) WyMathHelper.randomWithRange(0, 1), 0));	
			chest1.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(ListMisc.Flintlock, (int) WyMathHelper.randomWithRange(0, 1), 0));	
		}
		if(world.rand.nextInt(100) + world.rand.nextDouble() <= 50)
			chest1.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(ListMisc.BellyPouch, 1, 0));	
		if(world.rand.nextInt(100) + world.rand.nextDouble() <= 20)
			chest1.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(ListMisc.BellyPouch, 1, 0));	
		if(world.rand.nextInt(100) + world.rand.nextDouble() <= 50)
			chest1.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(ListMisc.KairosekiBullets, (int) WyMathHelper.randomWithRange(1, 5), 0));	
		if(world.rand.nextInt(100) + world.rand.nextDouble() <= 60)
			chest1.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(Items.boat, 1, 0));	
		if(world.rand.nextInt(100) + world.rand.nextDouble() <= 5)
			chest1.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(ListMisc.Box1, 1, 0));
		
		TileEntityChest chest2 = new TileEntityChest();
		world.setTileEntity(posX + x2, posY + 2, posZ + 39, chest2);
		if(world.rand.nextInt(100) + world.rand.nextDouble() <= 100)
		{
			chest2.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(Items.bread, (int) WyMathHelper.randomWithRange(1, 5), 0));	
			chest2.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(Items.bread, (int) WyMathHelper.randomWithRange(1, 2), 0));
			if(world.rand.nextInt(100) + world.rand.nextDouble() <= 10)
				chest2.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(Items.melon, (int) WyMathHelper.randomWithRange(5, 10), 0));	
		}
		if(world.rand.nextInt(100) + world.rand.nextDouble() <= 55)
		{
			chest2.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(Items.baked_potato, (int) WyMathHelper.randomWithRange(0, 5), 0));	
			chest2.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(Items.apple, (int) WyMathHelper.randomWithRange(1, 10), 0));	
			if(world.rand.nextInt(100) + world.rand.nextDouble() <= 50)
				chest2.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(Items.melon, (int) WyMathHelper.randomWithRange(5, 10), 0));				
		}
		if(world.rand.nextInt(100) + world.rand.nextDouble() <= 20)
		{
			chest2.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(ListMisc.Cola, (int) WyMathHelper.randomWithRange(0, 3), 0));	
			if(world.rand.nextInt(100) + world.rand.nextDouble() <= 50)
			{
				if(world.rand.nextInt(100) + world.rand.nextDouble() <= 50)
					chest2.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(Items.cooked_fished, (int) WyMathHelper.randomWithRange(1, 4), 0));
				else
					chest2.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(Items.cooked_fished, (int) WyMathHelper.randomWithRange(1, 4), 1));
			}
			else
				chest2.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(Items.cooked_chicken, (int) WyMathHelper.randomWithRange(1, 2), 0));
		}
		if(world.rand.nextInt(100) + world.rand.nextDouble() <= 10)
			chest2.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(ListMisc.Cola, (int) WyMathHelper.randomWithRange(0, 5), 0));	
		
		TileEntityChest chest3 = new TileEntityChest();
		world.setTileEntity(posX + x3, posY + 2, posZ + 42, chest3);
		
		if(world.rand.nextInt(100) + world.rand.nextDouble() <= 75)
			chest3.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(ListMisc.BellyPouch, 1, 0));	
		if(world.rand.nextInt(100) + world.rand.nextDouble() <= 35)
			chest3.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(ListMisc.BellyPouch, 1, 0));	
		if(world.rand.nextInt(100) + world.rand.nextDouble() <= 25)
			chest3.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(ListMisc.BlackMetal, (int) WyMathHelper.randomWithRange(1, 2), 0));	
		if(world.rand.nextInt(100) + world.rand.nextDouble() <= 70)
			chest3.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(ListMisc.KairosekiBullets, (int) WyMathHelper.randomWithRange(5, 10), 0));	
		if(world.rand.nextInt(100) + world.rand.nextDouble() <= 50)
			chest3.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(Items.boat, 1, 0));	
		if(world.rand.nextInt(100) + world.rand.nextDouble() <= 20)
			chest3.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(ListMisc.Box1, 1, 0));
		if(world.rand.nextInt(100) + world.rand.nextDouble() <= 10)
			chest3.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(ListMisc.UltraCola, 1, 0));
		if(world.rand.nextInt(100) + world.rand.nextDouble() <= 15)
			chest3.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(ListMisc.Box2, 1, 0));
		if(world.rand.nextInt(100) + world.rand.nextDouble() <= 10)
			chest3.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(ListMisc.Box3, 1, 0));
	}
}
