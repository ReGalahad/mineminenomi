package xyz.pixelatedw.mineminenomi.world.features.structures.largeship;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.ScatteredStructure;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.init.ModFeatures;
import xyz.pixelatedw.mineminenomi.values.ModValuesEnv;

public class LargeShipStructure extends ScatteredStructure<NoFeatureConfig>
{
	public LargeShipStructure()
	{
		super(NoFeatureConfig::deserialize);
		this.setRegistryName(ModValuesEnv.PROJECT_ID, "large_ship");
	}

	@Override
	public String getStructureName()
	{
		return "Large_Ship";
	}

	@Override
	protected int getSeedModifier()
	{
		return 14357611;
	}

	@Override
	public int getSize()
	{
		return 5;
	}

	@Override
	public boolean hasStartAt(ChunkGenerator<?> chunkGen, Random rand, int chunkPosX, int chunkPosZ)
	{
		Biome biome = chunkGen.getBiomeProvider().getBiome(new BlockPos((chunkPosX << 4) + 9, 0, (chunkPosZ << 4) + 9));
		if (chunkGen.hasStructure(biome, this))
			return rand.nextDouble() < (CommonConfig.instance.getLargeShipSpawnRate() / 1000.0);

		return false;
	}

	@Override
	public IStartFactory getStartFactory()
	{
		return Start::new;
	}

	public static void register(Biome biome)
	{
		biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Biome.createDecoratedFeature(ModFeatures.LARGE_SHIP, IFeatureConfig.NO_FEATURE_CONFIG, Placement.NOPE, IPlacementConfig.NO_PLACEMENT_CONFIG));
		biome.addStructure(ModFeatures.LARGE_SHIP, IFeatureConfig.NO_FEATURE_CONFIG);
	}

	public static class Start extends StructureStart
	{
		public Start(Structure<?> structure, int chunkX, int chunkZ, Biome biome, MutableBoundingBox bb, int i3, long seed)
		{
			super(structure, chunkX, chunkZ, biome, bb, i3, seed);
		}

		@Override
		public void init(ChunkGenerator<?> generator, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biome)
		{
			NoFeatureConfig config = generator.getStructureConfig(biome, ModFeatures.LARGE_SHIP);
			int i = chunkX * 16;
			int j = chunkZ * 16;
			BlockPos pos = new BlockPos(i, 90, j);
			this.components.add(new LargeShipPieces.Piece(pos.down(4)));
			this.recalculateStructureSize();
		}
	}
}

