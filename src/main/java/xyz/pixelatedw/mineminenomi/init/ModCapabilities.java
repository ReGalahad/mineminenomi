package xyz.pixelatedw.mineminenomi.init;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitProvider;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsProvider;
import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataProvider;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.APIDefaults;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class ModCapabilities
{

	public static void init()
	{
		// API Capabilities
		APIDefaults.initCapabilities();

		// Mod Capabilities
		DevilFruitCapability.register();
		EntityStatsCapability.register();
		HakiDataCapability.register();
	}

	@SubscribeEvent
	public static void attachCapabilities(AttachCapabilitiesEvent<Entity> event)
	{
		if (event.getObject() instanceof PlayerEntity)
		{
			event.addCapability(new ResourceLocation(APIConfig.PROJECT_ID, "devil_fruit"), new DevilFruitProvider());
			event.addCapability(new ResourceLocation(APIConfig.PROJECT_ID, "entity_stats"), new EntityStatsProvider());
			event.addCapability(new ResourceLocation(APIConfig.PROJECT_ID, "haki_data"), new HakiDataProvider());
		}
	}

}
