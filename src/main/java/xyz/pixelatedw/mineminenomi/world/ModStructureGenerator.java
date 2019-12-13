package xyz.pixelatedw.mineminenomi.world;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;

public class ModStructureGenerator
{
    public static void init()
    {
    	//BiomeDictionary.getBiomes(BiomeDictionary.Type.SWAMP).forEach(biome -> biome.addFeature(net.minecraft.world.gen.GenerationStage.Decoration.VEGETAL_DECORATION,
        //        Biome.createDecoratedFeature(new TrilliumGenerator(), new NoFeatureConfig(), Placement.TOP_SOLID_HEIGHTMAP, IPlacementConfig.NO_PLACEMENT_CONFIG)));

    	for (Biome biome: ForgeRegistries.BIOMES.getValues())
        {
			if(biome.getCategory() == Category.OCEAN || biome.getCategory() == Category.BEACH)
			{
				biome.addFeature
				(
					Decoration.UNDERGROUND_ORES, 
					Biome.createDecoratedFeature
					(
						Feature.ORE, 
						new OreFeatureConfig
						(
							OreFeatureConfig.FillerBlockType.NATURAL_STONE,
							ModBlocks.kairosekiOre.getDefaultState(),
							6
						),
						Placement.COUNT_RANGE,
						new CountRangeConfig(40, 10, 0, 128)
					)
				);
			}		
        }
    }
}
