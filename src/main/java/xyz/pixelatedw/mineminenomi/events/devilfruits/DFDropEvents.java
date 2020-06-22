package xyz.pixelatedw.mineminenomi.events.devilfruits;

import net.minecraft.block.LeavesBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.init.ModValues;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class DFDropEvents
{
	@SubscribeEvent	
	public static void onBreak(BreakEvent event)
	{
		if(CommonConfig.instance.getDevilFruitDropsFromLeavesChance() > 0 && event.getState().getBlock() instanceof LeavesBlock)
		{
			double chance = WyHelper.randomWithRange(0, 100) + WyHelper.randomDouble();

			if(chance < CommonConfig.instance.getDevilFruitDropsFromLeavesChance())
			{
				AkumaNoMiItem df = ModValues.devilfruits.get((int) WyHelper.randomWithRange(0, ModValues.devilfruits.size() - 1));
				
				boolean isAvailable = true;
				
				if(CommonConfig.instance.isOneFruitPerWorldEnabled())
				{
					String fruitName = df.getTranslationKey().substring("item.mineminenomi.".length()).replace("_no_mi", "").replace(":", "").replace(".", "").replace(",", "").replace("model_", "");
					int chanceForNewFruit = 0;
					while(DevilFruitHelper.isDevilFruitInWorld(event.getPlayer().world, fruitName))
					{
						if(chanceForNewFruit >= 10)
						{
							isAvailable = false;
							break;
						}
						df = ModValues.devilfruits.get((int) WyHelper.randomWithRange(0, ModValues.devilfruits.size() - 1));
						chanceForNewFruit++;
					}
				}
				
				if(isAvailable)
					event.getPlayer().inventory.addItemStackToInventory(new ItemStack(df));
			}
		}
	}
}
