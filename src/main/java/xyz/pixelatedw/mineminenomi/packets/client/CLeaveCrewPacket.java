package xyz.pixelatedw.mineminenomi.packets.client;

import java.util.UUID;
import java.util.function.Supplier;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.api.crew.Crew;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncWorldDataPacket;
import xyz.pixelatedw.wypi.network.WyNetwork;

public class CLeaveCrewPacket
{
	public CLeaveCrewPacket() {}
	
	public void encode(PacketBuffer buffer)
	{
	}
	
	public static CLeaveCrewPacket decode(PacketBuffer buffer)
	{
		CLeaveCrewPacket msg = new CLeaveCrewPacket();
		return msg;
	}

	public static void handle(CLeaveCrewPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = ctx.get().getSender();
				UUID uuid = player.getUniqueID();
				ExtendedWorldData worldData = ExtendedWorldData.get(player.world);
				
				Crew crew = worldData.getCrewWithMember(uuid);
				
				if(crew != null)
				{
					crew.removeMember(uuid);
					if(crew.getMembers().size() <= 0)
					{
						worldData.removeCrew(crew);
						WyNetwork.sendToAll(new SSyncWorldDataPacket(worldData));
					}
					else
					{
						WyNetwork.sendTo(new SSyncWorldDataPacket(worldData), player);
					}
				}
			});	
		}
		ctx.get().setPacketHandled(true);
	}

}
