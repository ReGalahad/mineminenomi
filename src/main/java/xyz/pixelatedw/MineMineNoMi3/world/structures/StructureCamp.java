package xyz.pixelatedw.MineMineNoMi3.world.structures;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.api.Schematic;
import xyz.pixelatedw.MineMineNoMi3.api.WySchematicHelper;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;
import xyz.pixelatedw.MineMineNoMi3.world.MainWorldGen;

public class StructureCamp extends Structure
{
	
	public static boolean build(Schematic sch, World world, int posX, int posY, int posZ, BiomeGenBase biome)
	{
		boolean flagBiome = (biome != BiomeGenBase.savannaPlateau && biome != BiomeGenBase.plains && biome != BiomeGenBase.taiga && biome != BiomeGenBase.savanna && biome != BiomeGenBase.swampland 
				&& biome != BiomeGenBase.forest && biome != BiomeGenBase.birchForest && !biome.biomeName.equalsIgnoreCase(BiomeGenBase.plains.createMutation().biomeName));
		boolean flagSpecialCheck = !MainWorldGen.checkCorners(sch, world, posX, posY, posZ);
		boolean flagAboveGround = !MainWorldGen.checkCornersAboveGround(sch, world, posX, posY, posZ);

		if(flagBiome || flagSpecialCheck || flagAboveGround)
			return false;
		
		WySchematicHelper.build(sch, world, posX, posY - 7, posZ, Blocks.bedrock);
		populate(posX, posY, posZ, world);

		return true;
	}
	
	private static void populate(int posX, int posY, int posZ, World world)
	{
		String toSpawn1 = ID.PROJECT_ID + ".Marine with Sword";
		String toSpawn2 = ID.PROJECT_ID + ".Marine with Gun";
		String toSpawnCpt = ID.PROJECT_ID + ".Marine with Sword";

		TileEntityChest smallTentChest01 = new TileEntityChest();
		world.setTileEntity(posX + 8, posY + 1, posZ + 20, smallTentChest01);

		TileEntityChest smallTentChest02 = new TileEntityChest();
		world.setTileEntity(posX + 8, posY + 1, posZ + 30, smallTentChest02);
		
		TileEntityChest smallTentChest03 = new TileEntityChest();
		world.setTileEntity(posX + 32, posY + 1, posZ + 20, smallTentChest03);
		
		TileEntityChest smallTentChest04 = new TileEntityChest();
		world.setTileEntity(posX + 32, posY + 1, posZ + 30, smallTentChest04);
		
		TileEntityChest[] smallTentChests = new TileEntityChest[] {smallTentChest01, smallTentChest02, smallTentChest03, smallTentChest04};	

		for(TileEntityChest chest : smallTentChests)
		{
			addChestLoot(world, chest, 100, ListMisc.Bullets, 5, 10);
			addChestLoot(world, chest, 90, Items.apple, 1, 3);
			addChestLoot(world, chest, 70, ListMisc.Bullets, 5, 10);
			addChestLoot(world, chest, 70, Items.cookie, 10, 20);
			addChestLoot(world, chest, 50, ListMisc.BellyPouch, 1, 0);
			addChestLoot(world, chest, 50, Items.gunpowder, 5, 10);
			addChestLoot(world, chest, 30, ListMisc.Flintlock, 0, 1);
		}
		
		TileEntityChest largeTentChest = new TileEntityChest();
		world.setTileEntity(posX + 21, posY + 1, posZ + 7, largeTentChest);

		addChestLoot(world, largeTentChest, 90, ListMisc.KairosekiBullets, 1, 7);
		addChestLoot(world, largeTentChest, 70, Items.cooked_chicken, 2, 4);
		addChestLoot(world, largeTentChest, 70, Items.cooked_beef, 2, 4);
		addChestLoot(world, largeTentChest, 70, Items.gunpowder, 2, 5);
		addChestLoot(world, largeTentChest, 50, Item.getItemFromBlock(ListMisc.DenDenMushi), 0, 1);
		addChestLoot(world, largeTentChest, 50, ListMisc.Cola, 0, 2);
		addChestLoot(world, largeTentChest, 30, ListMisc.BellyPouch, 1, 2);
		addChestLoot(world, largeTentChest, 10, ListMisc.UltraCola, 0, 1);
	}
}
