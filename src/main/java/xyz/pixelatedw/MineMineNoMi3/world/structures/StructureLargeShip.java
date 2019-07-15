package xyz.pixelatedw.MineMineNoMi3.world.structures;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.api.Schematic;
import xyz.pixelatedw.MineMineNoMi3.api.WySchematicHelper;
import xyz.pixelatedw.MineMineNoMi3.blocks.tileentities.TileEntityCustomSpawner;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;
import xyz.pixelatedw.MineMineNoMi3.world.MainWorldGen;

public class StructureLargeShip extends Structure
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
		String trashWithSword;
		String trashWithGun;
		String boss;
		int x1 = 0, x2 = 0, x3 = 0;
		
		Item swordToSpawn;
		if(faction.equals("marineLargeShip"))
		{
			trashWithSword = ID.PROJECT_ID + ".Marine with Sword";
			trashWithGun = ID.PROJECT_ID + ".Marine with Gun";
			boss = ID.PROJECT_ID + ".Marine Captain";
			swordToSpawn = ListMisc.MarineSword;
			x1 = 13;
			x2 = 6;
			x3 = 10;
		}
		else
		{
			trashWithSword = ID.PROJECT_ID + ".Pirate with Sword";
			trashWithGun = ID.PROJECT_ID + ".Pirate with Gun";	
			boss = ID.PROJECT_ID + ".Pirate Captain";
			swordToSpawn = ListMisc.PirateCutlass;
			x1 = 16;
			x2 = 9;
			x3 = 13;
		}
			
		// Spawners
		TileEntityCustomSpawner spawnTrash01 = new TileEntityCustomSpawner().setSpawnerMob(trashWithSword).setSpawnerLimit(5);
		TileEntityCustomSpawner spawnTrash02 = new TileEntityCustomSpawner().setSpawnerMob(trashWithGun).setSpawnerLimit(5);
		TileEntityCustomSpawner spawnTrash03 = new TileEntityCustomSpawner().setSpawnerMob(trashWithSword).setSpawnerLimit(5);
		TileEntityCustomSpawner spawnTrash04 = new TileEntityCustomSpawner().setSpawnerMob(trashWithGun).setSpawnerLimit(5);
		TileEntityCustomSpawner spawnTrash05 = new TileEntityCustomSpawner().setSpawnerMob(trashWithSword).setSpawnerLimit(5);
		TileEntityCustomSpawner spawnBoss = new TileEntityCustomSpawner().setSpawnerMob(boss).setSpawnerLimit(1);
		
		world.setBlock(posX + 10, posY + 2, posZ + 27, ListMisc.CustomSpawner );
		world.setTileEntity(posX + 10, posY + 2, posZ + 27, spawnTrash01);
		
		world.setBlock(posX + 14, posY + 2, posZ + 32, ListMisc.CustomSpawner );
		world.setTileEntity(posX + 14, posY + 2, posZ + 32, spawnTrash02);
		
		world.setBlock(posX + 12, posY + 8, posZ + 17, ListMisc.CustomSpawner );
		world.setTileEntity(posX + 12, posY + 8, posZ + 17, spawnTrash03);
		
		world.setBlock(posX + 12, posY + 8, posZ + 11, ListMisc.CustomSpawner );
		world.setTileEntity(posX + 12, posY + 8, posZ + 11, spawnTrash04);
		
		world.setBlock(posX + 14, posY + 8, posZ + 42, ListMisc.CustomSpawner);
		world.setTileEntity(posX + 14, posY + 8, posZ + 42, spawnTrash05);
		
		world.setBlock(posX + 8, posY + 8, posZ + 42, ListMisc.CustomSpawner );
		world.setTileEntity(posX + 8, posY + 8, posZ + 42, spawnBoss);
		
		
		// Chests	
		TileEntityChest combatChest = new TileEntityChest();
		world.setTileEntity(posX + x1, posY + 2, posZ + 39, combatChest);
		
		addChestLoot(world, combatChest, 100, ListMisc.Bullets, 5, 10);
		addChestLoot(world, combatChest, 100, ListMisc.Bullets, 1, 5);
		addChestLoot(world, combatChest, 60, Items.boat, 1, 0);
		addChestLoot(world, combatChest, 50, ListMisc.BellyPouch, 1, 0);
		addChestLoot(world, combatChest, 50, ListMisc.KairosekiBullets, 1, 5);
		addChestLoot(world, combatChest, 45, swordToSpawn, 0, 1);
		addChestLoot(world, combatChest, 45, ListMisc.Flintlock, 0, 1);
		addChestLoot(world, combatChest, 20, ListMisc.BellyPouch, 1, 0);
		addChestLoot(world, combatChest, 5, ListMisc.Box1, 1, 0);

		TileEntityChest foodChest = new TileEntityChest();
		world.setTileEntity(posX + x2, posY + 2, posZ + 39, foodChest);
		
		addChestLoot(world, foodChest, 100, Items.bread, 1, 3);
		addChestLoot(world, foodChest, 100, Items.bread, 1, 2);
		if(getRandomChance(world) <= 10)
			addChestLoot(world, foodChest, 45, Items.melon, 5, 10);
		addChestLoot(world, foodChest, 45, Items.baked_potato, 0, 5);
		addChestLoot(world, foodChest, 45, Items.apple, 1, 10);
		if(getRandomChance(world) <= 70)
			addChestLoot(world, foodChest, 45, Items.melon, 5, 10);
		addChestLoot(world, foodChest, 20, ListMisc.Cola, 0, 3);
		if(getRandomChance(world) <= 50)
		{
			addChestLoot(world, foodChest, 10, Items.cooked_chicken, 0, 2);
			if(getRandomChance(world) <= 50)
				addChestLoot(world, foodChest, 10, Items.cooked_fished, 1, 4);
			else
				addChestLoot(world, foodChest, 10, Items.cooked_fished, 1, 1, 4);
		}
		else
			addChestLoot(world, foodChest, 10, Items.cooked_chicken, 1, 2);
		addChestLoot(world, foodChest, 10, ListMisc.Cola, 0, 5);

		TileEntityChest specialLootChest = new TileEntityChest();
		world.setTileEntity(posX + x3, posY + 2, posZ + 42, specialLootChest);
		
		addChestLoot(world, foodChest, 75, ListMisc.BellyPouch, 1, 0);
		addChestLoot(world, foodChest, 70, ListMisc.KairosekiBullets, 5, 10);
		addChestLoot(world, foodChest, 50, Items.boat, 1, 0);
		addChestLoot(world, foodChest, 35, ListMisc.BellyPouch, 1, 0);
		addChestLoot(world, foodChest, 25, ListMisc.BlackMetal, 1, 2);
		addChestLoot(world, foodChest, 20, ListMisc.Box1, 1, 0);
		addChestLoot(world, foodChest, 15, ListMisc.Box2, 1, 0);
		addChestLoot(world, foodChest, 10, ListMisc.UltraCola, 1, 0);
		addChestLoot(world, foodChest, 10, ListMisc.Box3, 1, 0);
	}
}
