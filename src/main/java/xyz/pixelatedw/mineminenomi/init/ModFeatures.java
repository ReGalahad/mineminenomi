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
import xyz.pixelatedw.mineminenomi.world.features.structures.smallship.marine.MarineSmallShipPieces;
import xyz.pixelatedw.mineminenomi.world.features.structures.smallship.marine.MarineSmallShipStructure;
import xyz.pixelatedw.wypi.WyRegistry;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModFeatures
{

	public static final Structure<NoFeatureConfig> MARINE_SMALL_SHIP = new MarineSmallShipStructure();
	public static final Structure<NoFeatureConfig> DOJO = new DojoStructure();

	public static void init()
	{
		for (Biome biome : ForgeRegistries.BIOMES)
		{
			MarineSmallShipStructure.register(biome);
			//LargeShipStructure.register(biome);
				
			DojoStructure.register(biome);
			
			KairosekiOreFeature.register(biome);
		}
	}
		
	static
	{
		WyRegistry.registerFeature(DOJO, "Dojo");
		WyRegistry.registerFeature(MARINE_SMALL_SHIP, "Marine Small Ship");
	}
	
	public static class Pieces
	{
		public static final IStructurePieceType MARINE_SMALL_SHIP_BODY = IStructurePieceType.register(MarineSmallShipPieces.Piece::new, "small_ship_body");
		public static final IStructurePieceType DOJO_BODY = IStructurePieceType.register(DojoPiece::new, "dojo_body");
	}
}
