package xyz.pixelatedw.mineminenomi.init;

import net.minecraft.block.Block;
import net.minecraft.block.Block.Properties;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.blocks.AblProtectionBlock;
import xyz.pixelatedw.mineminenomi.blocks.BarrierBlock;
import xyz.pixelatedw.mineminenomi.blocks.CannonBlock;
import xyz.pixelatedw.mineminenomi.blocks.CustomBarsBlock;
import xyz.pixelatedw.mineminenomi.blocks.CustomSpawnerBlock;
import xyz.pixelatedw.mineminenomi.blocks.DarknessBlock;
import xyz.pixelatedw.mineminenomi.blocks.DemonPoisonBlock;
import xyz.pixelatedw.mineminenomi.blocks.DenDenMushiBlock;
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
import xyz.pixelatedw.mineminenomi.items.WantedPosterItem;
import xyz.pixelatedw.mineminenomi.items.dials.AxeDialItem;
import xyz.pixelatedw.mineminenomi.items.dials.BreathDialItem;
import xyz.pixelatedw.mineminenomi.items.dials.EisenDialItem;
import xyz.pixelatedw.mineminenomi.items.dials.FlameDialItem;
import xyz.pixelatedw.mineminenomi.items.dials.FlashDialItem;
import xyz.pixelatedw.mineminenomi.items.dials.ImpactDialItem;
import xyz.pixelatedw.mineminenomi.items.dials.MilkyDialItem;
import xyz.pixelatedw.mineminenomi.items.dials.RejectDialItem;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.WyRegistry;
import xyz.pixelatedw.wypi.json.loottables.block.JSONLootTableSimpleBlock;
import xyz.pixelatedw.wypi.json.models.JSONModelItem;
import xyz.pixelatedw.wypi.json.models.block.JSONModelBars;
import xyz.pixelatedw.wypi.json.models.block.JSONModelThinBlock;
import xyz.pixelatedw.wypi.json.models.item.JSONModelItemBlock;
import xyz.pixelatedw.wypi.json.models.item.JSONModelSimpleItem;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBlocks
{
	// Devil Fruit created blocks
	public static final Block OPE = new OpeBlock();
	public static final Block OPE_MID = new OpeMidBlock();
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
	public static final Block CUSTOM_SPAWNER = new CustomSpawnerBlock();
	public static final Block DEN_DEN_MUSHI = new DenDenMushiBlock();
	public static final Block CANNON = new CannonBlock();

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

	static
	{
		// Blocks
		WyRegistry.registerBlock(OPE, "Ope");
		WyRegistry.registerBlock(OPE_MID, "Ope Mid");
		WyRegistry.registerBlock(KAIROSEKI, "Kairoseki Block");
		WyRegistry.registerBlock(KAIROSEKI_ORE, "Kairoseki Ore");
		WyRegistry.registerBlock(SKY_BLOCK, "Sky Block");
		WyRegistry.registerBlock(KAIROSEKI_BARS, "Kairoseki Bars", new JSONModelBars("Kairoseki Bars"));
		WyRegistry.registerBlock(SUNA_SAND, "Suna Sand");
		WyRegistry.registerBlock(WAX, "Wax Block");
		WyRegistry.registerBlock(POISON, "Poison", new JSONModelThinBlock("Poison"));
		WyRegistry.registerBlock(DEMON_POISON, "Demon Poison", new JSONModelThinBlock("Demon Poison"));
		WyRegistry.registerBlock(STRING_WALL, "String Wall");
		WyRegistry.registerBlock(STRING_MID, "String Mid");
		WyRegistry.registerBlock(BARRIER, "Barrier");
		WyRegistry.registerBlock(DARKNESS, "Darkness");
		WyRegistry.registerBlock(ORI_BARS, "Ori Bars", new JSONModelBars("Ori Bars"));
		WyRegistry.registerBlock(CUSTOM_SPAWNER, "Custom Spawner");
		WyRegistry.registerBlock(WANTED_POSTER_PACKAGE, "Wanted Poster Package");
		WyRegistry.registerBlock(WANTED_POSTER, "Wanted Poster");
		WyRegistry.registerBlock(AXE_DIAL, "Axe Dial");
		WyRegistry.registerBlock(BREATH_DIAL, "Breath Dial");
		WyRegistry.registerBlock(FLAME_DIAL, "Flame Dial");
		WyRegistry.registerBlock(REJECT_DIAL, "Reject Dial");
		WyRegistry.registerBlock(IMPACT_DIAL, "Impact Dial");
		WyRegistry.registerBlock(FLASH_DIAL, "Flash Dial");
		WyRegistry.registerBlock(EISEN_DIAL, "Eisen Dial");
		WyRegistry.registerBlock(MILKY_DIAL, "Milky Dial");
		WyRegistry.registerBlock(ABILITY_PROTECTION, "Ability Protection");
		WyRegistry.registerBlock(DEN_DEN_MUSHI, "Den Den Mushi");
		WyRegistry.registerBlock(CANNON, "Cannon");
		
		// Item Blocks
		registerItemBlock(OPE, "Ope", false);
		registerItemBlock(OPE_MID, "Ope Mid", false);
		registerItemBlock(KAIROSEKI, "Kairoseki Block", true);
		registerItemBlock(KAIROSEKI_ORE, "Kairoseki Ore", true);
		registerItemBlock(SKY_BLOCK, "Sky Block", true);
		registerItemBlock(KAIROSEKI_BARS, "Kairoseki Bars", true, new JSONModelSimpleItem("Kairoseki Bars"));
		registerItemBlock(SUNA_SAND, "Suna Sand", false);
		registerItemBlock(WAX, "Wax Block", false);
		registerItemBlock(POISON, "Poison", false);
		registerItemBlock(DEMON_POISON, "Demon Poison", false);
		registerItemBlock(STRING_WALL, "String Wall", false);
		registerItemBlock(STRING_MID, "String Mid", false);
		registerItemBlock(BARRIER, "Barrier", false);
		registerItemBlock(DARKNESS, "Darkness", false);
		registerItemBlock(ORI_BARS, "Ori Bars", false, new JSONModelSimpleItem("Ori Bars"));
		registerItemBlock(CUSTOM_SPAWNER, "Custom Spawner", false);
		registerItemBlock(WANTED_POSTER_PACKAGE, "Wanted Poster Package", true, new JSONModelSimpleItem("Wanted Poster Package"));
		registerItemBlock(DEN_DEN_MUSHI, "Den Den Mushi", true, new JSONModelSimpleItem("Den Den Mushi"));
		registerItemBlock(CANNON, "Cannon", true, new JSONModelSimpleItem("Cannon"));
		registerCustomItemBlock(WANTED_POSTER, "Wanted Poster", new WantedPosterItem());
		registerCustomItemBlock(AXE_DIAL, "Axe Dial", new AxeDialItem());
		registerCustomItemBlock(BREATH_DIAL, "Breath Dial", new BreathDialItem());
		registerCustomItemBlock(FLAME_DIAL, "Flame Dial", new FlameDialItem());
		registerCustomItemBlock(REJECT_DIAL, "Reject Dial", new RejectDialItem());
		registerCustomItemBlock(IMPACT_DIAL, "Impact Dial", new ImpactDialItem());
		registerCustomItemBlock(FLASH_DIAL, "Flash Dial", new FlashDialItem());
		registerCustomItemBlock(EISEN_DIAL, "Eisen Dial", new EisenDialItem());
		registerCustomItemBlock(MILKY_DIAL, "Milky Dial", new MilkyDialItem());

		// Register loot tables
		// Ore
		WyRegistry.registerLootTable(KAIROSEKI_ORE, new JSONLootTableSimpleBlock("Kairoseki", 3, 6));

		// Self Drop
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
		WyRegistry.registerLootTable(DEN_DEN_MUSHI, new JSONLootTableSimpleBlock("Den Den Mushi"));
		WyRegistry.registerLootTable(CANNON, new JSONLootTableSimpleBlock("Cannon"));
	}

	public static Item registerItemBlock(Block block, String localizedName, boolean isInCreative)
	{
		String resourceName = WyHelper.getResourceName(localizedName);
		return registerItemBlock(block, localizedName, isInCreative, new JSONModelItemBlock(resourceName));
	}

	private static Item registerItemBlock(Block block, String localizedName, boolean isInCreative, JSONModelItem jsonType)
	{
		String resourceName = WyHelper.getResourceName(localizedName);
		if (isInCreative)
			return WyRegistry.registerItem(new BlockItem(block, new Item.Properties().group(ModCreativeTabs.MISC)), resourceName, jsonType);
		else
			return WyRegistry.registerItem(new BlockItem(block, new Item.Properties()), resourceName, jsonType);
	}

	public static <T extends BlockItem> T registerCustomItemBlock(Block block, String localizedName, T itemBlock)
	{
		String resourceName = WyHelper.getResourceName(localizedName);
		WyRegistry.registerItem(itemBlock, resourceName, new JSONModelSimpleItem(resourceName));

		return itemBlock;
	}
}
