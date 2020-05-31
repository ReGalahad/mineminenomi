package xyz.pixelatedw.mineminenomi.init;

import java.util.Map.Entry;

import org.apache.commons.lang3.ArrayUtils;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.pixelatedw.mineminenomi.entities.PhysicalBodyEntity;
import xyz.pixelatedw.mineminenomi.entities.VivreCardEntity;
import xyz.pixelatedw.mineminenomi.entities.WantedPosterPackageEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.animals.DenDenMushiEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.animals.LapahnEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.bandits.BanditWithSwordEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.marines.MarineCaptainEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.marines.MarineTraderEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.marines.MarineWithGunEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.marines.MarineWithSwordEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.misc.BlackKnightEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.misc.DoppelmanEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.misc.MirageCloneEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.misc.WaxCloneEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.PirateCaptainEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.PirateTraderEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.PirateWithGunEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.PirateWithSwordEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.quest.givers.BowMasterEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.quest.givers.DojoSenseiEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.quest.objectives.SniperTargetEntity;
import xyz.pixelatedw.wypi.ModdedSpawnEggItem;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.WyRegistry;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntities
{
	// Entity Classifications
	private static final EntityClassification MARINES = EntityClassification.create("marines", "marines", 20, false, false);
	private static final EntityClassification PIRATES = EntityClassification.create("pirates", "pirates", 20, false, false);
	private static final EntityClassification BANDITS = EntityClassification.create("bandits", "bandits", 20, false, false);

	// Mobs
	// Marines
	public static final EntityType MARINE_WITH_SWORD = WyRegistry.createEntityType(MarineWithSwordEntity::new, MARINES).build("marine_with_sword");
	public static final EntityType MARINE_WITH_GUN = WyRegistry.createEntityType(MarineWithGunEntity::new, MARINES).build("marine_with_gun");
	public static final EntityType MARINE_CAPTAIN = WyRegistry.createEntityType(MarineCaptainEntity::new, MARINES).build("marine_captain");
	public static final EntityType MARINE_TRADER = WyRegistry.createEntityType(MarineTraderEntity::new, MARINES).build("marine_trader");

	// Pirates
	public static final EntityType PIRATE_WITH_SWORD = WyRegistry.createEntityType(PirateWithSwordEntity::new, PIRATES).build("pirate_with_sword");
	public static final EntityType PIRATE_WITH_GUN = WyRegistry.createEntityType(PirateWithGunEntity::new, PIRATES).build("pirate_with_gun");
	public static final EntityType PIRATE_CAPTAIN = WyRegistry.createEntityType(PirateCaptainEntity::new, PIRATES).build("pirate_captain");
	public static final EntityType PIRATE_TRADER = WyRegistry.createEntityType(PirateTraderEntity::new, PIRATES).build("pirate_trader");

	// Bandits
	public static final EntityType BANDIT_WITH_SWORD = WyRegistry.createEntityType(BanditWithSwordEntity::new, BANDITS).build("bandit_with_sword");

	// Factionless
	public static final EntityType DOJO_SENSEI = WyRegistry.createEntityType(DojoSenseiEntity::new).build("dojo_sensei");
	public static final EntityType BOW_MASTER = WyRegistry.createEntityType(BowMasterEntity::new).build("bow_master");

	// Animals
	public static final EntityType DEN_DEN_MUSHI = WyRegistry.createEntityType(DenDenMushiEntity::new).size(0.8F, 0.8F).build("den_den_mushi");
	public static final EntityType LAPAHN = WyRegistry.createEntityType(LapahnEntity::new, EntityClassification.CREATURE).size(0.8F, 2.5F).build("lapahn");

	// Other
	public static final EntityType DOPPELMAN = WyRegistry.createEntityType(DoppelmanEntity::new).build("doppelman");
	public static final EntityType WAX_CLONE = WyRegistry.createEntityType(WaxCloneEntity::new).build("wax_clone");
	public static final EntityType BLACK_KNIGHT = WyRegistry.createEntityType(BlackKnightEntity::new).build("black_knight");
	public static final EntityType WANTED_POSTER_PACKAGE = WyRegistry.createEntityType(WantedPosterPackageEntity::new).size(1.5F, 1.5F).build("wanted_poster_package");
	public static final EntityType VIVRE_CARD = WyRegistry.createEntityType(VivreCardEntity::new).size(0.4F, 0.4F).build("vivre_card");
	public static final EntityType PHYSICAL_BODY = WyRegistry.createEntityType(PhysicalBodyEntity::new).build("physical_body");
	public static final EntityType SNIPER_TARGET = WyRegistry.createEntityType(SniperTargetEntity::new).build("sniper_target");
	public static final EntityType MIRAGE_CLONE = WyRegistry.createEntityType(MirageCloneEntity::new).build("mirage_clone");

	// Biome Sets
	private static final Biome.Category[] GENERIC_ONES = new Biome.Category[] { Biome.Category.PLAINS, Biome.Category.FOREST, Biome.Category.BEACH, Biome.Category.EXTREME_HILLS, Biome.Category.TAIGA, Biome.Category.SAVANNA, Biome.Category.SWAMP };
	private static final Biome.Category[] GENERIC_ONES_PLUS = ArrayUtils.addAll(GENERIC_ONES, Biome.Category.MESA, Biome.Category.DESERT);

	static
	{
		// Marines
		registerMarineWithSpawnEgg(MARINE_WITH_SWORD, "Marine with Sword");
		registerEntityWorldSpawn(MARINE_WITH_SWORD, 10, 2, 5, GENERIC_ONES_PLUS);
		registerMarineWithSpawnEgg(MARINE_WITH_GUN, "Marine with Gun");
		registerEntityWorldSpawn(MARINE_WITH_GUN, 8, 2, 5, GENERIC_ONES_PLUS);
		registerMarineWithSpawnEgg(MARINE_CAPTAIN, "Marine Captain");
		registerMarineWithSpawnEgg(MARINE_TRADER, "Marine Trader");
		registerEntityWorldSpawn(MARINE_TRADER, 2, 0, 1, GENERIC_ONES);

		// Pirates
		registerPirateWithSpawnEgg(PIRATE_WITH_SWORD, "Pirate with Sword");
		registerEntityWorldSpawn(PIRATE_WITH_SWORD, 10, 2, 5, GENERIC_ONES_PLUS);
		registerPirateWithSpawnEgg(PIRATE_WITH_GUN, "Pirate with Gun");
		registerEntityWorldSpawn(PIRATE_WITH_GUN, 8, 2, 5, GENERIC_ONES_PLUS);
		registerPirateWithSpawnEgg(PIRATE_CAPTAIN, "Pirate Captain");
		registerPirateWithSpawnEgg(PIRATE_TRADER, "Pirate Trader");
		registerEntityWorldSpawn(PIRATE_TRADER, 2, 0, 1, GENERIC_ONES);

		// Bandits
		registerBanditWithSpawnEgg(BANDIT_WITH_SWORD, "Bandit with Sword");
		registerEntityWorldSpawn(BANDIT_WITH_SWORD, 10, 2, 5, Biome.Category.EXTREME_HILLS, Biome.Category.FOREST, Biome.Category.MESA);

		// Factionless
		registerFactionlessWithSpawnEgg(DOJO_SENSEI, "Dojo Sensei");
		registerFactionlessWithSpawnEgg(BOW_MASTER, "Bow Master");
		registerEntityWorldSpawn(BOW_MASTER, 12, 1, 1, Biome.Category.FOREST, Biome.Category.TAIGA, Biome.Category.JUNGLE);

		// Animals
		registerAnimalWithSpawnEgg(DEN_DEN_MUSHI, "Den Den Mushi");
		registerEntityWorldSpawn(DEN_DEN_MUSHI, 12, 2, 5, GENERIC_ONES);
		registerAnimalWithSpawnEgg(LAPAHN, "Lapahn");
		registerEntityWorldSpawn(LAPAHN, 20, 1, 3, Biome.Category.ICY);

		// Other
		WyRegistry.registerEntityType(DOPPELMAN, "Doppelman");
		WyRegistry.registerEntityType(WAX_CLONE, "Wax Clone");
		WyRegistry.registerEntityType(BLACK_KNIGHT, "Black Knight");
		WyRegistry.registerEntityType(WANTED_POSTER_PACKAGE, "Wanted Poster Package");
		WyRegistry.registerEntityType(VIVRE_CARD, "Vivre Card");
		WyRegistry.registerEntityType(PHYSICAL_BODY, "Physical Body");
		WyRegistry.registerEntityType(SNIPER_TARGET, "Sniper Target");
		WyRegistry.registerEntityType(MIRAGE_CLONE, "Mirage Clone");
	}

	public static void registerEntityWorldSpawn(EntityType<?> type, int weight, int minGroup, int maxGroup, Biome.Category... categories)
	{
		for (Entry<ResourceLocation, Biome> entry : ForgeRegistries.BIOMES.getEntries())
		{
			for (Biome.Category category : categories)
			{
				if (entry != null && entry.getValue().getCategory() == category)
				{
					entry.getValue().getSpawns(type.getClassification()).add(new SpawnListEntry(type, weight, minGroup, maxGroup));
				}
			}
		}
	}

	public static void registerEntityWorldSpawn(EntityType<?> type, int weight, int minGroup, int maxGroup, Biome... biomes)
	{
		for (Biome biome : biomes)
		{
			if (biome != null)
			{
				biome.getSpawns(type.getClassification()).add(new SpawnListEntry(type, weight, minGroup, maxGroup));
			}
		}
	}

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void onPostRegisterEntities(final RegistryEvent.Register<EntityType<?>> event)
	{
		ModdedSpawnEggItem.initUnaddedEggs();
	}

	private static Item registerMarineWithSpawnEgg(EntityType type, String name)
	{
		WyRegistry.registerEntityType(type, name);
		return WyRegistry.registerSpawnEggItem(type, name, WyHelper.hexToRGB("#024a81").getRGB(), WyHelper.hexToRGB("#F7F7F7").getRGB());
	}

	private static Item registerPirateWithSpawnEgg(EntityType type, String name)
	{
		WyRegistry.registerEntityType(type, name);
		return WyRegistry.registerSpawnEggItem(type, name, WyHelper.hexToRGB("#c11c1c").getRGB(), WyHelper.hexToRGB("#F7F7F7").getRGB());
	}

	private static Item registerBanditWithSpawnEgg(EntityType type, String name)
	{
		WyRegistry.registerEntityType(type, name);
		return WyRegistry.registerSpawnEggItem(type, name, WyHelper.hexToRGB("#785355").getRGB(), WyHelper.hexToRGB("#F7F7F7").getRGB());
	}

	private static Item registerFactionlessWithSpawnEgg(EntityType type, String name)
	{
		WyRegistry.registerEntityType(type, name);
		return WyRegistry.registerSpawnEggItem(type, name, WyHelper.hexToRGB("#fbbf4c").getRGB(), WyHelper.hexToRGB("#F7F7F7").getRGB());
	}

	private static Item registerAnimalWithSpawnEgg(EntityType type, String name)
	{
		WyRegistry.registerEntityType(type, name);
		return WyRegistry.registerSpawnEggItem(type, name, WyHelper.hexToRGB("#a7ca34").getRGB(), WyHelper.hexToRGB("#a2f7c8").getRGB());
	}
}
