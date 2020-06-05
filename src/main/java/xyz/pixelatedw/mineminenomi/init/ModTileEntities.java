package xyz.pixelatedw.mineminenomi.init;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.AblProtectionTileEntity;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.CannonTileEntity;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.CustomSpawnerTileEntity;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.DenDenMushiTileEntity;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.RoomTileEntity;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.TorikagoTileEntity;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.WantedPosterPackageTileEntity;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.WantedPosterTileEntity;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.dials.AxeDialTileEntity;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.dials.BreathDialTileEntity;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.dials.EisenDialTileEntity;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.dials.FlameDialTileEntity;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.dials.FlashDialTileEntity;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.dials.ImpactDialTileEntity;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.dials.MilkyDialTileEntity;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.dials.RejectDialTileEntity;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyRegistry;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModTileEntities
{
	public static final TileEntityType ROOM = WyRegistry.createTileEntity(RoomTileEntity::new, ModBlocks.OPE_MID).build(null);
	public static final TileEntityType TORIKAGO = WyRegistry.createTileEntity(TorikagoTileEntity::new, ModBlocks.STRING_MID).build(null);
	public static final TileEntityType CUSTOM_SPAWNER = WyRegistry.createTileEntity(CustomSpawnerTileEntity::new, ModBlocks.CUSTOM_SPAWNER).build(null);
	public static final TileEntityType WANTED_POSTER_PACKAGE = WyRegistry.createTileEntity(WantedPosterPackageTileEntity::new, ModBlocks.WANTED_POSTER_PACKAGE).build(null);
	public static final TileEntityType WANTED_POSTER = WyRegistry.createTileEntity(WantedPosterTileEntity::new, ModBlocks.WANTED_POSTER).build(null);
	public static final TileEntityType AXE_DIAL = WyRegistry.createTileEntity(AxeDialTileEntity::new, ModBlocks.AXE_DIAL).build(null);
	public static final TileEntityType BREATH_DIAL = WyRegistry.createTileEntity(BreathDialTileEntity::new, ModBlocks.BREATH_DIAL).build(null);
	public static final TileEntityType FLAME_DIAL = WyRegistry.createTileEntity(FlameDialTileEntity::new, ModBlocks.FLAME_DIAL).build(null);
	public static final TileEntityType REJECT_DIAL = WyRegistry.createTileEntity(RejectDialTileEntity::new, ModBlocks.REJECT_DIAL).build(null);
	public static final TileEntityType IMPACT_DIAL = WyRegistry.createTileEntity(ImpactDialTileEntity::new, ModBlocks.IMPACT_DIAL).build(null);
	public static final TileEntityType FLASH_DIAL = WyRegistry.createTileEntity(FlashDialTileEntity::new, ModBlocks.FLASH_DIAL).build(null);
	public static final TileEntityType EISEN_DIAL = WyRegistry.createTileEntity(EisenDialTileEntity::new, ModBlocks.EISEN_DIAL).build(null);
	public static final TileEntityType MILKY_DIAL = WyRegistry.createTileEntity(MilkyDialTileEntity::new, ModBlocks.MILKY_DIAL).build(null);
	public static final TileEntityType ABILITY_PROTECTION = WyRegistry.createTileEntity(AblProtectionTileEntity::new, ModBlocks.ABILITY_PROTECTION).build(null);
	public static final TileEntityType DEN_DEN_MUSHI = WyRegistry.createTileEntity(DenDenMushiTileEntity::new, ModBlocks.DEN_DEN_MUSHI).build(null);
	public static final TileEntityType CANNON = WyRegistry.createTileEntity(CannonTileEntity::new, ModBlocks.CANNON).build(null);

	static
	{
		WyRegistry.registerTileEntity(ROOM, "room");
		WyRegistry.registerTileEntity(TORIKAGO, "torikago");
		WyRegistry.registerTileEntity(CUSTOM_SPAWNER, "custom_spawner");
		WyRegistry.registerTileEntity(WANTED_POSTER_PACKAGE, "wanted_poster_package");
		WyRegistry.registerTileEntity(WANTED_POSTER, "wanted_poster");
		WyRegistry.registerTileEntity(AXE_DIAL, "axe_dial");
		WyRegistry.registerTileEntity(BREATH_DIAL, "breath_dial");
		WyRegistry.registerTileEntity(FLAME_DIAL, "flame_dial");
		WyRegistry.registerTileEntity(REJECT_DIAL, "reject_dial");
		WyRegistry.registerTileEntity(IMPACT_DIAL, "impact_dial");
		WyRegistry.registerTileEntity(FLASH_DIAL, "flash_dial");
		WyRegistry.registerTileEntity(EISEN_DIAL, "eisen_dial");
		WyRegistry.registerTileEntity(MILKY_DIAL, "milky_dial");
		WyRegistry.registerTileEntity(ABILITY_PROTECTION, "ability_protection");
		WyRegistry.registerTileEntity(DEN_DEN_MUSHI, "den_den_mushi");
		WyRegistry.registerTileEntity(CANNON, "cannon");
	}
}
