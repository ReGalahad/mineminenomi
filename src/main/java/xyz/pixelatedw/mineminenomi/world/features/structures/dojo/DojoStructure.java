package xyz.pixelatedw.mineminenomi.world.features.structures.dojo;

import java.util.Random;

import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import xyz.pixelatedw.mineminenomi.init.ModFeatures;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;

public class DojoStructure extends Structure<NoFeatureConfig>
{
	public DojoStructure()
	{
		super(NoFeatureConfig::deserialize);
		this.setRegistryName(APIConfig.PROJECT_ID, "dojo");
	}

	@Override
	public String getStructureName()
	{
		return "Dojo";
	}

	@Override
	public int getSize()
	{
		return 5;
	}

	@Override
	public boolean hasStartAt(ChunkGenerator<?> generator, Random rand, int chunkPosX, int chunkPosZ)
	{
		Biome biome = generator.getBiomeProvider().getBiome(new BlockPos((chunkPosX << 4) + 9, 0, (chunkPosZ << 4) + 9));
		if (generator.hasStructure(biome, this))
			return WyHelper.randomWithRange(0, 100) < 1;

		return false;
	}

	@Override
	public IStartFactory getStartFactory()
	{
		return DojoStructure.Start::new;
	}

	public static void register(Biome biome)
	{
		if (biome.getCategory() == Category.PLAINS || biome.getCategory() == Category.DESERT)
		{
			biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Biome.createDecoratedFeature(ModFeatures.DOJO, IFeatureConfig.NO_FEATURE_CONFIG, Placement.NOPE, IPlacementConfig.NO_PLACEMENT_CONFIG));
			biome.addStructure(ModFeatures.DOJO, IFeatureConfig.NO_FEATURE_CONFIG);
		}
	}

	public static class Start extends StructureStart
	{
		public Start(Structure<?> structure, int chunkX, int chunkZ, Biome biome, MutableBoundingBox bb, int i3, long seed)
		{
			super(structure, chunkX, chunkZ, biome, bb, i3, seed);
		}

		@Override
		public void init(ChunkGenerator<?> generator, TemplateManager templateManager, int chunkX, int chunkZ, Biome biome)
		{
			int i = chunkX * 16;
			int j = chunkZ * 16;
			BlockPos blockpos = new BlockPos(i, 90, j);
			Rotation rotation = Rotation.values()[this.rand.nextInt(Rotation.values().length)];
			this.components.add(new DojoPiece(templateManager, blockpos, rotation));
			this.recalculateStructureSize();
		}
	}
}
