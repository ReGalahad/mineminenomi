package xyz.pixelatedw.mineminenomi.init;

import net.minecraft.block.Block;
import net.minecraft.block.Block.Properties;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.pixelatedw.mineminenomi.Env;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.api.json.loottables.block.JSONLootTableSimpleBlock;
import xyz.pixelatedw.mineminenomi.api.json.models.block.JSONModelBars;
import xyz.pixelatedw.mineminenomi.api.json.models.block.JSONModelThinBlock;
import xyz.pixelatedw.mineminenomi.api.json.models.item.JSONModelSimpleItem;
import xyz.pixelatedw.mineminenomi.blocks.AblProtectionBlock;
import xyz.pixelatedw.mineminenomi.blocks.BarrierBlock;
import xyz.pixelatedw.mineminenomi.blocks.CustomBarsBlock;
import xyz.pixelatedw.mineminenomi.blocks.CustomSpawnerBlock;
import xyz.pixelatedw.mineminenomi.blocks.DarknessBlock;
import xyz.pixelatedw.mineminenomi.blocks.DemonPoisonBlock;
import xyz.pixelatedw.mineminenomi.blocks.KageBlock;
import xyz.pixelatedw.mineminenomi.blocks.KairosekiOreBlock;
import xyz.pixelatedw.mineminenomi.blocks.OpeBlock;
import xyz.pixelatedw.mineminenomi.blocks.OpeMidBlock;
import xyz.pixelatedw.mineminenomi.blocks.PoisonBlock;
import xyz.pixelatedw.mineminenomi.blocks.SkyBlockBlock;
import xyz.pixelatedw.mineminenomi.blocks.StringMidBlock;
import xyz.pixelatedw.mineminenomi.blocks.StringWallBlock;
import xyz.pixelatedw.mineminenomi.blocks.SunaSandBlock;
import xyz.pixelatedw.mineminenomi.blocks.WantedPosterBlock;
import xyz.pixelatedw.mineminenomi.blocks.WantedPosterPackageBlock;
import xyz.pixelatedw.mineminenomi.blocks.dials.AxeDialBlock;
import xyz.pixelatedw.mineminenomi.blocks.dials.BreathDialBlock;
import xyz.pixelatedw.mineminenomi.blocks.dials.EisenDialBlock;
import xyz.pixelatedw.mineminenomi.blocks.dials.FlameDialBlock;
import xyz.pixelatedw.mineminenomi.blocks.dials.FlashDialBlock;
import xyz.pixelatedw.mineminenomi.blocks.dials.ImpactDialBlock;
import xyz.pixelatedw.mineminenomi.blocks.dials.MilkyDialBlock;
import xyz.pixelatedw.mineminenomi.blocks.dials.RejectDialBlock;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.AblProtectionTileEntity;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.CustomSpawnerTileEntity;
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
import xyz.pixelatedw.mineminenomi.items.WantedPosterItem;
import xyz.pixelatedw.mineminenomi.items.dials.AxeDialItem;
import xyz.pixelatedw.mineminenomi.items.dials.BreathDialItem;
import xyz.pixelatedw.mineminenomi.items.dials.EisenDialItem;
import xyz.pixelatedw.mineminenomi.items.dials.FlameDialItem;
import xyz.pixelatedw.mineminenomi.items.dials.FlashDialItem;
import xyz.pixelatedw.mineminenomi.items.dials.ImpactDialItem;
import xyz.pixelatedw.mineminenomi.items.dials.MilkyDialItem;
import xyz.pixelatedw.mineminenomi.items.dials.RejectDialItem;

@Mod.EventBusSubscriber(modid = Env.PROJECT_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBlocks
{

	// Devil Fruit created blocks	
	public static final Block OPE = new OpeBlock();
	public static final Block OPE_MID = new OpeMidBlock();
	public static final Block KAGE = new KageBlock();
	public static final Block SUNA_SAND = new SunaSandBlock();
	public static final Block WAX = new Block(Properties.create(Material.CLAY).hardnessAndResistance(2));
	public static final Block POISON = new PoisonBlock();
	public static final Block DEMON_POISON = new DemonPoisonBlock();
	public static final Block STRING_WALL = new StringWallBlock();
	public static final Block STRING_MID = new StringMidBlock();
	public static final Block BARRIER = new BarrierBlock();
	public static final Block DARKNESS = new DarknessBlock();
	public static final Block ORI_BARS = new CustomBarsBlock();

	// Other blocks
	public static final Block KAIROSEKI = new Block(Properties.create(Material.ROCK).hardnessAndResistance(10));
	public static final Block KAIROSEKI_ORE = new KairosekiOreBlock();
	public static final Block KAIROSEKI_BARS = new CustomBarsBlock();	
	public static final Block SKY_BLOCK = new SkyBlockBlock();
	public static final Block WANTED_POSTER = new WantedPosterBlock();
	public static final Block WANTED_POSTER_PACKAGE = new WantedPosterPackageBlock();

	//public static final Block DenDenMushi = new BlockDenDenMushi();
	
	public static final CustomSpawnerBlock CUSTOM_SPAWNER = new CustomSpawnerBlock();

	// Dials
	public static final Block AXE_DIAL = new AxeDialBlock();
	public static final Block IMPACT_DIAL = new ImpactDialBlock();
	public static final Block FLASH_DIAL = new FlashDialBlock();
	public static final Block BREATH_DIAL = new BreathDialBlock();
	public static final Block EISEN_DIAL = new EisenDialBlock();
	public static final Block MILKY_DIAL = new MilkyDialBlock();
	public static final Block FLAME_DIAL = new FlameDialBlock();
	public static final Block REJECT_DIAL = new RejectDialBlock();

	// Ability Protection
	public static final Block ABILITY_PROTECTION = new AblProtectionBlock();
	/*
	public static final Block AbilityProtectionAreaBlock = new BlockAbilityProtectionArea();
	public static final Block AbilityProtectionCenterBlock = new BlockAbilityProtectionArea();
	*/

	@SubscribeEvent
    public static void registerBlocks(final RegistryEvent.Register<Block> event)
    {
		// Register the blocks themselves
        event.getRegistry().registerAll
        (
        	WyRegistry.registerBlock(OPE, "Ope"),
        	WyRegistry.registerBlock(OPE_MID, "Ope Mid"),
        	WyRegistry.registerBlock(KAIROSEKI, "Kairoseki Block"),
        	WyRegistry.registerBlock(KAIROSEKI_ORE, "Kairoseki Ore"),
        	WyRegistry.registerBlock(SKY_BLOCK, "Sky Block"),
        	WyRegistry.registerBlock(KAIROSEKI_BARS, "Kairoseki Bars", new JSONModelBars("Kairoseki Bars")),
        	WyRegistry.registerBlock(KAGE, "Kage Block"),
        	WyRegistry.registerBlock(SUNA_SAND, "Suna Sand"),
        	WyRegistry.registerBlock(WAX, "Wax Block"),
        	WyRegistry.registerBlock(POISON, "Poison", new JSONModelThinBlock("Poison")),
        	WyRegistry.registerBlock(DEMON_POISON, "Demon Poison", new JSONModelThinBlock("Demon Poison")),
        	WyRegistry.registerBlock(STRING_WALL, "String Wall"),
        	WyRegistry.registerBlock(STRING_MID, "String Mid"),
        	WyRegistry.registerBlock(BARRIER, "Barrier"),
        	WyRegistry.registerBlock(DARKNESS, "Darkness"),
        	WyRegistry.registerBlock(ORI_BARS, "Ori Bars", new JSONModelBars("Ori Bars")),
        	WyRegistry.registerBlock(CUSTOM_SPAWNER, "Custom Spawner"),
        	WyRegistry.registerBlock(WANTED_POSTER_PACKAGE, "Wanted Poster Package"),
        	WyRegistry.registerBlock(WANTED_POSTER, "Wanted Poster"),
        	WyRegistry.registerBlock(AXE_DIAL, "Axe Dial"),
        	WyRegistry.registerBlock(BREATH_DIAL, "Breath Dial"),
        	WyRegistry.registerBlock(FLAME_DIAL, "Flame Dial"),
        	WyRegistry.registerBlock(REJECT_DIAL, "Reject Dial"),
        	WyRegistry.registerBlock(IMPACT_DIAL, "Impact Dial"),
        	WyRegistry.registerBlock(FLASH_DIAL, "Flash Dial"),
        	WyRegistry.registerBlock(EISEN_DIAL, "Eisen Dial"),
        	WyRegistry.registerBlock(MILKY_DIAL, "Milky Dial"),
        	WyRegistry.registerBlock(ABILITY_PROTECTION, "Ability Protection")
        );

        // Register loot tables
        //Ore
        WyRegistry.registerLootTable(KAIROSEKI_ORE, new JSONLootTableSimpleBlock("Kairoseki", 3, 6));
        
        //Self Drop
        WyRegistry.registerLootTable(KAIROSEKI, new JSONLootTableSimpleBlock("Kairoseki Block"));
        WyRegistry.registerLootTable(KAIROSEKI_BARS, new JSONLootTableSimpleBlock("Kairoseki Bars"));
        WyRegistry.registerLootTable(AXE_DIAL, new JSONLootTableSimpleBlock("Axe Dial"));
        WyRegistry.registerLootTable(BREATH_DIAL, new JSONLootTableSimpleBlock("Breath Dial"));
        WyRegistry.registerLootTable(FLAME_DIAL, new JSONLootTableSimpleBlock("Flame Dial"));    
        WyRegistry.registerLootTable(REJECT_DIAL, new JSONLootTableSimpleBlock("Reject Dial"));
        WyRegistry.registerLootTable(IMPACT_DIAL, new JSONLootTableSimpleBlock("Impact Dial"));
        WyRegistry.registerLootTable(FLASH_DIAL, new JSONLootTableSimpleBlock("Flash Dial"));
        WyRegistry.registerLootTable(EISEN_DIAL, new JSONLootTableSimpleBlock("Eisen Dial"));
        WyRegistry.registerLootTable(MILKY_DIAL, new JSONLootTableSimpleBlock("Milky Dial"));
    }
	
	@SubscribeEvent
	public static void registerTileEntities(RegistryEvent.Register<TileEntityType<?>> event) 
	{
		if (!event.getName().equals(ForgeRegistries.TILE_ENTITIES.getRegistryName()))
			return;
		
		event.getRegistry().registerAll
		(
			RoomTileEntity.TILE_ENTITY,
			TorikagoTileEntity.TILE_ENTITY,
			CustomSpawnerTileEntity.TILE_ENTITY,
			WantedPosterPackageTileEntity.TILE_ENTITY,
			WantedPosterTileEntity.TILE_ENTITY,
			AxeDialTileEntity.TILE_ENTITY,
			BreathDialTileEntity.TILE_ENTITY,
			FlameDialTileEntity.TILE_ENTITY,
			RejectDialTileEntity.TILE_ENTITY,
			ImpactDialTileEntity.TILE_ENTITY,
			FlashDialTileEntity.TILE_ENTITY,
			EisenDialTileEntity.TILE_ENTITY,
			MilkyDialTileEntity.TILE_ENTITY,
			AblProtectionTileEntity.TILE_ENTITY

		);
	}
	
	@SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event)
    {
        event.getRegistry().registerAll
        (
        	WyRegistry.registerItemBlock(OPE, false),
        	WyRegistry.registerItemBlock(OPE_MID, false),
        	WyRegistry.registerItemBlock(KAIROSEKI, true),
        	WyRegistry.registerItemBlock(KAIROSEKI_ORE, true),
        	WyRegistry.registerItemBlock(SKY_BLOCK, true),
        	WyRegistry.registerItemBlock(KAIROSEKI_BARS, true, new JSONModelSimpleItem("Kairoseki Bars")),
        	WyRegistry.registerItemBlock(KAGE, false),
        	WyRegistry.registerItemBlock(SUNA_SAND, false),
        	WyRegistry.registerItemBlock(WAX, false),
        	WyRegistry.registerItemBlock(POISON, false),
        	WyRegistry.registerItemBlock(DEMON_POISON , false),
        	WyRegistry.registerItemBlock(STRING_WALL, false),
        	WyRegistry.registerItemBlock(STRING_MID, false),
        	WyRegistry.registerItemBlock(BARRIER, false),
        	WyRegistry.registerItemBlock(DARKNESS, false),
        	WyRegistry.registerItemBlock(ORI_BARS, false, new JSONModelSimpleItem("Ori Bars")),
        	WyRegistry.registerItemBlock(CUSTOM_SPAWNER, false),
        	WyRegistry.registerItemBlock(WANTED_POSTER_PACKAGE, true, new JSONModelSimpleItem("Wanted Poster Package")),
        	WyRegistry.registerCustomItemBlock(WANTED_POSTER, new WantedPosterItem()),
        	WyRegistry.registerCustomItemBlock(AXE_DIAL, new AxeDialItem()),
        	WyRegistry.registerCustomItemBlock(BREATH_DIAL, new BreathDialItem()),
        	WyRegistry.registerCustomItemBlock(FLAME_DIAL, new FlameDialItem()),
        	WyRegistry.registerCustomItemBlock(REJECT_DIAL, new RejectDialItem()),
        	WyRegistry.registerCustomItemBlock(IMPACT_DIAL, new ImpactDialItem()),
        	WyRegistry.registerCustomItemBlock(FLASH_DIAL, new FlashDialItem()),
        	WyRegistry.registerCustomItemBlock(EISEN_DIAL, new EisenDialItem()),
        	WyRegistry.registerCustomItemBlock(MILKY_DIAL, new MilkyDialItem())

        );
    }
}
