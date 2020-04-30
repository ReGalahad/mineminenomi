package xyz.pixelatedw.mineminenomi.containers;

import java.util.List;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.network.PacketBuffer;
import xyz.pixelatedw.mineminenomi.api.TradeEntry;
import xyz.pixelatedw.mineminenomi.entities.mobs.misc.TraderEntity;
import xyz.pixelatedw.mineminenomi.init.ModContainers;

public class TraderContainer extends Container
{

	private TraderEntity trader;
	private PlayerEntity player;
	private List<TradeEntry> entries;
	
	public TraderContainer(int windowId, PlayerInventory playerInventory, PacketBuffer data)
	{
		super(ModContainers.TRADER, windowId);
	}

	public TraderContainer(int windowId, PlayerInventory playerInventory, PlayerEntity player, TraderEntity trader, List<TradeEntry> entries)
	{
		super(ModContainers.TRADER, windowId);
		
		this.player = player;
		this.trader = trader;
		this.entries = entries;
	}
	
	@Override
	public boolean canInteractWith(PlayerEntity playerIn)
	{
		return true;
	}

	public void setTradingItems(List<TradeEntry> entries)
	{
		this.entries = entries;
	}
	
	public List<TradeEntry> getTradingItems()
	{
		return this.entries;
	}
	
	public TraderEntity getTrader()
	{
		return this.trader;
	}
}
