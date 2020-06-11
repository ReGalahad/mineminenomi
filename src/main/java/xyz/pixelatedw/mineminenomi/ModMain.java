package xyz.pixelatedw.mineminenomi;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.init.ModJollyRogers;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyRegistry;

@Mod(APIConfig.PROJECT_ID)
public class ModMain
{
	public static ModMain instance;
	public static final Logger LOGGER = LogManager.getLogger(APIConfig.PROJECT_ID);

	public ModMain()
	{	
		APIConfig.setupResourceFolderPath();

		instance = this;
		
		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		
		WyRegistry.ENTITY_TYPES.register(modEventBus);
		WyRegistry.BLOCKS.register(modEventBus);
		WyRegistry.ITEMS.register(modEventBus);
		WyRegistry.ABILITIES.register(modEventBus);
		WyRegistry.ENCHANTMENTS.register(modEventBus);
		WyRegistry.TILE_ENTITIES.register(modEventBus);
		WyRegistry.EFFECTS.register(modEventBus);
		WyRegistry.PARTICLE_TYPES.register(modEventBus);
		WyRegistry.QUESTS.register(modEventBus);
		WyRegistry.CONTAINER_TYPES.register(modEventBus);
		WyRegistry.FEATURES.register(modEventBus);
		ModJollyRogers.JOLLY_ROGER_ELEMENTS.register(modEventBus);
		
		CommonConfig.init();
	}
}
