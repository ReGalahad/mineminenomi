package xyz.pixelatedw.mineminenomi.events.devilfruits;

import java.util.List;

import net.minecraft.block.LeavesBlock;
import net.minecraft.client.gui.screen.inventory.CreativeScreen.CreativeContainer;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.item.ItemFrameEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
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
public class OneFruitPerWorldEvents
{
	@SubscribeEvent
	public static void onBreak(BreakEvent event)
	{
		boolean hasShears = event.getPlayer().getHeldItem(event.getPlayer().getActiveHand()).getItem() == Items.SHEARS;
		if (CommonConfig.instance.getDevilFruitDropsFromLeavesChance() > 0 && event.getState().getBlock() instanceof LeavesBlock && !hasShears)
		{
			double chance = WyHelper.randomWithRange(0, 100) + WyHelper.randomDouble();

			if (chance < CommonConfig.instance.getDevilFruitDropsFromLeavesChance())
			{
				AkumaNoMiItem df = ModValues.devilfruits.get((int) WyHelper.randomWithRange(0, ModValues.devilfruits.size() - 1));

				boolean isAvailable = true && DevilFruitHelper.oneFruitPerWorldCheck((World) event.getWorld(), df);

				if (isAvailable)
				{
					event.getWorld().addEntity(new ItemEntity((World) event.getWorld(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), new ItemStack(df)));
					ExtendedWorldData.get(event.getPlayer().world).addDevilFruitInWorld(df);
				}
			}
		}
	}

	@SubscribeEvent
	public static void onExpire(ItemExpireEvent event)
	{
		if (CommonConfig.instance.hasOneFruitPerWorldSimpleLogic() && event.getEntityItem().getItem().getItem() instanceof AkumaNoMiItem)
		{
			ItemStack itemStack = event.getEntityItem().getItem();
			AkumaNoMiItem item = (AkumaNoMiItem) itemStack.getItem();
			ExtendedWorldData worldData = ExtendedWorldData.get(event.getEntityItem().world);

			worldData.removeDevilFruitInWorld(item);
		}
	}

	@SubscribeEvent
	public static void onStored(PlayerContainerEvent.Close event)
	{
		if (CommonConfig.instance.hasOneFruitPerWorldExtendedLogic() && !(event.getContainer() instanceof CreativeContainer) && !(event.getContainer() instanceof PlayerContainer))
		{
			int containerSlots = event.getContainer().inventorySlots.size() - (event.getPlayer().inventory.mainInventory.size());
			for (int i = 0; i < containerSlots; i++)
			{
				Slot slot = event.getContainer().inventorySlots.get(i);
				if (slot.getHasStack() && slot.getStack().getItem() instanceof AkumaNoMiItem)
				{
					event.getPlayer().dropItem(slot.getStack().copy(), false);
					slot.decrStackSize(1);
				}
			}

			List<BlockPos> blockPosList = WyHelper.getNearbyTileEntities(event.getPlayer(), 30);

			for (BlockPos pos : blockPosList)
			{
				TileEntity te = event.getPlayer().world.getTileEntity(pos);

				if (te instanceof IInventory)
				{
					for (int i = 0; i < ((IInventory) te).getSizeInventory(); i++)
					{
						Slot slot = event.getContainer().inventorySlots.get(i);
						if (slot.getHasStack() && slot.getStack().getItem() instanceof AkumaNoMiItem)
						{
							event.getPlayer().dropItem(slot.getStack().copy(), false);
							slot.decrStackSize(1);
						}
					}
				}
			}
		}
	}
	
	@SubscribeEvent
	public static void onRightClick(PlayerInteractEvent.EntityInteract event)
	{
		if(CommonConfig.instance.hasOneFruitPerWorldExtendedLogic() && event.getTarget() instanceof ItemFrameEntity && !event.getItemStack().isEmpty() && event.getItemStack().getItem() instanceof AkumaNoMiItem)
		{		
			event.setCanceled(true);
		}
	}
}
