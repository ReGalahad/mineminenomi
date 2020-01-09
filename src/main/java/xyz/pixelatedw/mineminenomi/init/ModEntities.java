package xyz.pixelatedw.mineminenomi.init;

import java.util.List;

import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityProjectile;
import xyz.pixelatedw.mineminenomi.entities.WantedPosterPackageEntity;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.ExtraProjectiles;
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

public class ModEntities
{
	// Mobs
	// Marines
	public static final EntityType MARINE_WITH_SWORD = WyRegistry.registerEntityType("marine_with_sword", EntityMarineWithSword::new);
	public static final EntityType MARINE_WITH_GUN = WyRegistry.registerEntityType("marine_with_gun", EntityMarineWithGun::new);
	public static final EntityType MARINE_CAPTAIN = WyRegistry.registerEntityType("marine_captain", EntityMarineCaptain::new);

	// Pirates
	public static final EntityType PIRATE_WITH_SWORD = WyRegistry.registerEntityType("pirate_with_sword", EntityPirateWithSword::new);
	public static final EntityType PIRATE_WITH_GUN = WyRegistry.registerEntityType("pirate_with_gun", EntityPirateWithGun::new);
	public static final EntityType PIRATE_CAPTAIN = WyRegistry.registerEntityType("pirate_captain", EntityPirateCaptain::new);

	// Bandits
	public static final EntityType BANDIT_WITH_SWORD = WyRegistry.registerEntityType("bandit_with_sword", EntityBanditWithSword::new);

	// Factionless
	public static final EntityType DOJO_SENSEI = WyRegistry.registerEntityType("dojo_sensei", DojoSenseiEntity::new);
	
	// Other
	public static final EntityType DOPPELMAN = WyRegistry.registerEntityType("doppelman", EntityDoppelman::new);
	public static final EntityType BLACK_KNIGHT = WyRegistry.registerEntityType("black_knight", EntityBlackKnight::new);
	public static final EntityType WANTED_POSTER_PACKAGE = WyRegistry.registerEntityType("wanted_poster_package", WantedPosterPackageEntity::new, 1.5F, 1.5F);

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class Registry
	{
		@SubscribeEvent
		public static void registerEntityTypes(RegistryEvent.Register<EntityType<?>> event)
		{
			if (!event.getName().equals(ForgeRegistries.ENTITIES.getRegistryName()))
				return;

			// Register projectiles
			for (List<AbilityProjectile.Data> list : ModDevilFruits.ALL_PROJECTILES)
			{
				list.forEach((value) ->
				{
					event.getRegistry().register(value.getEntityType());
				});
			}
			
			// Register mobs
			event.getRegistry().registerAll
			(
				// Marines
				MARINE_WITH_SWORD, MARINE_WITH_GUN, MARINE_CAPTAIN,
				
				// Pirates
				PIRATE_WITH_SWORD, PIRATE_WITH_GUN, PIRATE_CAPTAIN,
				
				// Bandits
				BANDIT_WITH_SWORD,
				
				// Factionless
				DOJO_SENSEI,
				
				// Other
				DOPPELMAN, BLACK_KNIGHT, ExtraProjectiles.CLOUD, WANTED_POSTER_PACKAGE
			);
		}
		
		@SubscribeEvent
	    public static void registerItems(final RegistryEvent.Register<Item> event)
	    {
			if (!event.getName().equals(ForgeRegistries.ITEMS.getRegistryName())) return;

	        event.getRegistry().registerAll
	        (
	        	// Marines
	        	registerMarineSpawnEgg(ModEntities.MARINE_WITH_SWORD),
		        registerMarineSpawnEgg(ModEntities.MARINE_WITH_GUN),
		        registerMarineSpawnEgg(ModEntities.MARINE_CAPTAIN),

	        	// Pirates
	        	registerPirateSpawnEgg(ModEntities.PIRATE_WITH_SWORD),
	        	registerPirateSpawnEgg(ModEntities.PIRATE_WITH_GUN),
	        	registerPirateSpawnEgg(ModEntities.PIRATE_CAPTAIN),
	        	
	        	// Bandits
	        	registerBanditSpawnEgg(ModEntities.BANDIT_WITH_SWORD),
	        	
	        	// Factionless
	        	registerFactionlessSpawnEgg(ModEntities.DOJO_SENSEI)
	        );
	    }
		
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

}
