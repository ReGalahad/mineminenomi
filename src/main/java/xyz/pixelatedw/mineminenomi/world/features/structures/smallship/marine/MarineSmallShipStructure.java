package xyz.pixelatedw.mineminenomi.world.features.structures.smallship.marine;

import java.util.Random;

import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
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
import xyz.pixelatedw.wypi.WyHelper;

public class MarineSmallShipStructure extends ScatteredStructure<NoFeatureConfig>
{
	public MarineSmallShipStructure()
	{
		super(NoFeatureConfig::deserialize);
	}

	@Override
	public String getStructureName()
	{
		return "Marine_Small_Ship";
	}

	@Override
	protected int getSeedModifier()
	{
		return 14357621;
	}

	@Override
	public int getSize()
	{
		return 2;
	}

	// Keep in mind Feature Distance - Feature Separation MUST BE > 0, otherwise the game will crash!
	@Override
	protected int getBiomeFeatureDistance(ChunkGenerator<?> chunkGenerator)
	{
		return 10;
	}

	@Override
	protected int getBiomeFeatureSeparation(ChunkGenerator<?> chunkGenerator)
	{
		return 8;
	}

	@Override
	public boolean hasStartAt(ChunkGenerator<?> chunkGen, Random rand, int chunkPosX, int chunkPosZ)
	{
		ChunkPos chunkPos = this.getStartPositionForPosition(chunkGen, rand, chunkPosX, chunkPosZ, 0, 0);
		if (chunkPosX == chunkPos.x && chunkPosZ == chunkPos.z && WyHelper.isSurfaceFlat(chunkGen, chunkPosX, chunkPosZ) && MathHelper.clamp(WyHelper.randomWithRange(0, 100) + WyHelper.randomDouble(), 0, 100) < CommonConfig.instance.getChanceForSmallShipSpawn())
		{
			return chunkGen.getBiomeProvider().getBiomesInSquare((chunkPosX << 4) + 9, (chunkPosZ << 4) + 9, this.getSize() * 16).stream().allMatch(biome -> chunkGen.hasStructure(biome, this));
		}

		return false;
	}

	@Override
	public IStartFactory getStartFactory()
	{
		return MarineSmallShipStructure.Start::new;
	}

	public static void register(Biome biome)
	{
		if (!CommonConfig.instance.canSpawnSmallShips())
			return;

		if (biome.getCategory() == Category.OCEAN)
		{
			biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Biome.createDecoratedFeature(ModFeatures.MARINE_SMALL_SHIP, IFeatureConfig.NO_FEATURE_CONFIG, Placement.NOPE, IPlacementConfig.NO_PLACEMENT_CONFIG));
			biome.addStructure(ModFeatures.MARINE_SMALL_SHIP, IFeatureConfig.NO_FEATURE_CONFIG);
		}
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
			int i = chunkX * 16;
			int j = chunkZ * 16;
			BlockPos blockpos = new BlockPos(i, generator.getSeaLevel() - 2, j);
			Rotation rotation = Rotation.values()[this.rand.nextInt(Rotation.values().length)];
			MarineSmallShipPieces.addComponents(templateManagerIn, blockpos, rotation, this.components);
			this.recalculateStructureSize();
		}
	}
}
