package xyz.pixelatedw.mineminenomi.packets.client;

import java.util.function.Supplier;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncCrewDataPacket;
import xyz.pixelatedw.wypi.network.WyNetwork;

public class CRequestSyncCrewDataPacket
{
	public CRequestSyncCrewDataPacket() {}
	
	public void encode(PacketBuffer buffer)
	{
	}
	
	public static CRequestSyncCrewDataPacket decode(PacketBuffer buffer)
	{
		CRequestSyncCrewDataPacket msg = new CRequestSyncCrewDataPacket();
		return msg;
	}
	
	public static void handle(CRequestSyncCrewDataPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = ctx.get().getSender();
				ExtendedWorldData worldData = ExtendedWorldData.get(player.world);

				WyNetwork.sendTo(new SSyncCrewDataPacket(player.getEntityId(), worldData), player);
			});			
		}
		ctx.get().setPacketHandled(true);
	}
}
