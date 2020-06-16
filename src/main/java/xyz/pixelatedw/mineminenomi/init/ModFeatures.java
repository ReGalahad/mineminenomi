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
import xyz.pixelatedw.mineminenomi.world.features.structures.smallship.marine.MarineSmallShipPieces;
import xyz.pixelatedw.mineminenomi.world.features.structures.smallship.marine.MarineSmallShipStructure;
import xyz.pixelatedw.wypi.WyRegistry;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModFeatures
{
	public static final Structure<NoFeatureConfig> BANDIT_CAMP = new BanditCampStructure();
	public static final Structure<NoFeatureConfig> MARINE_CAMP = new MarineCampStructure();
	public static final Structure<NoFeatureConfig> MARINE_SMALL_SHIP = new MarineSmallShipStructure();
	public static final Structure<NoFeatureConfig> DOJO = new DojoStructure();

	public static void init()
	{
		for (Biome biome : ForgeRegistries.BIOMES)
		{
			MarineSmallShipStructure.register(biome);
			// LargeShipStructure.register(biome);

			BanditCampStructure.register(biome);
			MarineCampStructure.register(biome);
			
			DojoStructure.register(biome);

			KairosekiOreFeature.register(biome);
		}
	}

	static
	{
		WyRegistry.registerFeature(BANDIT_CAMP, "Bandit Camp");
		WyRegistry.registerFeature(MARINE_CAMP, "Marine Camp");
		WyRegistry.registerFeature(MARINE_SMALL_SHIP, "Marine Small Ship");
		WyRegistry.registerFeature(DOJO, "Dojo");
	}

	public static class Pieces
	{
		public static final IStructurePieceType BANDIT_CAMP_PIECE = IStructurePieceType.register(BanditCampPieces.Piece::new, "bandit_camp_piece");
		public static final IStructurePieceType MARINE_CAMP_PIECE = IStructurePieceType.register(MarineCampPieces.Piece::new, "marine_camp_piece");
		public static final IStructurePieceType MARINE_SMALL_SHIP_PIECE = IStructurePieceType.register(MarineSmallShipPieces.Piece::new, "marine_small_ship_piece");
		public static final IStructurePieceType DOJO_PIECE = IStructurePieceType.register(DojoPiece::new, "dojo_piece");
	}
}
