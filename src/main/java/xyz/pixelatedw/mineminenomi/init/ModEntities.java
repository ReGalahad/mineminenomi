package xyz.pixelatedw.mineminenomi.init;

import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.entities.ChargingUrsusShockEntity;
import xyz.pixelatedw.mineminenomi.entities.VivreCardEntity;
import xyz.pixelatedw.mineminenomi.entities.WantedPosterPackageEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.bandits.EntityBanditWithSword;
import xyz.pixelatedw.mineminenomi.entities.mobs.marines.EntityMarineCaptain;
import xyz.pixelatedw.mineminenomi.entities.mobs.marines.EntityMarineWithGun;
import xyz.pixelatedw.mineminenomi.entities.mobs.marines.EntityMarineWithSword;
import xyz.pixelatedw.mineminenomi.entities.mobs.misc.EntityBlackKnight;
import xyz.pixelatedw.mineminenomi.entities.mobs.misc.EntityDoppelman;
import xyz.pixelatedw.mineminenomi.entities.mobs.misc.WaxPlayerEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.EntityPirateCaptain;
import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.EntityPirateWithGun;
import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.EntityPirateWithSword;
import xyz.pixelatedw.mineminenomi.entities.mobs.quest.givers.DojoSenseiEntity;
import xyz.pixelatedw.wypi.ModdedSpawnEggItem;
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
	public static final EntityType WAX_PLAYER = WyRegistry.createEntityType(WaxPlayerEntity::new).build("wax_player");
	public static final EntityType BLACK_KNIGHT = WyRegistry.createEntityType(EntityBlackKnight::new).build("black_knight");
	public static final EntityType WANTED_POSTER_PACKAGE = WyRegistry.createEntityType(WantedPosterPackageEntity::new).size(1.5F, 1.5F).build("wanted_poster_package");
	public static final EntityType VIVRE_CARD = WyRegistry.createEntityType(VivreCardEntity::new).size(0.4F, 0.4F).build("vivre_card");
	public static final EntityType CHARGING_URSUS_SHOCK = WyRegistry.createEntityType(ChargingUrsusShockEntity::new).size(1.0F, 1.0F).build("charging_ursus_shock");

	static
	{
		// Marines
		registerMarineWithSpawnEgg(MARINE_WITH_SWORD, "Marine with Sword");
		registerMarineWithSpawnEgg(MARINE_WITH_GUN, "Marine with Gun");
		registerMarineWithSpawnEgg(MARINE_CAPTAIN, "Marine Captain");

		// Pirates
		registerPirateWithSpawnEgg(PIRATE_WITH_SWORD, "Pirate with Sword");
		registerPirateWithSpawnEgg(PIRATE_WITH_GUN, "Pirate with Gun");
		registerPirateWithSpawnEgg(PIRATE_CAPTAIN, "Pirate Captain");

		// Bandits
		registerBanditWithSpawnEgg(BANDIT_WITH_SWORD, "Bandit with Sword");

		// Factionless
		registerFactionlessWithSpawnEgg(DOJO_SENSEI, "Dojo Sensei");

		// Other
		WyRegistry.registerEntityType(DOPPELMAN, "Doppelman");
		WyRegistry.registerEntityType(WAX_PLAYER, "Wax Player");
		WyRegistry.registerEntityType(BLACK_KNIGHT, "Black Knight");
		WyRegistry.registerEntityType(WANTED_POSTER_PACKAGE, "Wanted Poster Package");
		WyRegistry.registerEntityType(VIVRE_CARD, "Vivre Card");
		WyRegistry.registerEntityType(CHARGING_URSUS_SHOCK, "Charging Ursus Shock");
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

}
