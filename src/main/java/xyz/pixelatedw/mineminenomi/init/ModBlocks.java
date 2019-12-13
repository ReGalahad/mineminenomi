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
import xyz.pixelatedw.mineminenomi.values.ModValuesEnv;

@Mod.EventBusSubscriber(modid = ModValuesEnv.PROJECT_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBlocks
{

	// Devil Fruit created blocks	
	public static Block ope = new OpeBlock();
	public static Block opeMid = new OpeMidBlock();
	public static Block kageBlock = new KageBlock();
	public static Block sunaSand = new SunaSandBlock();
	public static Block waxBlock = new Block(Properties.create(Material.CLAY).hardnessAndResistance(2));
	public static Block poison = new PoisonBlock();
	public static Block demonPoison = new DemonPoisonBlock();
	public static Block stringWall = new StringWallBlock();
	public static Block stringMid = new StringMidBlock();
	public static Block barrier = new BarrierBlock();
	public static Block darkness = new DarknessBlock();
	public static Block oriBars = new CustomBarsBlock();

	// Other blocks
	public static Block kairosekiBlock = new Block(Properties.create(Material.ROCK).hardnessAndResistance(10));
	public static Block kairosekiOre = new KairosekiOreBlock();
	public static Block kairosekiBars = new CustomBarsBlock();	
	public static Block skyBlock = new SkyBlockBlock();
	public static Block wantedPoster = new WantedPosterBlock();
	public static Block wantedPosterPackage = new WantedPosterPackageBlock();

	//public static Block DenDenMushi = new BlockDenDenMushi();
	
	public static CustomSpawnerBlock customSpawner = new CustomSpawnerBlock();

	// Dials
	public static Block axeDialBlock = new AxeDialBlock();
	public static Block impactDialBlock = new ImpactDialBlock();
	public static Block flashDialBlock = new FlashDialBlock();
	public static Block breathDialBlock = new BreathDialBlock();
	public static Block eisenDialBlock = new EisenDialBlock();
	public static Block milkyDialBlock = new MilkyDialBlock();
	public static Block flameDialBlock = new FlameDialBlock();
	public static Block rejectDialBlock = new RejectDialBlock();

	// Ability Protection
	public static Block abilityProtection = new AblProtectionBlock();
	/*
	public static Block AbilityProtectionAreaBlock = new BlockAbilityProtectionArea();
	public static Block AbilityProtectionCenterBlock = new BlockAbilityProtectionArea();
	*/

	@SubscribeEvent
    public static void registerBlocks(final RegistryEvent.Register<Block> event)
    {
		// Register the blocks themselves
        event.getRegistry().registerAll
        (
        	WyRegistry.registerBlock(ope, "Ope"),
        	WyRegistry.registerBlock(opeMid, "Ope Mid"),
        	WyRegistry.registerBlock(kairosekiBlock, "Kairoseki Block"),
        	WyRegistry.registerBlock(kairosekiOre, "Kairoseki Ore"),
        	WyRegistry.registerBlock(skyBlock, "Sky Block"),
        	WyRegistry.registerBlock(kairosekiBars, "Kairoseki Bars", new JSONModelBars("Kairoseki Bars")),
        	WyRegistry.registerBlock(kageBlock, "Kage Block"),
        	WyRegistry.registerBlock(sunaSand, "Suna Sand"),
        	WyRegistry.registerBlock(waxBlock, "Wax Block"),
        	WyRegistry.registerBlock(poison, "Poison", new JSONModelThinBlock("Poison")),
        	WyRegistry.registerBlock(demonPoison, "Demon Poison", new JSONModelThinBlock("Demon Poison")),
        	WyRegistry.registerBlock(stringWall, "String Wall"),
        	WyRegistry.registerBlock(stringMid, "String Mid"),
        	WyRegistry.registerBlock(barrier, "Barrier"),
        	WyRegistry.registerBlock(darkness, "Darkness"),
        	WyRegistry.registerBlock(oriBars, "Ori Bars", new JSONModelBars("Ori Bars")),
        	WyRegistry.registerBlock(customSpawner, "Custom Spawner"),
        	WyRegistry.registerBlock(wantedPosterPackage, "Wanted Poster Package"),
        	WyRegistry.registerBlock(wantedPoster, "Wanted Poster"),
        	WyRegistry.registerBlock(axeDialBlock, "Axe Dial"),
        	WyRegistry.registerBlock(breathDialBlock, "Breath Dial"),
        	WyRegistry.registerBlock(flameDialBlock, "Flame Dial"),
        	WyRegistry.registerBlock(rejectDialBlock, "Reject Dial"),
        	WyRegistry.registerBlock(impactDialBlock, "Impact Dial"),
        	WyRegistry.registerBlock(flashDialBlock, "Flash Dial"),
        	WyRegistry.registerBlock(eisenDialBlock, "Eisen Dial"),
        	WyRegistry.registerBlock(milkyDialBlock, "Milky Dial"),
        	WyRegistry.registerBlock(abilityProtection, "Ability Protection")
        );

        // Register loot tables
        //Ore
        WyRegistry.registerLootTable(kairosekiOre, new JSONLootTableSimpleBlock("Kairoseki", 3, 6));
        
        //Self Drop
        WyRegistry.registerLootTable(kairosekiBlock, new JSONLootTableSimpleBlock("Kairoseki Block"));
        WyRegistry.registerLootTable(kairosekiBars, new JSONLootTableSimpleBlock("Kairoseki Bars"));
        WyRegistry.registerLootTable(axeDialBlock, new JSONLootTableSimpleBlock("Axe Dial"));
        WyRegistry.registerLootTable(breathDialBlock, new JSONLootTableSimpleBlock("Breath Dial"));
        WyRegistry.registerLootTable(flameDialBlock, new JSONLootTableSimpleBlock("Flame Dial"));    
        WyRegistry.registerLootTable(rejectDialBlock, new JSONLootTableSimpleBlock("Reject Dial"));
        WyRegistry.registerLootTable(impactDialBlock, new JSONLootTableSimpleBlock("Impact Dial"));
        WyRegistry.registerLootTable(flashDialBlock, new JSONLootTableSimpleBlock("Flash Dial"));
        WyRegistry.registerLootTable(eisenDialBlock, new JSONLootTableSimpleBlock("Eisen Dial"));
        WyRegistry.registerLootTable(milkyDialBlock, new JSONLootTableSimpleBlock("Milky Dial"));
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
        	WyRegistry.registerItemBlock(ope, false),
        	WyRegistry.registerItemBlock(opeMid, false),
        	WyRegistry.registerItemBlock(kairosekiBlock, true),
        	WyRegistry.registerItemBlock(kairosekiOre, true),
        	WyRegistry.registerItemBlock(skyBlock, true),
        	WyRegistry.registerItemBlock(kairosekiBars, true, new JSONModelSimpleItem("Kairoseki Bars")),
        	WyRegistry.registerItemBlock(kageBlock, false),
        	WyRegistry.registerItemBlock(sunaSand, false),
        	WyRegistry.registerItemBlock(waxBlock, false),
        	WyRegistry.registerItemBlock(poison, false),
        	WyRegistry.registerItemBlock(demonPoison , false),
        	WyRegistry.registerItemBlock(stringWall, false),
        	WyRegistry.registerItemBlock(stringMid, false),
        	WyRegistry.registerItemBlock(barrier, false),
        	WyRegistry.registerItemBlock(darkness, false),
        	WyRegistry.registerItemBlock(oriBars, false, new JSONModelSimpleItem("Ori Bars")),
        	WyRegistry.registerItemBlock(customSpawner, false),
        	WyRegistry.registerItemBlock(wantedPosterPackage, true, new JSONModelSimpleItem("Wanted Poster Package")),
        	WyRegistry.registerCustomItemBlock(wantedPoster, new WantedPosterItem()),
        	WyRegistry.registerCustomItemBlock(axeDialBlock, new AxeDialItem()),
        	WyRegistry.registerCustomItemBlock(breathDialBlock, new BreathDialItem()),
        	WyRegistry.registerCustomItemBlock(flameDialBlock, new FlameDialItem()),
        	WyRegistry.registerCustomItemBlock(rejectDialBlock, new RejectDialItem()),
        	WyRegistry.registerCustomItemBlock(impactDialBlock, new ImpactDialItem()),
        	WyRegistry.registerCustomItemBlock(flashDialBlock, new FlashDialItem()),
        	WyRegistry.registerCustomItemBlock(eisenDialBlock, new EisenDialItem()),
        	WyRegistry.registerCustomItemBlock(milkyDialBlock, new MilkyDialItem())

        );
    }
}
