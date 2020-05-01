package xyz.pixelatedw.mineminenomi.packets.server;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.container.Container;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.api.TradeEntry;
import xyz.pixelatedw.mineminenomi.containers.TraderContainer;

public class SUpdateTraderOffersPacket
{
	private int traderEntity;
	private List<TradeEntry> tradeEntries;

	public SUpdateTraderOffersPacket()
	{
	}

	public SUpdateTraderOffersPacket(int traderEntity, List<TradeEntry> tradeEntries)
	{
		this.traderEntity = traderEntity;
		this.tradeEntries = tradeEntries;
	}

	public void encode(PacketBuffer buffer)
	{
		buffer.writeInt(this.traderEntity);
		buffer.writeInt(this.tradeEntries.size());
		for (TradeEntry entry : this.tradeEntries)
		{
			buffer.writeItemStack(entry.getItemStack());
		}
	}

	public static SUpdateTraderOffersPacket decode(PacketBuffer buffer)
	{
		SUpdateTraderOffersPacket msg = new SUpdateTraderOffersPacket();
		msg.traderEntity = buffer.readInt();
		int size = buffer.readInt();
		List<TradeEntry> entries = new ArrayList<TradeEntry>();
		for (int i = 0; i < size; i++)
		{
			entries.add(new TradeEntry(buffer.readItemStack()));
		}
		msg.tradeEntries = entries;
		return msg;
	}

	public static void handle(SUpdateTraderOffersPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
		{
			ctx.get().enqueueWork(() ->
			{
				ClientHandler.handle(message);
			});
		}
		ctx.get().setPacketHandled(true);
	}

	public static class ClientHandler
	{
		@OnlyIn(Dist.CLIENT)
		public static void handle(SUpdateTraderOffersPacket message)
		{
			Container container = Minecraft.getInstance().player.openContainer;
			Entity entity = Minecraft.getInstance().world.getEntityByID(message.traderEntity);
			if (container instanceof TraderContainer)
			{
				((TraderContainer)container).setTradingItems(message.tradeEntries);
				((TraderContainer)container).setTrader(entity);
			}
		}
	}
}
