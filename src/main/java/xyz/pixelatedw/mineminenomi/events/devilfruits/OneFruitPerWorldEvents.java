package xyz.pixelatedw.mineminenomi.events.devilfruits;

import java.util.List;

import net.minecraft.block.LeavesBlock;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.item.ItemFrameEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.inventory.container.ShulkerBoxContainer;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedOutEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
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
	public static void onStored(PlayerContainerEvent.Open event)
	{
		if (CommonConfig.instance.hasOneFruitPerWorldExtendedLogic() && event.getContainer() instanceof ShulkerBoxContainer)
		{
			PlayerEntity player = event.getPlayer();
			for (int i = 0; i < player.inventory.mainInventory.size(); i++)
			{
				ItemStack stack = player.inventory.mainInventory.get(i);
				if (stack != null && stack.getItem() instanceof AkumaNoMiItem)
				{
					event.getPlayer().dropItem(stack.copy(), false);
					stack.shrink(stack.getCount());
				}
			}

			OneFruitPerWorldEvents.dropFruitsFromNearbyContainers(event.getPlayer());
		}
	}
	
	@SubscribeEvent
	public static void onStored(PlayerContainerEvent.Close event)
	{
		if (CommonConfig.instance.hasOneFruitPerWorldExtendedLogic() && !(event.getContainer() instanceof PlayerContainer))
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

			OneFruitPerWorldEvents.dropFruitsFromNearbyContainers(event.getPlayer());
		}
	}

	@SubscribeEvent
	public static void onRightClick(PlayerInteractEvent.EntityInteract event)
	{
		if (CommonConfig.instance.hasOneFruitPerWorldExtendedLogic() && event.getTarget() instanceof ItemFrameEntity && !event.getItemStack().isEmpty() && event.getItemStack().getItem() instanceof AkumaNoMiItem)
		{
			event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public static void onClonePlayer(PlayerEvent.Clone event)
	{
		if (CommonConfig.instance.getAfterDeathLogic() == CommonConfig.KeepStatsLogic.CUSTOM)
		{
			for (String stat : CommonConfig.instance.getStatsToKeep())
			{
				if (WyHelper.getResourceName(stat).equals("devil_fruit"))
					return;
			}
		}
		else if (CommonConfig.instance.getAfterDeathLogic() == CommonConfig.KeepStatsLogic.FULL)
			return;

		IDevilFruit oldDevilFruit = DevilFruitCapability.get(event.getOriginal());
		ExtendedWorldData worldData = ExtendedWorldData.get(event.getOriginal().world);
		
		if(CommonConfig.instance.hasOneFruitPerWorldSimpleLogic())
		{
			worldData.removeAteDevilFruit(event.getOriginal());
			worldData.removeDevilFruitInWorld(oldDevilFruit.getDevilFruit());
		}
	}

	@SubscribeEvent
	public static void onEntityJoinWorld(EntityJoinWorldEvent event)
	{
		if(event.getEntity() instanceof PlayerEntity && CommonConfig.instance.hasOneFruitPerWorldExtendedLogic())
		{
			OneFruitPerWorldEvents.dropFruitsFromNearbyContainers((PlayerEntity) event.getEntity());
		}
	}
	
	@SubscribeEvent
	public static void onEntityLeavesWorld(PlayerLoggedOutEvent event)
	{
		if(CommonConfig.instance.hasOneFruitPerWorldExtendedLogic())
		{
			OneFruitPerWorldEvents.dropFruitsFromNearbyContainers((PlayerEntity) event.getEntity());
		}
	}
	
	@SubscribeEvent
	public static void onItemPickedUp(EntityItemPickupEvent event)
	{
		if (CommonConfig.instance.hasOneFruitPerWorldExtendedLogic())
		{
			PlayerEntity player = event.getPlayer();
			ItemStack stack = event.getItem().getItem();
			
			if(stack.getItem() == Items.SHULKER_BOX && stack.hasTag())
			{
				ListNBT items = stack.getOrCreateTag().getCompound("BlockEntityTag").getList("Items", Constants.NBT.TAG_COMPOUND);
				for (int i = 0; i < items.size(); i++)
				{
					CompoundNBT itemNBT = items.getCompound(i);
					String itemId = itemNBT.getString("id");

					Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemId));
					if (item == null)
						continue;

					if (item instanceof AkumaNoMiItem)
						items.remove(i);
				}
			}
			else if(stack.getItem() instanceof AkumaNoMiItem)
			{
				int inventoryDevilFruits = 0;
				for(ItemStack invStack : player.inventory.mainInventory)
				{
					if(invStack != null && invStack.getItem() instanceof AkumaNoMiItem)
					{
						inventoryDevilFruits++;
						if(inventoryDevilFruits >= 3)
							break;
					}
				}
				
				if(inventoryDevilFruits >= 3)
					event.setCanceled(true);
			}
		}
	}
	
	private static void dropFruitsFromNearbyContainers(PlayerEntity player)
	{
		List<BlockPos> blockPosList = WyHelper.getNearbyTileEntities(player, 40);

		for (BlockPos pos : blockPosList)
		{
			TileEntity te = player.world.getTileEntity(pos);

			if (te instanceof IInventory)
			{
				for (int i = 0; i < ((IInventory) te).getSizeInventory(); i++)
				{
					ItemStack stack = ((IInventory) te).getStackInSlot(i);
					if (stack != null && stack.getItem() instanceof AkumaNoMiItem)
					{
						player.dropItem(stack.copy(), false);
						stack.shrink(stack.getCount());
					}
				}
			}
		}
	}
}
