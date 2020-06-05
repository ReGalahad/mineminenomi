package xyz.pixelatedw.mineminenomi.events;

import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.event.TickEvent.WorldTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.world.spawners.AmbushSpawner;
import xyz.pixelatedw.mineminenomi.world.spawners.TraderSpawner;
import xyz.pixelatedw.mineminenomi.world.spawners.TrainerSpawner;
import xyz.pixelatedw.wypi.APIConfig;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class SpawnerEvents
{
	private static final TraderSpawner TRADER_SPAWNER = new TraderSpawner();
	private static final TrainerSpawner TRAINER_SPAWNER = new TrainerSpawner();
	private static final AmbushSpawner AMBUSH_SPAWNER = new AmbushSpawner();

	@SubscribeEvent
	public static void onServerTick(WorldTickEvent event)
	{
		if(event.phase == Phase.END)
		{
			if(CommonConfig.instance.canSpawnTraders())
				TRADER_SPAWNER.tick((ServerWorld) event.world);
			if(CommonConfig.instance.canSpawnTrainers())
				TRAINER_SPAWNER.tick((ServerWorld) event.world);
			if(CommonConfig.instance.canSpawnAmbushes())
				AMBUSH_SPAWNER.tick((ServerWorld) event.world);
		}
	}
	
}
