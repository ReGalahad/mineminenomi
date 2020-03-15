package xyz.pixelatedw.mineminenomi.init;

import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.entities.WantedPosterPackageEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.bandits.EntityBanditWithSword;
import xyz.pixelatedw.mineminenomi.entities.mobs.marines.EntityMarineCaptain;
import xyz.pixelatedw.mineminenomi.entities.mobs.marines.EntityMarineWithGun;
import xyz.pixelatedw.mineminenomi.entities.mobs.marines.EntityMarineWithSword;
import xyz.pixelatedw.mineminenomi.entities.mobs.misc.EntityBlackKnight;
import xyz.pixelatedw.mineminenomi.entities.mobs.misc.EntityDoppelman;
import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.EntityPirateCaptain;
import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.EntityPirateWithGun;
import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.EntityPirateWithSword;
import xyz.pixelatedw.mineminenomi.entities.mobs.quest.givers.DojoSenseiEntity;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.WyRegistry;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntities
{
	// Mobs
	// Marines
	public static final EntityType MARINE_WITH_SWORD = WyRegistry.createEntityType(EntityMarineWithSword::new).build("marine_with_sword");
	public static final EntityType MARINE_WITH_GUN = WyRegistry.createEntityType(EntityMarineWithGun::new).build("marine_with_gun");
	public static final EntityType MARINE_CAPTAIN = WyRegistry.createEntityType(EntityMarineCaptain::new).build("marine_captain");

	// Pirates
	public static final EntityType PIRATE_WITH_SWORD = WyRegistry.createEntityType(EntityPirateWithSword::new).build("pirate_with_sword");
	public static final EntityType PIRATE_WITH_GUN = WyRegistry.createEntityType(EntityPirateWithGun::new).build("pirate_with_gun");
	public static final EntityType PIRATE_CAPTAIN = WyRegistry.createEntityType(EntityPirateCaptain::new).build("pirate_captain");

	// Bandits
	public static final EntityType BANDIT_WITH_SWORD = WyRegistry.createEntityType(EntityBanditWithSword::new).build("bandit_with_sword");

	// Factionless
	public static final EntityType DOJO_SENSEI = WyRegistry.createEntityType(DojoSenseiEntity::new).build("dojo_sensei");

	// Other
	public static final EntityType DOPPELMAN = WyRegistry.createEntityType(EntityDoppelman::new).build("doppelman");
	public static final EntityType BLACK_KNIGHT = WyRegistry.createEntityType(EntityBlackKnight::new).build("black_knight");
	public static final EntityType WANTED_POSTER_PACKAGE = WyRegistry.createEntityType(WantedPosterPackageEntity::new).size(1.5F, 1.5F).build("wanted_poster_package");

	@SubscribeEvent
	public static void registerEntityTypes(RegistryEvent.Register<EntityType<?>> event)
	{
		WyRegistry.setupEntityTypeRegistry(event.getRegistry());

		// Marines
		WyRegistry.registerEntityType(MARINE_WITH_SWORD, "Marine with Sword");
		WyRegistry.registerEntityType(MARINE_WITH_GUN, "Marine with Gun");
		WyRegistry.registerEntityType(MARINE_CAPTAIN, "Marine Captain");

		// Pirates
		WyRegistry.registerEntityType(PIRATE_WITH_SWORD, "Pirate with Sword");
		WyRegistry.registerEntityType(PIRATE_WITH_GUN, "Pirate with Gun");
		WyRegistry.registerEntityType(PIRATE_CAPTAIN, "Pirate Captain");

		// Bandits
		WyRegistry.registerEntityType(BANDIT_WITH_SWORD, "Bandit with Sword");

		// Factionless
		WyRegistry.registerEntityType(DOJO_SENSEI, "Dojo Sensei");

		// Other
		WyRegistry.registerEntityType(DOPPELMAN, "Doppelman");
		WyRegistry.registerEntityType(BLACK_KNIGHT, "Black Knight");
		WyRegistry.registerEntityType(WANTED_POSTER_PACKAGE, "Wanted Poster Package");
	}
/*
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void onPostRegisterEntities(final RegistryEvent.Register<EntityType<?>> event)
	{
		ModdedSpawnEggItem.initUnaddedEggs();
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event)
	{
		WyRegistry.setupItemsRegistry(event.getRegistry());

		// Marines
		registerMarineSpawnEgg(MARINE_WITH_SWORD);
		registerMarineSpawnEgg(MARINE_WITH_GUN);
		registerMarineSpawnEgg(MARINE_CAPTAIN);

		// Pirates
		registerPirateSpawnEgg(PIRATE_WITH_SWORD);
		registerPirateSpawnEgg(PIRATE_WITH_GUN);
		registerPirateSpawnEgg(PIRATE_CAPTAIN);

		// Bandits
		registerBanditSpawnEgg(BANDIT_WITH_SWORD);

		// Factionless
		registerFactionlessSpawnEgg(DOJO_SENSEI);
	}
*/
	private static Item registerMarineSpawnEgg(EntityType type)
	{
		return WyRegistry.registerSpawnEggItem(type, WyHelper.hexToRGB("#024a81").getRGB(), WyHelper.hexToRGB("#F7F7F7").getRGB());
	}

	private static Item registerPirateSpawnEgg(EntityType type)
	{
		return WyRegistry.registerSpawnEggItem(type, WyHelper.hexToRGB("#c11c1c").getRGB(), WyHelper.hexToRGB("#F7F7F7").getRGB());
	}

	private static Item registerBanditSpawnEgg(EntityType type)
	{
		return WyRegistry.registerSpawnEggItem(type, WyHelper.hexToRGB("#785355").getRGB(), WyHelper.hexToRGB("#F7F7F7").getRGB());
	}

	private static Item registerFactionlessSpawnEgg(EntityType type)
	{
		return WyRegistry.registerSpawnEggItem(type, WyHelper.hexToRGB("#fbbf4c").getRGB(), WyHelper.hexToRGB("#F7F7F7").getRGB());
	}

}
