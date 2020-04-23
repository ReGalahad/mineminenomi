package xyz.pixelatedw.mineminenomi.init;

import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.entities.PhysicalBodyEntity;
import xyz.pixelatedw.mineminenomi.entities.VivreCardEntity;
import xyz.pixelatedw.mineminenomi.entities.WantedPosterPackageEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.bandits.EntityBanditWithSword;
import xyz.pixelatedw.mineminenomi.entities.mobs.marines.EntityMarineCaptain;
import xyz.pixelatedw.mineminenomi.entities.mobs.marines.EntityMarineWithGun;
import xyz.pixelatedw.mineminenomi.entities.mobs.marines.EntityMarineWithSword;
import xyz.pixelatedw.mineminenomi.entities.mobs.misc.BlackKnightEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.misc.DoppelmanEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.misc.TraderEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.misc.WaxPlayerEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.EntityPirateCaptain;
import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.EntityPirateWithGun;
import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.EntityPirateWithSword;
import xyz.pixelatedw.mineminenomi.entities.mobs.quest.givers.BowMasterEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.quest.givers.DojoSenseiEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.quest.objectives.SniperTargetEntity;
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
	public static final EntityType BOW_MASTER = WyRegistry.createEntityType(BowMasterEntity::new).build("bow_master");
    public static final EntityType TRADER = WyRegistry.createEntityType(TraderEntity::new).build("trader");
    
	// Other
	public static final EntityType DOPPELMAN = WyRegistry.createEntityType(DoppelmanEntity::new).build("doppelman");
	public static final EntityType WAX_PLAYER = WyRegistry.createEntityType(WaxPlayerEntity::new).build("wax_player");
	public static final EntityType BLACK_KNIGHT = WyRegistry.createEntityType(BlackKnightEntity::new).build("black_knight");
	public static final EntityType WANTED_POSTER_PACKAGE = WyRegistry.createEntityType(WantedPosterPackageEntity::new).size(1.5F, 1.5F).build("wanted_poster_package");
	public static final EntityType VIVRE_CARD = WyRegistry.createEntityType(VivreCardEntity::new).size(0.4F, 0.4F).build("vivre_card");
	public static final EntityType PHYSICAL_BODY = WyRegistry.createEntityType(PhysicalBodyEntity::new).build("physical_body");
	public static final EntityType SNIPER_TARGET = WyRegistry.createEntityType(SniperTargetEntity::new).build("sniper_target");

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
		registerFactionlessWithSpawnEgg(BOW_MASTER, "Bow Master");
		registerFactionlessWithSpawnEgg(TRADER, "Trader");

		// Other
		WyRegistry.registerEntityType(DOPPELMAN, "Doppelman");
		WyRegistry.registerEntityType(WAX_PLAYER, "Wax Player");
		WyRegistry.registerEntityType(BLACK_KNIGHT, "Black Knight");
		WyRegistry.registerEntityType(WANTED_POSTER_PACKAGE, "Wanted Poster Package");
		WyRegistry.registerEntityType(VIVRE_CARD, "Vivre Card");
		WyRegistry.registerEntityType(PHYSICAL_BODY, "Physical Body");
		WyRegistry.registerEntityType(SNIPER_TARGET, "Sniper Target");
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
