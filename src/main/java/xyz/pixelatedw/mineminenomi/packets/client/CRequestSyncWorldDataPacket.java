package xyz.pixelatedw.mineminenomi.packets.client;

import java.util.function.Supplier;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncWorldDataPacket;
import xyz.pixelatedw.wypi.network.WyNetwork;

public class CRequestSyncWorldDataPacket
{
	public CRequestSyncWorldDataPacket() {}
	
	public void encode(PacketBuffer buffer)
	{
	}
	
	public static CRequestSyncWorldDataPacket decode(PacketBuffer buffer)
	{
		CRequestSyncWorldDataPacket msg = new CRequestSyncWorldDataPacket();
		return msg;
	}
	
	public static void handle(CRequestSyncWorldDataPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = ctx.get().getSender();
				ExtendedWorldData worldData = ExtendedWorldData.get(player.world);

				WyNetwork.sendTo(new SSyncWorldDataPacket(worldData), player);
			});			
		}
		ctx.get().setPacketHandled(true);
	}
}
