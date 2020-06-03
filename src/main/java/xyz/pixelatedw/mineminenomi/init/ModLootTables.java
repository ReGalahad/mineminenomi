package xyz.pixelatedw.mineminenomi.init;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.ItemLootEntry;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTables;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.TableLootEntry;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.wypi.APIConfig;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class ModLootTables {

    @SubscribeEvent
    public static void onVanillaLootLoading(LootTableLoadEvent event) {


        if (event.getName().toString().equals("minecraft:blocks/brain_coral_block")) {
            TableLootEntry.Builder lootEntry = TableLootEntry.builder(new ResourceLocation(APIConfig.PROJECT_ID, "blocks/inject/brain_coral_block"));
            event.getTable().addPool(LootPool.builder().addEntry(lootEntry).build());
        }

        if (event.getName().equals(LootTables.CHESTS_UNDERWATER_RUIN_BIG) || event.getName().equals(LootTables.CHESTS_UNDERWATER_RUIN_SMALL)) {
            LootPool fruit_boxes = constructLootPool("fruit_boxes", 1F, 1F,
					ItemLootEntry.builder(ModItems.TIER_1_BOX).weight(10),
					ItemLootEntry.builder(ModItems.TIER_2_BOX).weight(2)
            );
			event.getTable().addPool(fruit_boxes);
        }

        if (event.getName().equals(LootTables.CHESTS_BURIED_TREASURE) || event.getName().equals(LootTables.CHESTS_SHIPWRECK_TREASURE)) {
            LootPool fruit_boxes = constructLootPool("fruit_boxes", 1F, 2F,
                    ItemLootEntry.builder(ModItems.TIER_2_BOX).weight(10),
                    ItemLootEntry.builder(ModItems.TIER_3_BOX).weight(3)
            );
			event.getTable().addPool(fruit_boxes);
        }

        if (event.getName().equals(LootTables.CHESTS_SHIPWRECK_SUPPLY) && event.getName().equals(LootTables.CHESTS_SHIPWRECK_MAP)) {
            LootPool fruit_boxes = constructLootPool("fruit_boxes", 1F, 1F,
                    ItemLootEntry.builder(ModItems.TIER_1_BOX).weight(8),
                    ItemLootEntry.builder(ModItems.TIER_2_BOX).weight(4)
            );
			event.getTable().addPool(fruit_boxes);
		}

    }

    public static LootPool constructLootPool(String name, float minRolls, float maxRolls, LootEntry.Builder<?>... lootEntries) {
        LootPool.Builder poolBuilder = LootPool.builder().name(name).rolls(RandomValueRange.of(minRolls, maxRolls));
        if (lootEntries != null) {
            for (LootEntry.Builder<?> e : lootEntries) {
                if (e != null) poolBuilder.addEntry(e);
            }
        }
        return poolBuilder.build();
    }

}
