package xyz.pixelatedw.mineminenomi.packets.client;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.api.TradeEntry;
import xyz.pixelatedw.mineminenomi.entities.mobs.misc.TraderEntity;

public class CUpdateTraderOffersPacket
{
	private int traderEntity;
	private List<TradeEntry> tradeEntries;

	public CUpdateTraderOffersPacket()
	{
	}

	public CUpdateTraderOffersPacket(int traderEntity, List<TradeEntry> tradeEntries)
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

	public static CUpdateTraderOffersPacket decode(PacketBuffer buffer)
	{
		CUpdateTraderOffersPacket msg = new CUpdateTraderOffersPacket();
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

	public static void handle(CUpdateTraderOffersPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = ctx.get().getSender();
				Entity entity = player.world.getEntityByID(message.traderEntity);
				if (entity instanceof TraderEntity)
					((TraderEntity)entity).setTradingItems(message.tradeEntries);
			});
		}
		ctx.get().setPacketHandled(true);
	}

}
