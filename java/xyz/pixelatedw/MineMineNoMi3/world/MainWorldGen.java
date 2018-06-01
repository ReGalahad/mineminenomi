package xyz.pixelatedw.MineMineNoMi3.world;

import java.util.Random;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.MainConfig;
import xyz.pixelatedw.MineMineNoMi3.api.Schematic;
import xyz.pixelatedw.MineMineNoMi3.api.WySchematicHelper;
import xyz.pixelatedw.MineMineNoMi3.api.telemetry.WyTelemetry;
import xyz.pixelatedw.MineMineNoMi3.lists.ListExtraStructures;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;

public class MainWorldGen implements IWorldGenerator 
{
	//-8290517664781417306
	//1682725888991043500
	
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		switch (world.provider.dimensionId)
		{
			case 0: generateSurface(world, random, chunkX * 16, chunkZ * 16);
		}
	}	
	
	private void generateSurface(World world, Random random, int i, int j) 
	{
		this.addOreSpawn(ListMisc.KairosekiOre, world, random, i, j, 16, 16, 4 + random.nextInt(3), 10, 15, 50);	
				
		if(MainConfig.enableShips)
		{
			this.addStructureSpawn(WySchematicHelper.load("marineShip"), world, random, i * 2, j * 2, 1, 1, 1.8 * MainConfig.rateShipsSpawn);
			this.addStructureSpawn(WySchematicHelper.load("pyrateShip"), world, random, i * 2, j * 2, 1, 1, 2.1 * MainConfig.rateShipsSpawn);
			this.addStructureSpawn(WySchematicHelper.load("pyrateLargeShip"), world, random, i * 2, j * 2, 1, 1, 1.8 * MainConfig.rateShipsSpawn);
			this.addStructureSpawn(WySchematicHelper.load("marineLargeShip"), world, random, i * 2, j * 2, 1, 1, 1.9 * MainConfig.rateShipsSpawn);
		}
		/*if(MainConfig.enableCamps)
		{
			this.addStructureSpawn(WySchematicHelper.load("marineCamp"), world, random, i * 3, j * 3, 1, 1, 50);
		}*/
	}
	 
	private void addOreSpawn(Block block, World world, Random random, int blockXPos, int blockZPos, int maxX, int maxZ, int maxVeinSize, int chancesToSpawn, int minY, int maxY)
	{
		int maxPossY = minY + (maxY - 1);
		int diffBtwnMinMaxY = maxY - minY;
		
		for(int x = 0; x < chancesToSpawn; x++)
		{
			int posX = blockXPos + random.nextInt(maxX);
			int posY = minY + random.nextInt(diffBtwnMinMaxY);
			int posZ = blockZPos + random.nextInt(maxZ);
			BiomeGenBase biome = world.getBiomeGenForCoordsBody(posX, posZ);
			
			if(block == ListMisc.KairosekiOre)
				if(biome.biomeName.equals("Ocean") || biome.biomeName.equals("Deep Ocean") || biome.biomeName.equals("Beach"))
					new WorldGenMinable(block, maxVeinSize).generate(world, random, posX, posY, posZ);
			//else
			//	new WorldGenMinable(block.getDefaultState(), maxVeinSize).generate(world, random, new BlockPos(posX, posY, posZ));
		}
	}
	 
	private void addStructureSpawn(Schematic s, World world, Random random, int blockXPos, int blockZPos, int maxX, int maxZ, double rarity)
	{
		if(world.rand.nextInt(100) + world.rand.nextDouble() <= rarity)
		{		
			int posX = blockXPos;// + random.nextInt(maxX);
			int posY = random.nextInt(128);
			int posZ = blockZPos;// + random.nextInt(maxZ);			
			BiomeGenBase biome = world.getBiomeGenForCoordsBody(posX, posZ);		
			
			if(s.getName().toLowerCase().contains("ship"))
			{
				if( (biome.biomeName.equals("Ocean") || biome.biomeName.equals("Deep Ocean") ) && world.getChunkProvider().chunkExists(posX, posZ) && checkForShipSpawn(s, world, posX, posY, posZ))
				{
					System.out.println("" + s.getName() + " spawned at /tp @p " + posX + " " + posY + " " + posZ);
					if(s.getName().equals("marineShip") || s.getName().equals("pyrateShip"))		
					{
						WySchematicHelper.build(s, world, posX, posY, posZ);
						ListExtraStructures.buildSmallShip(posX, posY, posZ, world, s.getName());
					}
					else if(s.getName().equals("marineLargeShip") || s.getName().equals("pyrateLargeShip"))	
					{ 
						WySchematicHelper.build(s, world, posX, posY - 4, posZ);	
						ListExtraStructures.buildLargeShip(posX, posY, posZ, world, s.getName());
					}
					
			    	if(!ID.DEV_EARLYACCESS)
			    		WyTelemetry.addStat("spawnedStructure_" + s.getName(), 1);
				}
			}
			else if(s.getName().toLowerCase().contains("camp"))
			{
				if( (biome.biomeName.equals("Beach") || biome.biomeName.equals("Forest") || biome.biomeName.equals("Desert") || biome.biomeName.equals("Plains")) && checkForCampSpawn(s, world, posX, posY, posZ))
				{
					System.out.println("" + s.getName() + " spawned at /tp @p " + posX + " " + posY + " " + posZ);
					WySchematicHelper.build(s, world, posX, posY, posZ);
					ListExtraStructures.buildCamp(posX, posY, posZ, world);
				}
			}
		}	
	}
	
	private Block[] campBlocks = new Block[] {Blocks.dirt, Blocks.grass, Blocks.stone, Blocks.sand, Blocks.sandstone, Blocks.cobblestone};
	
	private boolean checkForCampSpawn(Schematic s, World world, int posX, int posY, int posZ)
	{
		for(int i = 0; i < s.getWidth(); i++)
		for(int j = 0; j < s.getHeight(); j++)
		for(int k = 0; k < s.getLength(); k++)
		{	
			for(Block b : campBlocks)
			{
				if( world.getBlock(posX + i, posY - 1, posZ + k) == b )
				{
					if( world.getBlock(posX, posY + 2, posZ) == Blocks.air)
						return true;
					else return false;
				}
				else return false;
			}
		}		
		return false;
	}
	
	private boolean checkForShipSpawn(Schematic s, World world, int posX, int posY, int posZ)
	{
		for(int i = 0; i < s.getWidth(); i++)
		for(int j = 0; j < s.getHeight(); j++)
		for(int k = 0; k < s.getLength(); k++)
		{			
			if(world.getBlock(posX + i, posY + j, posZ + k) == Blocks.air) //|| world.getBlock(posX, posY, posZ) == Blocks.water || world.getBlock(posX + i, posY + j, posZ + k) == Blocks.flowing_water)
			{
				if( world.getBlock(posX, posY - 1, posZ) == Blocks.water || world.getBlock(posX, posY - 1, posZ) == Blocks.flowing_water )
				{
					if( world.getBlock(posX, posY + 2, posZ) == Blocks.air)
						return true;
					else return false;
				}
				else return false;
			}
		}		
		return false;
	}

}
