package xyz.pixelatedw.wypi;

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
import net.minecraft.particles.IParticleData.IDeserializer;
import net.minecraft.particles.ParticleType;
import net.minecraft.potion.Effect;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.world.World;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
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
	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, APIConfig.PROJECT_ID);
	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, APIConfig.PROJECT_ID);
	public static final DeferredRegister<Ability> ABILITIES = new DeferredRegister<>(APIRegistries.ABILITIES, APIConfig.PROJECT_ID);
	public static final DeferredRegister<Effect> EFFECTS = new DeferredRegister<>(ForgeRegistries.POTIONS, APIConfig.PROJECT_ID);
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.ENTITIES, APIConfig.PROJECT_ID);

	/*
	 * Register Helpers
	 */

	public static Effect registerEffect(Effect effect, String localizedName)
	{
		String resourceName = WyHelper.getResourceName(localizedName);
		langMap.put("effect." + APIConfig.PROJECT_ID + "." + resourceName, localizedName);

		EFFECTS.register(resourceName, () -> effect);
		
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
		
		ABILITIES.register(resourceName, () -> ability);

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
		items.put(item, jsonType);

		ITEMS.register(resourceName, () -> item);
		
		return item;
	}

	public static Item registerSpawnEggItem(EntityType type, String localizedEntityName, int backgroundColor, int foregroundColor)
	{
		String entityResName = WyHelper.getResourceName(localizedEntityName);
		ModdedSpawnEggItem egg = new ModdedSpawnEggItem(() -> type, backgroundColor, foregroundColor, (new Item.Properties()).group(ItemGroup.MISC));

		String resourceName = entityResName + "_spawn_egg";
		String localizedName = "Spawn " + localizedEntityName;
		
		langMap.put("item." + APIConfig.PROJECT_ID + "." + resourceName, localizedName);
		items.put(egg, new JSONModelSpawnEgg(resourceName));
		
		ITEMS.register(resourceName, () -> egg);
		
		/*SpawnEggItem egg = new SpawnEggItem(type, backgroundColor, foregroundColor, (new Item.Properties()).group(ItemGroup.MISC));

		StringBuilder builder = new StringBuilder();
		String[] strs = type.getRegistryName().getPath().split("_");
		Arrays.stream(strs).forEach(x -> builder.append(WyHelper.upperCaseFirst(x)));

		String langKey = type.getRegistryName().getPath() + "_spawn_egg";
		String localizedName = "Spawn " + builder.toString().trim();

		langMap.put("item." + APIConfig.PROJECT_ID + "." + langKey, localizedName);
		egg.setRegistryName(APIConfig.PROJECT_ID, langKey);
		items.put(egg, new JSONModelSpawnEgg(langKey));*/

		//itemsRegistry.register(egg);

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
		blocks.put(block, jsonType);

		BLOCKS.register(resourceName, () -> block);
		
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

	public static <T extends Entity> void registerEntityType(EntityType<?> type, String localizedName)
	{
		String resourceName = WyHelper.getResourceName(localizedName);
		langMap.put("entity." + APIConfig.PROJECT_ID + "." + resourceName, localizedName);
		
		ENTITY_TYPES.register(resourceName, () -> type);
	}
}
