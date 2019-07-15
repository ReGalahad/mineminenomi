package xyz.pixelatedw.MineMineNoMi3.world.structures;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import xyz.pixelatedw.MineMineNoMi3.api.Schematic;
import xyz.pixelatedw.MineMineNoMi3.api.WySchematicHelper;
import xyz.pixelatedw.MineMineNoMi3.world.MainWorldGen;

public class StructureLargeBase extends Structure
{
	public static boolean build(Schematic sch, World world, int posX, int posY, int posZ, BiomeGenBase biome)
	{		
		boolean flagBiome = (biome != BiomeGenBase.savannaPlateau && biome != BiomeGenBase.plains && biome != BiomeGenBase.taiga && biome != BiomeGenBase.savanna && biome != BiomeGenBase.swampland 
				&& biome != BiomeGenBase.forest && biome != BiomeGenBase.birchForest);
		boolean flagSpecialCheck = !MainWorldGen.checkCorners(sch, world, posX, posY, posZ);
		boolean flagAboveGround = !MainWorldGen.checkCornersAboveGround(sch, world, posX, posY, posZ);

		if(flagBiome || flagSpecialCheck || flagAboveGround)
			return false;
		
		WySchematicHelper.build(sch, world, posX, posY, posZ, Blocks.bedrock);
		populate(posX, posY, posZ, world);

		return true;
	}
	
	private static void populate(int posX, int posY, int posZ, World world)
	{

	}
}
