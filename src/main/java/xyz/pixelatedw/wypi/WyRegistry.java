package xyz.pixelatedw.wypi;

import java.util.HashMap;
import java.util.function.Function;
import java.util.function.Supplier;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityType.Builder;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.particles.ParticleType;
import net.minecraft.potion.Effect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.json.loottables.IJSONLootTable;
import xyz.pixelatedw.wypi.json.models.JSONModelBlock;
import xyz.pixelatedw.wypi.json.models.JSONModelItem;
import xyz.pixelatedw.wypi.json.models.block.JSONModelSimpleBlock;
import xyz.pixelatedw.wypi.json.models.item.JSONModelSimpleItem;
import xyz.pixelatedw.wypi.json.models.item.JSONModelSpawnEgg;
import xyz.pixelatedw.wypi.quests.Quest;
import xyz.pixelatedw.wypi.quests.objectives.Objective;

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
	public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = new DeferredRegister<>(ForgeRegistries.CONTAINERS, APIConfig.PROJECT_ID);
	public static final DeferredRegister<Ability> ABILITIES = new DeferredRegister<>(APIRegistries.ABILITIES, APIConfig.PROJECT_ID);
	public static final DeferredRegister<Effect> EFFECTS = new DeferredRegister<>(ForgeRegistries.POTIONS, APIConfig.PROJECT_ID);
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.ENTITIES, APIConfig.PROJECT_ID);
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, APIConfig.PROJECT_ID);
	public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = new DeferredRegister<>(ForgeRegistries.PARTICLE_TYPES, APIConfig.PROJECT_ID);
	public static final DeferredRegister<Quest> QUESTS = new DeferredRegister<>(APIRegistries.QUESTS, APIConfig.PROJECT_ID);

	/*
	 * Register Helpers
	 */

	public static void registerLootTable(Object obj, IJSONLootTable json)
	{
		lootTables.put(obj, json);
	}

	public static String registerName(String key, String localizedName)
	{
		langMap.put(key, localizedName);
		return key;
	}

	public static void registerParticleType(ParticleType<?> type, String localizedName)
	{
		String resourceName = WyHelper.getResourceName(localizedName);

		PARTICLE_TYPES.register(resourceName, () -> type);
	}

	public static Effect registerEffect(Effect effect, String localizedName)
	{
		String resourceName = WyHelper.getResourceName(localizedName);
		langMap.put("effect." + APIConfig.PROJECT_ID + "." + resourceName, localizedName);

		EFFECTS.register(resourceName, () -> effect);

		return effect;
	}

	public static Quest registerQuest(Quest quest)
	{
		String resourceName = WyHelper.getResourceName(quest.getId());
		langMap.put("quest." + APIConfig.PROJECT_ID + "." + resourceName, quest.getTitle());

		for(Objective obj : quest.getObjectives())
		{
			langMap.put("quest.objective." + APIConfig.PROJECT_ID + "." + obj.getId(), obj.getTitle());
		}
		
		QUESTS.register(resourceName, () -> quest);

		return quest;
	}
	
	public static Ability registerAbility(Ability ability)
	{
		String resourceName = WyHelper.getResourceName(ability.getName());
		langMap.put("ability." + APIConfig.PROJECT_ID + "." + resourceName, ability.getName());
		
        final ResourceLocation key = new ResourceLocation(APIConfig.PROJECT_ID, resourceName);
		RegistryObject<Ability> ret = RegistryObject.of(key, APIRegistries.ABILITIES);
		if(!ABILITIES.getEntries().contains(ret))
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

	public static <T extends TileEntity> TileEntityType.Builder<T> createTileEntity(Supplier<T> factory, Block... blocks)
	{
		TileEntityType.Builder<T> type = TileEntityType.Builder.create(factory, blocks);

		return type;
	}

	public static <T extends TileEntity> void registerTileEntity(TileEntityType<T> type, String localizedName)
	{
		String resourceName = WyHelper.getResourceName(localizedName);
		
		TILE_ENTITIES.register(resourceName, () -> type);
	}

	public static <T extends Entity> EntityType.Builder<T> createEntityType(Function<World, T> func)
	{
		return createEntityType(func, EntityClassification.MISC);
	}
	
	public static <T extends Entity> EntityType.Builder<T> createEntityType(Function<World, T> func, EntityClassification classification)
	{
		Builder<T> builder = EntityType.Builder.create((entityType, world) -> func.apply(world), classification);

		builder.setTrackingRange(128).setShouldReceiveVelocityUpdates(true).setUpdateInterval(1).setCustomClientFactory((entity, world) -> func.apply(world)).size(0.6F, 1.8F);

		return builder;
	}

	public static <T extends Entity> void registerEntityType(EntityType<T> type, String localizedName)
	{
		String resourceName = WyHelper.getResourceName(localizedName);
		langMap.put("entity." + APIConfig.PROJECT_ID + "." + resourceName, localizedName);

		ENTITY_TYPES.register(resourceName, () -> type);
	}
	
	public static void registerContainerType(ContainerType<?> type, String localizedName)
	{
		String resourceName = WyHelper.getResourceName(localizedName);
		langMap.put("container." + APIConfig.PROJECT_ID + "." + resourceName, localizedName);

		CONTAINER_TYPES.register(resourceName, () -> type);
	}
}
