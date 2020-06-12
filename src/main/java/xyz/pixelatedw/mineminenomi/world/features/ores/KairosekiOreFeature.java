package xyz.pixelatedw.mineminenomi.world.features.ores;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;

public class KairosekiOreFeature
{
	public static void register(Biome biome)
	{
		if (biome.getCategory() == Category.OCEAN || biome.getCategory() == Category.BEACH)
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
						ModBlocks.KAIROSEKI_ORE.getDefaultState(),
						6
					),
					Placement.COUNT_RANGE,
					new CountRangeConfig(CommonConfig.instance.getKairosekiSpawnCount(), 0, 0, CommonConfig.instance.getKairosekiSpawnHeight())
				)
			);
		}
	}
}
