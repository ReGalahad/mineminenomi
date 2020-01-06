package xyz.pixelatedw.mineminenomi.api;

import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Function;
import java.util.function.Supplier;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.particles.IParticleData.IDeserializer;
import net.minecraft.particles.ParticleType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.Env;
import xyz.pixelatedw.mineminenomi.api.json.loottables.IJSONLootTable;
import xyz.pixelatedw.mineminenomi.api.json.models.JSONModelBlock;
import xyz.pixelatedw.mineminenomi.api.json.models.JSONModelItem;
import xyz.pixelatedw.mineminenomi.api.json.models.block.JSONModelSimpleBlock;
import xyz.pixelatedw.mineminenomi.api.json.models.item.JSONModelItemBlock;
import xyz.pixelatedw.mineminenomi.api.json.models.item.JSONModelSimpleItem;
import xyz.pixelatedw.mineminenomi.api.json.models.item.JSONModelSpawnEgg;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;

public class WyRegistry
{

	private static int entityID = 200;
	private static int packetID = 0;

	public static HashMap<Item, JSONModelItem> items = new HashMap<Item, JSONModelItem>();
	public static HashMap<Block, JSONModelBlock> blocks = new HashMap<Block, JSONModelBlock>();
	public static HashMap<Object, IJSONLootTable> lootTables = new HashMap<Object, IJSONLootTable>();
	public static HashMap<String, String> langMap = new HashMap<String, String>();

	public static ParticleType registerGenericParticleType(String id)
	{
		return registerGenericParticleType(id, GenericParticleData.DESERIALIZER);
	}
	
	public static ParticleType registerGenericParticleType(String id, IDeserializer<?> deserializer)
	{
		return new ParticleType<>(true, deserializer).setRegistryName(Env.PROJECT_ID, id);
	}
	
	public static void registerLootTable(Object obj, IJSONLootTable json)
	{
		lootTables.put(obj, json);
	}
	
	public static void registerName(String key, String localizedName)
	{
		langMap.put(key, localizedName);
	}
	
	public static BlockItem registerItemBlock(Block block, boolean isInCreative)
	{
		return registerItemBlock(block, isInCreative, new JSONModelItemBlock(block.getRegistryName().getPath()) );
	}
	
	public static BlockItem registerItemBlock(Block block, boolean isInCreative, JSONModelItem jsonType)
	{
		BlockItem itemBlock;
		if(isInCreative)
			itemBlock = new BlockItem(block, new Properties().group(ModCreativeTabs.MISC));
		else
			itemBlock = new BlockItem(block, new Properties());
		itemBlock.setRegistryName(block.getRegistryName());
		
		items.put(itemBlock, jsonType);
		
		return itemBlock;
	}
	
	public static <T extends BlockItem> T registerCustomItemBlock(Block block, T itemBlock)
	{
		itemBlock.setRegistryName(block.getRegistryName());

		items.put(itemBlock, new JSONModelSimpleItem(block.getRegistryName().getPath()));
		
		return itemBlock;		
	}
	
	public static Block registerBlock(Block block, String localizedName)
	{
		return registerBlock(block, localizedName, new JSONModelSimpleBlock(localizedName));
	}
	
	public static Block registerBlock(Block block, String localizedName, JSONModelBlock jsonType)
	{
		String truename = WyHelper.getFancyName(localizedName);
		langMap.put("block." + Env.PROJECT_ID + "." + truename, localizedName);
		block.setRegistryName(Env.PROJECT_ID, truename);
		
		blocks.put(block, jsonType);
		
		return block;
	}
	
	public static TileEntityType<?> registerTileEntity(String id, Supplier factory, Block... blocks)
	{
		String name = WyHelper.getFancyName(id);

		TileEntityType<?> type = TileEntityType.Builder.create(factory, blocks).build(null);
		type.setRegistryName(Env.PROJECT_ID, name);
		
		return type;
	}
	
	/*public static void registerBlock(Block block, String localizedName, float hard, CreativeTabs tab, Class<? extends TileEntity> tile)
	{	
		String truename = WyHelper.getFancyName(localizedName);
		block.setBlockName(truename).setBlockTextureName(ModValuesEnv.PROJECT_ID + ":" + truename).setHardness(hard).setResistance(hard);
		GameRegistry.registerBlock(block, truename);
		if(tab != null)
			block.setCreativeTab(tab);
		if(tile != null)
			GameRegistry.registerTileEntity(tile, truename);		
		getItemsMap().put(block, localizedName);
		registerName("tile." + truename + ".name", localizedName);
	}*/
	
	public static Item registerItem(Item item, String localizedName)
	{
		return registerItem(item, localizedName, new JSONModelSimpleItem(localizedName));
	}
	
	public static Item registerItem(Item item, String localizedName, JSONModelItem jsonType)
	{
		String truename = WyHelper.getFancyName(localizedName);
		langMap.put("item." + Env.PROJECT_ID + "." + truename, localizedName);
		item.setRegistryName(Env.PROJECT_ID, truename);
		
		items.put(item, jsonType);

		return item;
	}
	
	public static Item registerSpawnEggItem(EntityType type, int backgroundColor, int foregroundColor)
	{
		SpawnEggItem egg = new SpawnEggItem(type, backgroundColor, foregroundColor, (new Item.Properties()).group(ItemGroup.MISC));
		
		StringBuilder builder = new StringBuilder();
		String[] strs = type.getRegistryName().getPath().split("_");
		Arrays.stream(strs).forEach(x -> builder.append(WyHelper.upperCaseFirst(x)));	
		
		String langKey = type.getRegistryName().getPath() + "_spawn_egg";
		String localizedName = "Spawn " + builder.toString().trim();

		langMap.put("item." + Env.PROJECT_ID + "." + langKey, localizedName);
		egg.setRegistryName(Env.PROJECT_ID, langKey);

		items.put(egg, new JSONModelSpawnEgg(langKey));

		return egg;
	}
	
	public static <T extends Entity> EntityType<T> registerEntityType(String id, Function<World, T> func)
	{
		return registerEntityType(id, func, 0.6F, 1.8F);
	}
	
	public static <T extends Entity> EntityType<T> registerEntityType(String id, Function<World, T> func, float width, float height)
	{
		String name = WyHelper.getFancyName(id);
		
		EntityType type = EntityType.Builder.create((entityType, world) -> func.apply(world), EntityClassification.MISC)
			.setTrackingRange(128)
			.setShouldReceiveVelocityUpdates(true)
			.setUpdateInterval(3)
			.setCustomClientFactory((entity, world) -> func.apply(world))
			.size(width, height)
			.build(name)
			.setRegistryName(Env.PROJECT_ID, name);
		
		StringBuilder builder = new StringBuilder();
		String[] strs = name.split("_");
		Arrays.stream(strs).forEach(x -> builder.append(WyHelper.upperCaseFirst(x)));	
		
		langMap.put("entity." + Env.PROJECT_ID + "." + name, builder.toString().trim());
		
		return type;
	}
	
	public static Enchantment registerEnchantment(String name)
	{
		String truename = name.toLowerCase().replace(" ", "_");
		GenericEnchantment ench = new GenericEnchantment(truename, Enchantment.Rarity.RARE, EquipmentSlotType.MAINHAND);
		registerName("enchantment." + Env.PROJECT_ID + "." + truename, name);
		
		return ench;
	}
	
	/*
	public static void registerSpawnBiomesFor(Class<? extends EntityLiving> entity, int rarity, int min, int max, Type... biomeTypes)
	{
		BiomeGenBase[] biomes = new BiomeGenBase[0];
		for(Type t : biomeTypes)
			biomes = ArrayUtils.addAll(biomes, BiomeDictionary.getBiomesForType(t));
		EntityRegistry.addSpawn(entity, rarity, min, max, EnumCreatureType.creature, biomes);		
	}
	
	/*public void registerDimension(String name, int id, Class<? extends WorldProvider> clazz)
	{
		DimensionType.register(name, "_" + WyHelper.instance().getFancyName(name), id, clazz, true);
		DimensionManager.registerDimension(id, DimensionType.getById(id));	
		DimensionManager.createProviderFor(id);
	}*/

}
