package xyz.pixelatedw.wypi;

import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Function;
import java.util.function.Supplier;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityType.Builder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.particles.IParticleData.IDeserializer;
import net.minecraft.particles.ParticleType;
import net.minecraft.potion.Effect;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistry;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.json.loottables.IJSONLootTable;
import xyz.pixelatedw.wypi.json.models.JSONModelBlock;
import xyz.pixelatedw.wypi.json.models.JSONModelItem;
import xyz.pixelatedw.wypi.json.models.block.JSONModelSimpleBlock;
import xyz.pixelatedw.wypi.json.models.item.JSONModelSimpleItem;
import xyz.pixelatedw.wypi.json.models.item.JSONModelSpawnEgg;

public class WyRegistry
{
	/*
	 * Maps
	 */
	private static HashMap<Item, JSONModelItem> items = new HashMap<Item, JSONModelItem>();
	public static HashMap<Item, JSONModelItem> getItems()
	{
		return items;
	}
	
	private static HashMap<Block, JSONModelBlock> blocks = new HashMap<Block, JSONModelBlock>();
	public static HashMap<Block, JSONModelBlock> getBlocks()
	{
		return blocks;
	}
	
	private static HashMap<Object, IJSONLootTable> lootTables = new HashMap<Object, IJSONLootTable>();
	public static HashMap<Object, IJSONLootTable> getLootTables()
	{
		return lootTables;
	}
	
	private static HashMap<String, String> langMap = new HashMap<String, String>();
	public static HashMap<String, String> getLangMap()
	{
		return langMap;
	}

	/*
	 * Registries
	 */
	private static IForgeRegistry<Effect> effectsRegistry;
	public static void setupEffectsRegistry(IForgeRegistry<Effect> registry)
	{
		effectsRegistry = registry;
	}
	
	private static IForgeRegistry<Ability> abilitiesRegistry;
	public static void setupAbilitiesRegistry(IForgeRegistry<Ability> registry)
	{
		abilitiesRegistry = registry;
	}
	
	private static IForgeRegistry<Item> itemsRegistry;
	public static void setupItemsRegistry(IForgeRegistry<Item> registry)
	{
		itemsRegistry = registry;
	}

	private static IForgeRegistry<Block> blocksRegistry;
	public static void setupBlocksRegistry(IForgeRegistry<Block> registry)
	{
		blocksRegistry = registry;
	}
	
	private static IForgeRegistry<EntityType<?>> entityTypesRegistry;
	public static void setupEntityTypeRegistry(IForgeRegistry<EntityType<?>> registry)
	{
		entityTypesRegistry = registry;
	}

	/*
	 * Register Helpers
	 */

	public static Effect registerEffect(Effect effect, String localizedName)
	{
		String resourceName = WyHelper.getResourceName(localizedName);
		langMap.put("effect." + APIConfig.PROJECT_ID + "." + resourceName, localizedName);
		effect.setRegistryName(APIConfig.PROJECT_ID, resourceName);

		effectsRegistry.register(effect);
		
		return effect;
	}

	public static ParticleType registerGenericParticleType(String id, IDeserializer<?> deserializer)
	{
		return new ParticleType<>(true, deserializer).setRegistryName(APIConfig.PROJECT_ID, id);
	}

	public static void registerLootTable(Object obj, IJSONLootTable json)
	{
		lootTables.put(obj, json);
	}

	public static void registerName(String key, String localizedName)
	{
		langMap.put(key, localizedName);
	}
	
	public static Ability registerAbility(Ability ability)
	{
		String resourceName = WyHelper.getResourceName(ability.getName());
		langMap.put("ability." + APIConfig.PROJECT_ID + "." + resourceName, ability.getName());
		ability.setRegistryName(APIConfig.PROJECT_ID, resourceName);
		
		abilitiesRegistry.register(ability);

		return ability;
	}

	public static Item registerItem(Item item, String localizedName)
	{
		return registerItem(item, localizedName, new JSONModelSimpleItem(localizedName));
	}

	public static Item registerItem(Item item, String localizedName, JSONModelItem jsonType)
	{
		String resourceName = WyHelper.getResourceName(localizedName);
		langMap.put("item." + APIConfig.PROJECT_ID + "." + resourceName, localizedName);
		item.setRegistryName(APIConfig.PROJECT_ID, resourceName);
		items.put(item, jsonType);

		itemsRegistry.register(item);
		
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

		langMap.put("item." + APIConfig.PROJECT_ID + "." + langKey, localizedName);
		egg.setRegistryName(APIConfig.PROJECT_ID, langKey);
		items.put(egg, new JSONModelSpawnEgg(langKey));

		itemsRegistry.register(egg);

		return egg;
	}

	public static Block registerBlock(Block block, String localizedName)
	{
		return registerBlock(block, localizedName, new JSONModelSimpleBlock(localizedName));
	}

	public static Block registerBlock(Block block, String localizedName, JSONModelBlock jsonType)
	{
		String resourceName = WyHelper.getResourceName(localizedName);
		langMap.put("block." + APIConfig.PROJECT_ID + "." + resourceName, localizedName);
		block.setRegistryName(new ResourceLocation(APIConfig.PROJECT_ID, resourceName));
		blocks.put(block, jsonType);

		blocksRegistry.register(block);
		
		return block;
	}
	
	public static TileEntityType<?> registerTileEntity(String id, Supplier factory, Block... blocks)
	{
		String resourceName = WyHelper.getResourceName(id);

		TileEntityType<?> type = TileEntityType.Builder.create(factory, blocks).build(null);
		type.setRegistryName(APIConfig.PROJECT_ID, resourceName);
		
		return type;
	}

	public static <T extends Entity> Builder<T> createEntityType(Function<World, T> func)
	{
		Builder<T> builder = EntityType.Builder.create((entityType, world) -> func.apply(world), EntityClassification.MISC);
		
		builder.setTrackingRange(128)
			.setShouldReceiveVelocityUpdates(true)
			.setUpdateInterval(1)
			.setCustomClientFactory((entity, world) -> func.apply(world))
			.size(0.6F, 1.8F);
		
		return builder;
	}

	public static <T extends Entity> EntityType<T> registerEntityType(EntityType type, String localizedName)
	{
		String resourceName = WyHelper.getResourceName(localizedName);

		type.setRegistryName(APIConfig.PROJECT_ID, resourceName);
		langMap.put("entity." + APIConfig.PROJECT_ID + "." + resourceName, localizedName);

		entityTypesRegistry.register(type);
		
		return type;
	}
}
