package xyz.pixelatedw.mineminenomi.packets.client;

import java.util.function.Supplier;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.api.crew.Crew;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.wypi.debug.WyDebug;

public class CCreateCrewPacket
{
	private String name;
	
	public CCreateCrewPacket() {}

	public CCreateCrewPacket(String name)
	{
		this.name = name;
	}
	
	public void encode(PacketBuffer buffer)
	{
		buffer.writeInt(this.name.length());
		buffer.writeString(this.name);
	}
	
	public static CCreateCrewPacket decode(PacketBuffer buffer)
	{
		CCreateCrewPacket msg = new CCreateCrewPacket();
		int len = buffer.readInt();
		msg.name = buffer.readString(len);
		return msg;
	}

	public static void handle(CCreateCrewPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = ctx.get().getSender();
				ExtendedWorldData worldProps = ExtendedWorldData.get(player.world);
										
				Crew crew = worldProps.getCrewWithCaptain(player.getUniqueID());
				if(crew == null)
				{
					WyDebug.debug("Cannot find a crew for captain " + player.getName().getFormattedText());
					return;
				}
				
				crew.setName(message.name);
				EntityStatsCapability.get(player).setInCrew(true);
				crew.create(player.world);
			});			
		}
		ctx.get().setPacketHandled(true);
	}
}
