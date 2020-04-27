package xyz.pixelatedw.mineminenomi.packets.client;

import java.util.function.Supplier;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.wypi.data.quest.IQuestData;
import xyz.pixelatedw.wypi.data.quest.QuestDataCapability;
import xyz.pixelatedw.wypi.network.WyNetwork;
import xyz.pixelatedw.wypi.network.packets.server.SSyncQuestDataPacket;

public class CRequestQuestDataSyncPacket
{
	public CRequestQuestDataSyncPacket() {}
	
	public void encode(PacketBuffer buffer)
	{
	}
	
	public static CRequestQuestDataSyncPacket decode(PacketBuffer buffer)
	{
		CRequestQuestDataSyncPacket msg = new CRequestQuestDataSyncPacket();
		return msg;
	}
	
	public static void handle(CRequestQuestDataSyncPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = ctx.get().getSender();
				IQuestData props = QuestDataCapability.get(player);

				WyNetwork.sendTo(new SSyncQuestDataPacket(props), player);
			});			
		}
		ctx.get().setPacketHandled(true);
	}
}
