package xyz.pixelatedw.mineminenomi.packets.client;

import java.util.function.Supplier;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.data.entity.jollyroger.IJollyRoger;
import xyz.pixelatedw.mineminenomi.data.entity.jollyroger.JollyRogerCapability;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncJollyRogerPacket;
import xyz.pixelatedw.wypi.network.WyNetwork;

public class CRequestSyncJollyRogerDataPacket
{
	public CRequestSyncJollyRogerDataPacket() {}
	
	public void encode(PacketBuffer buffer)
	{
	}
	
	public static CRequestSyncJollyRogerDataPacket decode(PacketBuffer buffer)
	{
		CRequestSyncJollyRogerDataPacket msg = new CRequestSyncJollyRogerDataPacket();
		return msg;
	}
	
	public static void handle(CRequestSyncJollyRogerDataPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = ctx.get().getSender();
				IJollyRoger props = JollyRogerCapability.get(player);

				WyNetwork.sendTo(new SSyncJollyRogerPacket(player.getEntityId(), props), player);
			});			
		}
		ctx.get().setPacketHandled(true);
	}
}
