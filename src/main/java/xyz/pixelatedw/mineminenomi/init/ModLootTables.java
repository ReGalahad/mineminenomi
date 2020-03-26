package xyz.pixelatedw.mineminenomi.init;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.TableLootEntry;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.wypi.APIConfig;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class ModLootTables
{

	@SubscribeEvent
	public static void onVanillaLootLoading(LootTableLoadEvent event)
	{
		if (event.getName().toString().equals("minecraft:blocks/brain_coral_block"))
		{
			TableLootEntry.Builder lootEntry = TableLootEntry.builder(new ResourceLocation(APIConfig.PROJECT_ID, "blocks/inject/brain_coral_block"));
			System.out.println(lootEntry);
			event.getTable().addPool(LootPool.builder().addEntry(lootEntry).build());
			System.out.println(event.getTable().toString());
		}
	}
	
}
