package xyz.pixelatedw.mineminenomi.events.devilfruits;

import net.minecraft.block.LeavesBlock;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
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
		boolean hasShears = event.getPlayer().getHeldItem(event.getPlayer().getActiveHand()).getItem() == Items.SHEARS;
		if(CommonConfig.instance.getDevilFruitDropsFromLeavesChance() > 0 && event.getState().getBlock() instanceof LeavesBlock && !hasShears)
		{
			double chance = WyHelper.randomWithRange(0, 100) + WyHelper.randomDouble();

			if(chance < CommonConfig.instance.getDevilFruitDropsFromLeavesChance())
			{
				AkumaNoMiItem df = ModValues.devilfruits.get((int) WyHelper.randomWithRange(0, ModValues.devilfruits.size() - 1));
				
				boolean isAvailable = true && DevilFruitHelper.oneFruitPerWorldCheck((World) event.getWorld(), df);

				if(isAvailable)
				{
					event.getWorld().addEntity(new ItemEntity((World) event.getWorld(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), new ItemStack(df)));
					ExtendedWorldData.get(event.getPlayer().world).addDevilFruitInWorld(df);
				}
			}
		}
	}
}
