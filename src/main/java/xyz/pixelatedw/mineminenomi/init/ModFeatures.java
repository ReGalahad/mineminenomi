package xyz.pixelatedw.mineminenomi.init;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.pixelatedw.mineminenomi.world.features.ores.KairosekiOreFeature;
import xyz.pixelatedw.mineminenomi.world.features.structures.camp.bandit.BanditCampPieces;
import xyz.pixelatedw.mineminenomi.world.features.structures.camp.bandit.BanditCampStructure;
import xyz.pixelatedw.mineminenomi.world.features.structures.camp.marine.MarineCampPieces;
import xyz.pixelatedw.mineminenomi.world.features.structures.camp.marine.MarineCampStructure;
import xyz.pixelatedw.mineminenomi.world.features.structures.dojo.DojoPiece;
import xyz.pixelatedw.mineminenomi.world.features.structures.dojo.DojoStructure;
import xyz.pixelatedw.mineminenomi.world.features.structures.largebase.marine.MarineLargeBasePieces;
import xyz.pixelatedw.mineminenomi.world.features.structures.largebase.marine.MarineLargeBaseStructure;
import xyz.pixelatedw.mineminenomi.world.features.structures.largeship.marine.MarineLargeShipPieces;
import xyz.pixelatedw.mineminenomi.world.features.structures.largeship.marine.MarineLargeShipStructure;
import xyz.pixelatedw.mineminenomi.world.features.structures.largeship.pirate.PirateLargeShipPieces;
import xyz.pixelatedw.mineminenomi.world.features.structures.largeship.pirate.PirateLargeShipStructure;
import xyz.pixelatedw.mineminenomi.world.features.structures.smallbase.bandit.BanditSmallBasePieces;
import xyz.pixelatedw.mineminenomi.world.features.structures.smallbase.bandit.BanditSmallBaseStructure;
import xyz.pixelatedw.mineminenomi.world.features.structures.smallship.marine.MarineSmallShipPieces;
import xyz.pixelatedw.mineminenomi.world.features.structures.smallship.marine.MarineSmallShipStructure;
import xyz.pixelatedw.mineminenomi.world.features.structures.smallship.pirate.PirateSmallShipPieces;
import xyz.pixelatedw.mineminenomi.world.features.structures.smallship.pirate.PirateSmallShipStructure;
import xyz.pixelatedw.wypi.WyRegistry;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModFeatures
{
	public static final Structure<NoFeatureConfig> MARINE_LARGE_BASE = new MarineLargeBaseStructure();
	public static final Structure<NoFeatureConfig> PIRATE_LARGE_SHIP = new PirateLargeShipStructure();
	public static final Structure<NoFeatureConfig> MARINE_LARGE_SHIP = new MarineLargeShipStructure();
	public static final Structure<NoFeatureConfig> BANDIT_SMALL_BASE = new BanditSmallBaseStructure();
	public static final Structure<NoFeatureConfig> BANDIT_CAMP = new BanditCampStructure();
	public static final Structure<NoFeatureConfig> MARINE_CAMP = new MarineCampStructure();
	public static final Structure<NoFeatureConfig> PIRATE_SMALL_SHIP = new PirateSmallShipStructure();
	public static final Structure<NoFeatureConfig> MARINE_SMALL_SHIP = new MarineSmallShipStructure();
	public static final Structure<NoFeatureConfig> DOJO = new DojoStructure();

	public static void init()
	{
		for (Biome biome : ForgeRegistries.BIOMES)
		{
			MarineLargeBaseStructure.register(biome);

			PirateLargeShipStructure.register(biome);
			MarineLargeShipStructure.register(biome);

			BanditSmallBaseStructure.register(biome);
			
			PirateSmallShipStructure.register(biome);
			MarineSmallShipStructure.register(biome);

			BanditCampStructure.register(biome);
			MarineCampStructure.register(biome);
			
			DojoStructure.register(biome);

			KairosekiOreFeature.register(biome);
		}
	}

	static
	{
		WyRegistry.registerFeature(MARINE_LARGE_BASE, "Marine Large Base");
		WyRegistry.registerFeature(PIRATE_LARGE_SHIP, "Pirate Large Ship");
		WyRegistry.registerFeature(MARINE_LARGE_SHIP, "Marine Large Ship");
		WyRegistry.registerFeature(BANDIT_SMALL_BASE, "Bandit Small Base");
		WyRegistry.registerFeature(BANDIT_CAMP, "Bandit Camp");
		WyRegistry.registerFeature(MARINE_CAMP, "Marine Camp");
		WyRegistry.registerFeature(PIRATE_SMALL_SHIP, "Pirate Small Ship");
		WyRegistry.registerFeature(MARINE_SMALL_SHIP, "Marine Small Ship");
		WyRegistry.registerFeature(DOJO, "Dojo");
	}

	public static class Pieces
	{
		public static final IStructurePieceType MARINE_LARGE_BASE_PIECE = IStructurePieceType.register(MarineLargeBasePieces.Piece::new, "marine_large_base_piece");
		public static final IStructurePieceType PIRATE_LARGE_SHIP_PIECE = IStructurePieceType.register(PirateLargeShipPieces.Piece::new, "pirate_large_ship_piece");
		public static final IStructurePieceType MARINE_LARGE_SHIP_PIECE = IStructurePieceType.register(MarineLargeShipPieces.Piece::new, "marine_large_ship_piece");
		public static final IStructurePieceType BANDIT_SMALL_BASE_PIECE = IStructurePieceType.register(BanditSmallBasePieces.Piece::new, "bandit_small_base_piece");
		public static final IStructurePieceType BANDIT_CAMP_PIECE = IStructurePieceType.register(BanditCampPieces.Piece::new, "bandit_camp_piece");
		public static final IStructurePieceType MARINE_CAMP_PIECE = IStructurePieceType.register(MarineCampPieces.Piece::new, "marine_camp_piece");
		public static final IStructurePieceType PIRATE_SMALL_SHIP_PIECE = IStructurePieceType.register(PirateSmallShipPieces.Piece::new, "pirate_small_ship_piece");
		public static final IStructurePieceType MARINE_SMALL_SHIP_PIECE = IStructurePieceType.register(MarineSmallShipPieces.Piece::new, "marine_small_ship_piece");
		public static final IStructurePieceType DOJO_PIECE = IStructurePieceType.register(DojoPiece::new, "dojo_piece");
	}
}
