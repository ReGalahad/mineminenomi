package xyz.pixelatedw.mineminenomi.init;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.pixelatedw.mineminenomi.world.features.ores.KairosekiOreFeature;
import xyz.pixelatedw.mineminenomi.world.features.structures.dojo.DojoPiece;
import xyz.pixelatedw.mineminenomi.world.features.structures.dojo.DojoStructure;
import xyz.pixelatedw.mineminenomi.world.features.structures.smallship.SmallShipPieces;
import xyz.pixelatedw.mineminenomi.world.features.structures.smallship.SmallShipStructure;
import xyz.pixelatedw.wypi.WyRegistry;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModFeatures
{

	public static final Structure<NoFeatureConfig> SMALL_SHIP = new SmallShipStructure();
	public static final Structure<NoFeatureConfig> DOJO = new DojoStructure();

	public static void init()
	{
		for (Biome biome : ForgeRegistries.BIOMES)
		{
			//SmallShipStructure.register(biome);
			//LargeShipStructure.register(biome);
				
			DojoStructure.register(biome);
			
			KairosekiOreFeature.register(biome);
		}
	}
	
	public static class Pieces
	{
		public static final IStructurePieceType SMALL_SHIP_MARINE_MAST = IStructurePieceType.register(SmallShipPieces.Piece::new, "small_ship_marine_mast");
		public static final IStructurePieceType SMALL_SHIP_MARINE_BACK = IStructurePieceType.register(SmallShipPieces.Piece::new, "small_ship_marine_back");
		public static final IStructurePieceType SMALL_SHIP_MARINE_MIDDLE = IStructurePieceType.register(SmallShipPieces.Piece::new, "small_ship_marine_middle");
		public static final IStructurePieceType SMALL_SHIP_MARINE_FRONT = IStructurePieceType.register(SmallShipPieces.Piece::new, "small_ship_marine_front");
		public static final IStructurePieceType DOJO_BODY = IStructurePieceType.register(DojoPiece::new, "dojo_body");
	}
	
	static
	{
		WyRegistry.registerFeature(DOJO, "Dojo");
	}
}
