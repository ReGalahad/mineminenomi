package xyz.pixelatedw.mineminenomi.init;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.pixelatedw.mineminenomi.world.features.structures.dojo.DojoPieces;
import xyz.pixelatedw.mineminenomi.world.features.structures.dojo.DojoStructure;
import xyz.pixelatedw.mineminenomi.world.features.structures.largeship.LargeShipPieces;
import xyz.pixelatedw.mineminenomi.world.features.structures.largeship.LargeShipStructure;
import xyz.pixelatedw.mineminenomi.world.features.structures.smallship.SmallShipPieces;
import xyz.pixelatedw.mineminenomi.world.features.structures.smallship.SmallShipStructure;

public class ModFeatures
{

	public static final Structure<NoFeatureConfig> SMALL_SHIP = new SmallShipStructure();
	public static final Structure<NoFeatureConfig> LARGE_SHIP = new LargeShipStructure();
	public static final Structure<NoFeatureConfig> DOJO = new DojoStructure();

	public static void init()
	{
		for (Biome biome : ForgeRegistries.BIOMES)
		{
			if (biome.getCategory() == Category.OCEAN)
			{
				SmallShipStructure.register(biome);
				LargeShipStructure.register(biome);
			}
			
			if(biome.getCategory() == Category.PLAINS || biome.getCategory() == Category.DESERT)
			{
				DojoStructure.register(biome);
			}
			
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
	
	public static class Pieces
	{
		public static final IStructurePieceType SMALL_SHIP_BODY = IStructurePieceType.register(SmallShipPieces.Piece::new, "small_ship_body");
		public static final IStructurePieceType LARGE_SHIP_BODY = IStructurePieceType.register(LargeShipPieces.Piece::new, "large_ship_body");
		public static final IStructurePieceType DOJO_BODY = IStructurePieceType.register(DojoPieces.Piece::new, "dojo_body");
	}
	
	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class Registry
	{
		@SubscribeEvent
		public static void registerEntityTypes(RegistryEvent.Register<Feature<?>> event)
		{
			if (!event.getName().equals(ForgeRegistries.FEATURES.getRegistryName()))
				return;

			event.getRegistry().registerAll
			(
				SMALL_SHIP, LARGE_SHIP, DOJO
			);

		}
	}
	
}
