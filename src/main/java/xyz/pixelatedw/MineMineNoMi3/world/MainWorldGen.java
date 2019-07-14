package xyz.pixelatedw.MineMineNoMi3.world;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

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
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.WySchematicHelper;
import xyz.pixelatedw.MineMineNoMi3.api.debug.WyDebug;
import xyz.pixelatedw.MineMineNoMi3.api.telemetry.WyTelemetry;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;
import xyz.pixelatedw.MineMineNoMi3.world.structures.StructureCamp;
import xyz.pixelatedw.MineMineNoMi3.world.structures.StructureLargeBase;
import xyz.pixelatedw.MineMineNoMi3.world.structures.StructureLargeShip;
import xyz.pixelatedw.MineMineNoMi3.world.structures.StructureSmallShip;

public class MainWorldGen implements IWorldGenerator 
{
	//-8290517664781417306
	//1682725888991043500

	@Override
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
			this.addStructureSpawn(WySchematicHelper.load("marineShip"), world, random, i, j, 1, 1, 1.5 * MainConfig.modifierShipsSpawn);
			this.addStructureSpawn(WySchematicHelper.load("pyrateShip"), world, random, i, j, 1, 1, 1.8 * MainConfig.modifierShipsSpawn);
			this.addStructureSpawn(WySchematicHelper.load("pyrateLargeShip"), world, random, i, j, 1, 1, 1.3 * MainConfig.modifierShipsSpawn);
			this.addStructureSpawn(WySchematicHelper.load("marineLargeShip"), world, random, i, j, 1, 1, 1.4 * MainConfig.modifierShipsSpawn);
		}
		
		this.addStructureSpawn(WySchematicHelper.load("dojo"), world, random, i, j, 1, 1, 25);
		
		if(MainConfig.enableCamps)
		{
			this.addStructureSpawn(WySchematicHelper.load("marineCamp"), world, random, i, j, 1, 1, 15);
			this.addStructureSpawn(WySchematicHelper.load("banditCamp"), world, random, i, j, 1, 1, 25);
		}
		
		if(MainConfig.enableBases)
			this.addStructureSpawn(WySchematicHelper.load("marineLargeBase"), world, random, i, j, 1, 1, 0.2);
		
		this.addDialSpawn(ListMisc.DialEisenBlock, world, random, i, j, 1, 1, 100);
		this.addDialSpawn(ListMisc.DialFireBlock, world, random, i, j, 1, 1, 70);
		this.addDialSpawn(ListMisc.DialAxeBlock, world, random, i, j, 1, 1, 70);
		this.addDialSpawn(ListMisc.DialMilkyBlock, world, random, i, j, 1, 1, 20);
		this.addDialSpawn(ListMisc.DialRejectBlock, world, random, i, j, 1, 1, 10);
		this.addDialSpawn(ListMisc.DialBreathBlock, world, random, i, j, 1, 1, 50);
		this.addDialSpawn(ListMisc.DialFlashBlock, world, random, i, j, 1, 1, 45);
		
	}
	 
	public boolean addOreSpawn(Block block, World world, Random random, int blockXPos, int blockZPos, int maxX, int maxZ, int maxVeinSize, int chancesToSpawn, int minY, int maxY)
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
			{
				if(biome.biomeName.equals("Ocean") || biome.biomeName.equals("Deep Ocean") || biome.biomeName.equals("Beach"))
				{
					new WorldGenMinable(block, maxVeinSize).generate(world, random, posX, posY, posZ);
					return true;
				}
			}
		}
		
		return false;
	}
	
	
	public boolean addDialSpawn(Block blockToSpawn, World world, Random random, int blockXPos, int blockZPos, int maxX, int maxZ, double rarity)
	{
		if(world.rand.nextInt(100) + world.rand.nextDouble() <= rarity)
		{		
			int posX = blockXPos;
			int posY = random.nextInt(128);
			int posZ = blockZPos;
			BiomeGenBase biome = world.getBiomeGenForCoordsBody(posX, posZ);	
			
			if( (biome == BiomeGenBase.beach || biome == BiomeGenBase.plains) && (world.getBlock(posX, posY - 1, posZ) == Blocks.sand || world.getBlock(posX, posY - 1, posZ) == Blocks.grass) && world.getBlock(posX, posY + 1, posZ) == Blocks.air)
			{
				world.setBlock(posX, posY, posZ, blockToSpawn);
				
				if(WyDebug.isDebug())
					System.out.println("" + blockToSpawn.getLocalizedName() + " spawned at /tp @p " + posX + " " + (posY + 1) + " " + posZ);
				
		    	if(!ID.DEV_EARLYACCESS)
		    		WyTelemetry.addStat("spawnedDial_" + WyHelper.getFancyName(blockToSpawn.getLocalizedName()), 1);
		    	
		    	return true;
			}
			
		}
		
		return false;
	}
	
	public boolean addStructureSpawn(Schematic sch, World world, Random random, int blockXPos, int blockZPos, int maxX, int maxZ, double rarity)
	{
		if(random.nextInt(100) + random.nextDouble() <= rarity)
		{
			int posX = blockXPos;
			int posY = random.nextInt(128);
			int posZ = blockZPos;
			BiomeGenBase biome = world.getBiomeGenForCoordsBody(posX, posZ);		

			if(sch != null)
			{
				boolean spawned = false;
				
				// Ships
				if(sch.getName().equals("marineShip") || sch.getName().equals("pyrateShip"))
					spawned = StructureSmallShip.build(sch, world, posX, posY, posZ, biome);			
				if(sch.getName().equals("marineLargeShip") || sch.getName().equals("pyrateLargeShip"))
					spawned = StructureLargeShip.build(sch, world, posX, posY, posZ, biome);
				
				// Camps
				if(sch.getName().equals("marineCamp") || sch.getName().equals("banditCamp"))
					spawned = StructureCamp.build(sch, world, posX, posY, posZ, biome);
				
				// Bases
				if(sch.getName().equals("marineLargeBase"))
					spawned = StructureLargeBase.build(sch, world, posX, posY, posZ, biome);
				
				// Quest Related
				if(sch.getName().equals("dojo"))
					spawned = StructureLargeShip.build(sch, world, posX, posY, posZ, biome);
							
				if(spawned)
				{
					if(WyDebug.isDebug())
						System.out.println("" + sch.getName() + " spawned at /tp @p " + posX + " " + posY + " " + posZ);
	
					if(!ID.DEV_EARLYACCESS )
						WyTelemetry.addStat("spawnedStructure_" + sch.getName(), 1);
				}   	
				
				return spawned;
			}
			else
			{
				Logger.getGlobal().log(Level.SEVERE, "Some schematic is null, this should never happen !");
			}
		}
		
		return false;
	}

	public static boolean checkCorners(Schematic sch, World world, int posX, int posY, int posZ)
	{
		for(int i = 1; i < 4; i++)
		{
			boolean corner1 = world.getBlock(posX, posY - i, posZ) != Blocks.air && world.getBlock(posX, posY - i, posZ) != Blocks.water;
			boolean corner2 = world.getBlock(posX + sch.getWidth(), posY - i, posZ) != Blocks.air && world.getBlock(posX + sch.getWidth(), posY - i, posZ) != Blocks.water;
			boolean corner3 = world.getBlock(posX, posY - i, posZ + sch.getLength()) != Blocks.air && world.getBlock(posX, posY - i, posZ + sch.getLength()) != Blocks.water;
			boolean corner4 = world.getBlock(posX + sch.getWidth(), posY - i, posZ + sch.getLength()) != Blocks.air && world.getBlock(posX + sch.getWidth(), posY - i, posZ + sch.getLength()) != Blocks.water;		
	
			if((corner1?1:0) + (corner2?1:0) + (corner3?1:0) + (corner4?1:0) >= 3)
			{
				return true;
			}			
		}
		
		return false;
	}

	public static boolean checkCornersAboveGround(Schematic sch, World world, int posX, int posY, int posZ)
	{
		boolean corner1 = world.getBlock(posX, posY + 1, posZ) == Blocks.air;
		boolean corner2 = world.getBlock(posX + sch.getWidth(), posY + 1, posZ) == Blocks.air;
		boolean corner3 = world.getBlock(posX, posY + 1, posZ + sch.getLength()) == Blocks.air;
		boolean corner4 = world.getBlock(posX + sch.getWidth(), posY + 1, posZ + sch.getLength()) == Blocks.air;		

		if((corner1?1:0) + (corner2?1:0) + (corner3?1:0) + (corner4?1:0) >= 3)
		{
			return true;
		}
		
		return false;
	}
	
	public static boolean checkForWaterSpawn(Schematic s, World world, int posX, int posY, int posZ)
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
